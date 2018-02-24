package com.gordeev.userstore;

import com.gordeev.userstore.dao.jdbc.JdbcUserDao;
import com.gordeev.userstore.locator.ServiceLocator;
import com.gordeev.userstore.servlet.AddUserServlet;
import com.gordeev.userstore.servlet.LoginPageServlet;
import com.gordeev.userstore.servlet.UpdateUserServlet;
import com.gordeev.userstore.servlet.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        ServiceLocator.register("userDao", jdbcUserDao);

        UsersServlet usersServlet = new UsersServlet();
        LoginPageServlet loginPageServlet = new LoginPageServlet();
        AddUserServlet addUserServlet = new AddUserServlet();
        UpdateUserServlet updateUserServlet = new UpdateUserServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(usersServlet), "/users.html");
        context.addServlet(new ServletHolder(addUserServlet), "/addUser.html");
        context.addServlet(new ServletHolder(updateUserServlet), "/update.html");
        context.addServlet(new ServletHolder(loginPageServlet), "/");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
