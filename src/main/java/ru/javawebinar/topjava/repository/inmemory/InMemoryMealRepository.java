package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Comparator<Meal> mealComparator = (o1, o2) -> -o1.getDateTime().compareTo(o2.getDateTime());
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int authUserId) {
        if (meal.isNew()) {
            log.info("save meal {}", meal);
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        if (authUserId == meal.getUserId()) {
            log.info("update meal {}", meal);
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int authUserId) {
        log.info("delete meal {}", id);
        if (authUserId == repository.get(id).getUserId()) {
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int authUserId) {
        log.info("get meal {}", id);
        try {
            if (authUserId == repository.get(id).getUserId()) {
                return repository.get(id);
            }
        } catch (NullPointerException e) {
            log.warn("Meal not found", e);
//            throw new NotFoundException("Meal not found");
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int authUserId) {
        log.info("get all meal");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == authUserId)
                .sorted(mealComparator)
                .collect(Collectors.toList());
    }
}

