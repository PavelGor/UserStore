package com.gordeev.userstore.servlet;

import com.gordeev.userstore.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IndexLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = ServiceForServlet.createPageVariablesMap(request);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = ServiceForServlet.createPageVariablesMap(request);

        response.setContentType("text/html;charset=utf-8");

        if ("qwe".equals(request.getParameter("login")) && "123".equals(request.getParameter("password"))) { //later, add authorization from DB
            response.setStatus(HttpServletResponse.SC_OK);
            response.sendRedirect("/users.html");
        } else {
            response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
        }
    }
}
