package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(MEAL6.getId(), USER_ID);
        MealTestData.assertMatch(actual, MEAL6);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(ADMINMEAL1.getId(), USER_ID);
    }

    @Test
    public void delete() {
        service.delete(MEALID, USER_ID);
        MealTestData.assertMatch(service.getAll(USER_ID), MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    public void getBetween() {
        MealTestData.assertMatch(service.getBetweenInclusive(
                LocalDate.of(2020, Month.OCTOBER, 24),
                LocalDate.of(2020, Month.OCTOBER, 25), USER_ID), MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getAll() {
        MealTestData.assertMatch(service.getAll(USER_ID), MEALSSORTED);
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        service.update(updated, USER_ID);
        MealTestData.assertMatch(service.get(MEAL1.getId(), USER_ID), updated);
    }

    @Test
    public void create() {
        Meal created = MealTestData.getCreated();
        service.create(created, USER_ID);
        MealTestData.assertMatch(service.getAll(USER_ID), created, MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }
}