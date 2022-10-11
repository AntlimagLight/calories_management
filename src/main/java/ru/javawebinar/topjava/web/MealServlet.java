package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.DataInit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MealServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/users.jsp").forward(request, response);
        request.setAttribute("mealList", DataInit.doInit());
        response.sendRedirect("meals.jsp");
    }
}
