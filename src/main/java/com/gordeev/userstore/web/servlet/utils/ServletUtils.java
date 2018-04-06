package com.gordeev.userstore.web.servlet.utils;

import com.gordeev.userstore.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

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
}
