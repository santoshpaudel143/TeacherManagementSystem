package com.project.teachermanagementsystem;

import android.text.TextUtils;
import android.util.Patterns;

public class Validator {

    public static String saveValidate(String fullName, String address, String contact, String email, String department, String subject, String salary, String age) {
        String validationMessage = null;
        if (TextUtils.isEmpty(fullName)) {
            validationMessage = "Full Name cannot be empty.";
        }
        if (address.isEmpty()) {
            validationMessage = "Address cannot be empty";
        }
        if (contact.isEmpty()) {
            validationMessage = "Contact cannot be empty";
        }
        if (email.isEmpty()) {
            validationMessage = "Email cannot be empty";
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                validationMessage = "Enter a valid Email";
            }
        }
        if (department.isEmpty()) {
            validationMessage = "Department cannot be empty";
        }
        if (subject.isEmpty()) {
            validationMessage = "Subject cannot be empty";
        }
        if (salary.isEmpty()) {
            validationMessage = "Salary cannot be empty";
        } else {
            if (Integer.parseInt(salary) < 20000) {
                validationMessage = "Salary cannot be less than 20000";
            }
        }
        if (age.isEmpty()) {
            validationMessage = "Age cannot be empty";
        } else {
            if (Integer.parseInt(age) < 18 || Integer.parseInt(age) > 60) {
                validationMessage = "Age cannot be less than 18 or greater than 60";
            }
        }
        return validationMessage;
    }

    public static String editValidate(String id, String fullName, String address, String contact, String email, String department, String subject, String salary, String age) {
        String validationMessage;
        if (id.isEmpty()) {
            validationMessage = "Id cannot be empty";
            return validationMessage;
        } else {
            return saveValidate(fullName, address, contact, email, department, subject, salary, age);
        }
    }

//    public static String idValidate(String id) {
//        String validationMessage;
//        if (id.isEmpty()) {
//            validationMessage = "Id cannot be empty";
//            return validationMessage;
//        }
//        return null;
//    }
}