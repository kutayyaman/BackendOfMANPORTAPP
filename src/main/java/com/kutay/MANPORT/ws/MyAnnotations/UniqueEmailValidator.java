package com.kutay.MANPORT.ws.MyAnnotations;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.service.Impl.UserServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> { //ilk verdigimzi sey constraintimiz'in annotation'i ikinci istedigi sey ise hangi tipdeki bir objeyi validate edicek.

    private final UserServiceImpl userService;

    public UniqueEmailValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return false;
        }
        return true;
    }

}
