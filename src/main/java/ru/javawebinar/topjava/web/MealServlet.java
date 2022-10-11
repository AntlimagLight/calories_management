package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.DataInit;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mealList", DataInit.doInit());
//        response.sendRedirect("meals.jsp");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
