package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    @Autowired
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int authUserId) {
        return repository.save(meal, authUserId);
    }

    public void delete(int id, int authUserId) {
        checkNotFoundWithId(repository.delete(id, authUserId), id);
    }

    public Meal get(int id, int authUserId) {
        return checkNotFoundWithId(repository.get(id, authUserId), id);
    }

    public List<MealTo> getAll(int authUserId) {
        return MealsUtil.getTos(repository.getAll(authUserId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void update(Meal meal, int id, int authUserId) {
        checkNotFoundWithId(repository.save(meal, authUserId), id);
    }

}