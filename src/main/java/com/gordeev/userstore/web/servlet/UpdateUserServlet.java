package com.gordeev.userstore.web.servlet;

import com.gordeev.userstore.entity.User;
import com.gordeev.userstore.service.UserService;
import com.gordeev.userstore.web.servlet.utils.ServletUtils;
import com.gordeev.userstore.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();

        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getById(id);

        pageVariables.put("user", user);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("updateUser.html", pageVariables));
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        User user = ServletUtils.createUserFromRequest(request);

        userService.update(user);

        response.sendRedirect("/users");
    }
}
