package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class DataInit {
    public static final LocalTime START_TIME = LocalTime.MIN;
    public static final LocalTime END_TIME = LocalTime.MAX;
    public static final int CALORIESPERDAY = 2000;
    private static final List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 0, 0), "Шальной чупачупс", 100),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    public static List<Meal> getMeals() {
        return meals;
    }

}
