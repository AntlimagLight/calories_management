package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;

public class MainTest {
    public static void main(String[] args) {

        System.out.println("__TEST USER REPOSITORY__");

        UserRepository testUserRepository = new InMemoryUserRepository();

        testUserRepository.save(new User(null,"Kate","Kate@gmail.com","qwerty", Role.USER));
        testUserRepository.save(new User(null,"Den","Den@gmail.com","12345", Role.USER));
        testUserRepository.save(new User(null,"Boris","Boris@gmail.com","asd", Role.USER));
        testUserRepository.save(new User(null,"Boris","Bobobo@gmail.com","thisissparta", Role.USER, Role.ADMIN));

        System.out.println(testUserRepository.getAll());

        System.out.println("____");

        System.out.println(testUserRepository.getByEmail("Boris@gmail.com"));

        System.out.println("____");

        System.out.println(testUserRepository.get(2));
        testUserRepository.delete(2);
        System.out.println(testUserRepository.get(2));
        System.out.println(testUserRepository.getAll());

        System.out.println("__TEST MEAL REPOSITORY__");

        MealRepository testMealRepository = new InMemoryMealRepository();
        testMealRepository.save(new Meal(SecurityUtil.authUserId(),
                LocalDateTime.of(2022, Month.OCTOBER, 18, 12, 3),
                "Новая Еда", 200));
        testMealRepository.save(new Meal(SecurityUtil.authUserId(),
                LocalDateTime.of(2018, Month.OCTOBER, 12, 12, 0),
                "Старая Еда", 200));
        testMealRepository.save(new Meal(2,
                LocalDateTime.of(2022, Month.OCTOBER, 15, 11, 3),
                "Чужая Еда", 600));
        testMealRepository.save(new Meal(3,
                LocalDateTime.of(2021, Month.OCTOBER, 15, 9, 3),
                "Еще чужая Еда", 600));

        System.out.println("____");

        testMealRepository.getAll().forEach(System.out::println);
        testMealRepository.delete(9);

        System.out.println("____");

        System.out.println(testMealRepository.get(9));
        System.out.println(testMealRepository.get(8));
        testMealRepository.save(new Meal(4, SecurityUtil.authUserId(),
                LocalDateTime.of(2022, Month.OCTOBER, 12, 12, 3),
                "Обновленная Еда", 200));
        testMealRepository.getAll().forEach(System.out::println);

    }
}
