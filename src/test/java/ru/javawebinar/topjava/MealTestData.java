package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.Month;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEALID = START_SEQ + 3;
    public static final Meal meal1 = new Meal(MEALID, of(2020, Month.OCTOBER, 25, 8, 0), "popcorn", 200);
    public static final Meal meal2 = new Meal(MEALID + 1, of(2020, Month.OCTOBER, 25, 11, 0), "fish", 300);
    public static final Meal meal3 = new Meal(MEALID + 2, of(2020, Month.OCTOBER, 25, 14, 0), "fish", 400);
    public static final Meal meal4 = new Meal(MEALID + 3, of(2020, Month.OCTOBER, 26, 17, 0), "apple", 500);
    public static final Meal meal5 = new Meal(MEALID + 4, of(2020, Month.OCTOBER, 26, 19, 0), "milk", 150);
    public static final Meal meal6 = new Meal(MEALID + 5, of(2020, Month.OCTOBER, 26, 22, 0), "potato", 410);
    public static final Meal meal7 = new Meal(MEALID + 6, of(2020, Month.OCTOBER, 26, 23, 30), "tomato", 210);
    public static final Meal meal8 = new Meal(MEALID + 7, of(2020, Month.OCTOBER, 26, 0, 0), "night egg", 100);

    public static final Meal ADMINMEAL1 = new Meal(MEALID + 8, of(2020, Month.OCTOBER, 25, 14, 0), "milk", 210);
    public static final Meal ADMINMEAL2 = new Meal(MEALID + 9, of(2020, Month.OCTOBER, 26, 21, 0), "sausage", 400);

    public static final List<Meal> MEALSSORTED = Arrays.asList( meal7, meal6, meal5, meal4, meal8, meal3, meal2, meal1);

    public static Meal getCreated() {
        return new Meal(null, of(2020, Month.OCTOBER, 27, 18, 30), "New food", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEALID, meal1.getDateTime(), "Updated food", 200);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
