package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository;

import java.util.Collections;

public class MainTest {
    public static void main(String[] args) {
        UserRepository testRepository = new InMemoryUserRepository();

        testRepository.save(new User(null,"Kate","Kate@gmail.com","qwerty", Role.USER));
        testRepository.save(new User(null,"Den","Den@gmail.com","12345", Role.USER));
        testRepository.save(new User(null,"Boris","Boris@gmail.com","asd", Role.USER));
        testRepository.save(new User(null,"Boris","Bobobo@gmail.com","thisissparta", Role.USER, Role.ADMIN));

        System.out.println(testRepository.getAll());

        System.out.println("____");

        System.out.println(testRepository.getByEmail("Boris@gmail.com"));

        System.out.println("____");

        System.out.println(testRepository.get(2));
        testRepository.delete(2);
        System.out.println(testRepository.get(2));
        System.out.println(testRepository.getAll());
    }
}
