package com.gordeev.userstore.servlet.utils;

import com.gordeev.userstore.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ServletUtils {

    public static User createUserFromRequest(HttpServletRequest request){
        String idString = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String salaryString = request.getParameter("salary");
        double salary;
        if (request.getParameter("salary").isEmpty()){
            salary = 0;
        } else {
            salary = Double.valueOf(salaryString);
        }

        LocalDate dateOfBirth;
        if (request.getParameter("dateOfBirth").isEmpty()){
            dateOfBirth = LocalDate.parse("01.01.2000");
        } else {
            dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        }

        User user = new User();
        int id;
        if (idString!=null){
            id = Integer.parseInt(idString);
            user.setId(id);
        }
        user.setFirstName(firstName.isEmpty() ? "default FirstName" : firstName);
        user.setLastName(lastName.isEmpty() ? "default LastName" : lastName);
        user.setSalary(salary);
        user.setDateOfBirth(dateOfBirth);
        return user;
    }

    public static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

}
