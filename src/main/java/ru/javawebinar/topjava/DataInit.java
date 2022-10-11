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

    static final LocalTime START_TIME = LocalTime.of(0, 0);
    static final LocalTime END_TIME = LocalTime.of(23, 59);
    static final int CALORIESPERDAY = 2000;

    static List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 0, 0), "Шальной чупачупс", 100),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2022, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    public static void main(String[] args) {
        MealTo testMeal =
                new MealTo(LocalDateTime.of(2020, Month.JANUARY, 20, 10, 3), "Хавка",
                        800, true);
        System.out.println(testMeal.getDateTime().toLocalTime());
    }

    public static List<MealTo> doInit() {
        return MealsUtil.filteredByStreams(MEALS, START_TIME, END_TIME, CALORIESPERDAY);
    }
}
