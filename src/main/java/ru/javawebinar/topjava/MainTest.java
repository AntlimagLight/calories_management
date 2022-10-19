package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainTest {
    public static void main(String[] args) {

        System.out.println("__TEST USER REPOSITORY__");

        UserRepository testUserRepository = new InMemoryUserRepository();

        testUserRepository.save(new User(null, "Kate", "Kate@gmail.com", "qwerty", Role.USER));
        testUserRepository.save(new User(null, "Den", "Den@gmail.com", "12345", Role.USER));
        testUserRepository.save(new User(null, "Boris", "Boris@gmail.com", "asd", Role.USER));
        testUserRepository.save(new User(null, "Boris", "Bobobo@gmail.com", "thisissparta", Role.USER, Role.ADMIN));

        System.out.println(testUserRepository.getAll());

        System.out.println("____");

        System.out.println(testUserRepository.getByEmail("BORis@gmail.com"));

        System.out.println("____");

        System.out.println(testUserRepository.get(2));
        testUserRepository.delete(2);
        System.out.println(testUserRepository.get(2));
        System.out.println(testUserRepository.getAll());

        System.out.println("__TEST MEAL REPOSITORY__");

        MealRepository testMealRepository = new InMemoryMealRepository();
        testMealRepository.save(new Meal(1,
                LocalDateTime.of(2022, Month.OCTOBER, 18, 12, 3),
                "Новая Еда", 200), 1);
        testMealRepository.save(new Meal(1,
                LocalDateTime.of(2018, Month.OCTOBER, 12, 12, 0),
                "Старая Еда", 200), 1);
        testMealRepository.save(new Meal(2,
                LocalDateTime.of(2022, Month.OCTOBER, 15, 11, 3),
                "Чужая Еда", 600), 1);
        testMealRepository.save(new Meal(3,
                LocalDateTime.of(2021, Month.OCTOBER, 15, 9, 3),
                "Еще чужая Еда", 600), 1);

        System.out.println("____");

        testMealRepository.getAll(1, LocalDate.MIN, LocalDate.MAX).forEach(System.out::println);
        testMealRepository.delete(9, 1);
        testMealRepository.delete(10, 1);

        System.out.println("____");

        System.out.println(testMealRepository.get(9, 1));
        System.out.println(testMealRepository.get(8, 1));
        testMealRepository.save(new Meal(4, 1,
                LocalDateTime.of(2022, Month.OCTOBER, 12, 12, 3),
                "Обновленная Еда", 200), 1);
        testMealRepository.getAll(1, LocalDate.MIN, LocalDate.MAX).forEach(System.out::println);

        System.out.println("____");

        Map<Integer, Map<Integer, String>> bigMap = new ConcurrentHashMap<>();
        Map<Integer, String> map1 = new ConcurrentHashMap<>();
        Map<Integer, String> map2 = new ConcurrentHashMap<>();
        map1.put(1, "объект1.1");
        map1.put(2, "объект1.2");
        map2.put(1, "объект2.1");
        map2.put(2, "объект2.2");
        bigMap.put(1, map1);
        bigMap.put(2, map2);

        System.out.println(bigMap);
        System.out.println(bigMap.get(1).get(2));
    }
}
