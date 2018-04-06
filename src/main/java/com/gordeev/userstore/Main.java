package com.gordeev.userstore;

import com.gordeev.userstore.dao.jdbc.JdbcUserDao;
import com.gordeev.userstore.locator.ServiceLocator;
import com.gordeev.userstore.service.AuthorizedUserService;
import com.gordeev.userstore.web.filter.SecurityFilter;
import com.gordeev.userstore.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        ServiceLocator.register("userDao", new JdbcUserDao());
        ServiceLocator.register("authUserService", new AuthorizedUserService());

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UsersServlet()), "/users");
        context.addServlet(new ServletHolder(new AddUserServlet()), "/user/add");
        context.addServlet(new ServletHolder(new UpdateUserServlet()), "/user/update");
        context.addServlet(new ServletHolder(new AssetsServlet()), "/assets/*");
        context.addServlet(new ServletHolder(new LoginPageServlet()), "/login");


        //maybe, there is a way of exception? all, without "/login"
        context.addFilter(SecurityFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(SecurityFilter.class, "/user/add", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(SecurityFilter.class, "/user/update", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(SecurityFilter.class, "/assets/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
