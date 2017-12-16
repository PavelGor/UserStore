package com.gordeev.userstore;

import com.gordeev.userstore.servlet.AddNewUserServlet;
import com.gordeev.userstore.servlet.IndexLoginServlet;
import com.gordeev.userstore.servlet.UpdateUserServlet;
import com.gordeev.userstore.servlet.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        UsersServlet usersServlet = new UsersServlet();
        IndexLoginServlet indexLoginServlet = new IndexLoginServlet();
        AddNewUserServlet addNewUserServlet = new AddNewUserServlet();
        UpdateUserServlet updateUserServlet = new UpdateUserServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(indexLoginServlet), "/index.html");
        context.addServlet(new ServletHolder(usersServlet), "/users.html");
        context.addServlet(new ServletHolder(addNewUserServlet), "/addUser.html");
        context.addServlet(new ServletHolder(updateUserServlet), "/update.html");
        context.addServlet(new ServletHolder(indexLoginServlet), "/");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
