package com.gordeev.userstore.servlet;

import com.gordeev.userstore.entity.User;
import com.gordeev.userstore.service.UserService;
import com.gordeev.userstore.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AddUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = ServletUtils.createPageVariablesMap(request);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("addUser.html", pageVariables));
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        //request.setCharacterEncoding("UTF-8");

        User user = ServletUtils.createUserFromRequest(request);

        userService.addUser(user);

        response.sendRedirect("/users.html");
    }
}
