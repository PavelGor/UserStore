package com.gordeev.userstore.web.servlet;

import com.gordeev.userstore.entity.User;
import com.gordeev.userstore.service.UserService;
import com.gordeev.userstore.web.servlet.utils.ServletUtils;
import com.gordeev.userstore.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("addUser.html"));
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        User user = ServletUtils.createUserFromRequest(request);

        userService.add(user);

        response.sendRedirect("/users");
    }
}
