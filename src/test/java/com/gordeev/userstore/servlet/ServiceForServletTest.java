package com.gordeev.userstore.servlet;

import com.gordeev.userstore.entity.User;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceForServletTest {

    User expectedUser;
    HttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setFirstName("Tolik");
        expectedUser.setLastName("Tr");
        expectedUser.setSalary(3000);
        expectedUser.setDateOfBirth(LocalDate.parse("2000-01-01"));

        request = mock(HttpServletRequest.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("firstName")).thenReturn("Tolik");
        when(request.getParameter("lastName")).thenReturn("Tr");
        when(request.getParameter("salary")).thenReturn("3000");
        when(request.getParameter("dateOfBirth")).thenReturn("2000-01-01");
    }

    @Test
    public void createUserFromRequestTest() {
        User actualUser = ServiceForServlet.createUserFromRequest(request);

        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(),actualUser.getId());
        assertEquals(expectedUser.getFirstName(),actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(),actualUser.getLastName());
        assertEquals(expectedUser.getSalary(),actualUser.getSalary(), 0.1);
    }
}