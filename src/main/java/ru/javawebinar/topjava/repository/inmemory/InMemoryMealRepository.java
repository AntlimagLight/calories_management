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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Comparator<Meal> mealComparator = Comparator.comparing(Meal::getDateTime).reversed();
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            log.info("save meal {}", meal);
        }
        if (userId != meal.getUserId()) {
            log.warn("wrong meal owner {} != {}", userId, meal.getUserId());
            return null;
        }
        repository.compute(userId, (id, singleUserStorage) -> {
            if (singleUserStorage == null) {
                singleUserStorage = new ConcurrentHashMap<>();
            }
            singleUserStorage.computeIfPresent(meal.getId(), (mealId, oldMeal) -> {
                log.info("update meal {}", meal);
                return meal;
            });
            singleUserStorage.putIfAbsent(meal.getId(), meal);
            return singleUserStorage;
        });
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete meal {}", id);
        repository.compute(userId, (mealId, singleUserStorage) -> {
            if (singleUserStorage == null) {
                log.warn("This user has no food {}", userId);
                return null;
            }
            if (!singleUserStorage.containsKey(id)) {
                log.warn("food with given id was not found {}", id);
                return singleUserStorage;
            }
            singleUserStorage.remove(id);
            return singleUserStorage;
        });
        return true;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get meal {}", id);
        Meal meal = repository.get(userId).get(id);
        if (meal == null) {
            log.warn("food with given id was not found in the repository");
            return null;
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("get all filtered meal");
        return repository.get(userId).values().stream()
                .filter(meal -> DateTimeUtil.betweenDatesInclusive(meal.getDateTime().toLocalDate(), startDate, endDate))
                .sorted(mealComparator)
                .collect(Collectors.toList());
    }
}

