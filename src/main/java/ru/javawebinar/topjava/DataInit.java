package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class DataInit {
    private static final LocalTime START_TIME = LocalTime.of(0, 0);
    private static final LocalTime END_TIME = LocalTime.of(23, 59);
    private static final int CALORIESPERDAY = 2000;
    private static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 0, 0), "Шальной чупачупс", 100),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    public static List<Meal> getMEALS() {
        return MEALS;
    }

    public static LocalTime getStartTime() {
        return START_TIME;
    }

    public static int getCALORIESPERDAY() {
        return CALORIESPERDAY;
    }

    public static LocalTime getEndTime() {
        return END_TIME;
    }

}
