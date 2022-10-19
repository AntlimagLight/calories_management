package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Comparator<Meal> mealComparator = Comparator.comparing(Meal::getDateTime).reversed();
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            log.info("save meal {}", meal);
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        if (userId == repository.get(meal.getId()).getUserId()) {
            log.info("update meal {}", meal);
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete meal {}", id);
        Meal meal = repository.get(id);
        if (meal == null) {
            log.warn("food with given id was not found in the repository");
            return false;
        }
        if (userId == meal.getUserId()) {
            return repository.remove(id) != null;
        }
        log.warn("the specified food belongs to another user");
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get meal {}", id);
        Meal meal = repository.get(id);
        if (meal == null) {
            log.warn("food with given id was not found in the repository");
            return null;
        }
        if (userId == meal.getUserId()) {
            return meal;
        }
        log.warn("the specified food belongs to another user");
        return null;
    }

    @Override
    public List<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("get all filtered meal");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .filter(meal -> DateTimeUtil.betweenDatesInclusive(meal.getDateTime().toLocalDate(), startDate, endDate))
                .sorted(mealComparator)
                .collect(Collectors.toList());
    }
}

