package com.gordeev.userstore.web.servlet;

import com.gordeev.userstore.entity.AuthorizedUser;
import com.gordeev.userstore.locator.ServiceLocator;
import com.gordeev.userstore.service.AuthorizedUserService;
import com.gordeev.userstore.web.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class LoginPageServlet extends HttpServlet {

    private AuthorizedUserService authorizedUserService = (AuthorizedUserService) ServiceLocator.getService("authUserService");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("login.html"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        AuthorizedUser autenticatedUser = authorizedUserService.autenticate(login, password);

        if (autenticatedUser != null){
            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("user-token", token);
            response.addCookie(cookie);
            response.sendRedirect("/users");
        } else {
            response.sendRedirect("/login");
        }
    }
}
