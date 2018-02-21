package com.gordeev.userstore.servlet;

import com.gordeev.userstore.entity.User;
import com.gordeev.userstore.service.UserService;
import com.gordeev.userstore.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> usersList = userService.getAll();

        Map<String, Object> pageVariables = ServletUtils.createPageVariablesMap(request);

        response.setContentType("text/html;charset=utf-8");

        if (usersList == null || usersList.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("usersList", usersList == null ? "" : usersList);

        response.getWriter().println(PageGenerator.instance().getPage("users.html", pageVariables));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
