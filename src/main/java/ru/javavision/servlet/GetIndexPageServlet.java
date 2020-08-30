package ru.javavision.servlet;

import ru.javavision.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetIndexPageServlet extends HttpServlet {

    private static String index = "/WEB-INF/view/index.jsp";
    private Map<Integer, User> users;

    @Override
    public void init() throws ServletException {

        final Object users = getServletContext().getAttribute("users");

        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("Your repository is not initialized");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("users", users.values());
        req.getRequestDispatcher(index).forward(req, resp);
    }
}