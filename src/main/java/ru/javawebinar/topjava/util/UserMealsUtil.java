package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        int STARTHOUR = 7;
        int ENDHOUR = 12;

        meals.forEach(System.out::println);
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(STARTHOUR, 0), LocalTime.of(12, 0), 2000);
        System.out.println("___Circles___");
        mealsTo.forEach(System.out::println);

        mealsTo.clear();

        mealsTo = filteredByStreams(meals, LocalTime.of(STARTHOUR, 0), LocalTime.of(ENDHOUR, 0), 2000);
        System.out.println("___Streams___");
        mealsTo.forEach(System.out::println);

    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime,
                                                            LocalTime endTime, int caloriesPerDay) {
        HashMap<LocalDate, Integer> controlMap = new HashMap<>();
        for (UserMeal meal : meals) {
            if (controlMap.containsKey(meal.getDateTime().toLocalDate())) {
                controlMap.computeIfPresent(meal.getDateTime().toLocalDate(), (key, value) -> value + meal.getCalories());
            } else {
                controlMap.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            }
        }
        List<UserMealWithExcess> result = new ArrayList<>();
        for (UserMeal meal : meals) {
            if (meal.getDateTime().toLocalTime().isAfter(startTime) &&
                    meal.getDateTime().toLocalTime().isBefore(endTime)) {
                result.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        controlMap.get(meal.getDateTime().toLocalDate()) <= caloriesPerDay));
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime,
                                                             LocalTime endTime, int caloriesPerDay) {
        HashMap<LocalDate, Integer> controlMap = new HashMap<>();
        meals.stream()
                .filter(meal -> !(controlMap.containsKey(meal.getDateTime().toLocalDate())))
                .forEach(meal -> controlMap.put(meal.getDateTime().toLocalDate(), 0));
        meals.stream()
                .filter(meal -> controlMap.containsKey(meal.getDateTime().toLocalDate()))
                .forEach(meal -> controlMap.computeIfPresent(meal.getDateTime().toLocalDate(),
                        (key, value) -> value + meal.getCalories()));
        System.out.println(controlMap);
        return meals.stream()
                .filter(meal -> meal.getDateTime().toLocalTime().isAfter(startTime) &&
                        meal.getDateTime().toLocalTime().isBefore(endTime))
                .map(meal -> new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        controlMap.get(meal.getDateTime().toLocalDate()) <= caloriesPerDay))
                .collect(Collectors.toList());
    }

}