package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;
import ru.javawebinar.topjava.web.testdata.MealTestData;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.web.testdata.MealTestData.*;
import static ru.javawebinar.topjava.web.testdata.UserTestData.USER_ID;
import static ru.javawebinar.topjava.web.testdata.UserTestData.user;

class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_MEAL_URL = MealRestController.REST_MEAL_URL + '/';

    @Autowired
    private MealService mealService;

    @Test
    void testGet() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MEAL_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(meal1));
    }

    @Test
    void testDelete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_MEAL_URL + MEAL1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(MEAL1_ID, USER_ID));
    }

    @Test
    void testUpdate() throws Exception {
        Meal updated = MealTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_MEAL_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MEAL_MATCHER.assertMatch(mealService.get(MEAL1_ID, USER_ID), updated);
    }

    @Test
    void testCreate() throws Exception {
        Meal newMeal = MealTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_MEAL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)));

        Meal created = MEAL_MATCHER.readFromJson(action);
        int newId = created.id();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(newId, USER_ID), newMeal);
    }

    @Test
    void testGetAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MEAL_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEALTO_MATCHER.contentJson(MealsUtil.getTos(meals, user.getCaloriesPerDay())));
    }

    @Test
    void testFilter() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MEAL_URL + "filter")
                .param("startDate", "2020-01-30").param("startTime", "07:00")
                .param("endDate", "2020-01-31").param("endTime", "14:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MEALTO_MATCHER.contentJson(mealsBetween));
    }

    @Test
    void testFilterAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MEAL_URL + "filter?startDate=&endTime="))
                .andExpect(status().isOk())
                .andExpect(MEALTO_MATCHER.contentJson(MealsUtil.getTos(meals, user.getCaloriesPerDay())));
    }
}