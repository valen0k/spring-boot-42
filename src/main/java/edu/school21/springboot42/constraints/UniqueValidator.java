package edu.school21.springboot42.constraints;

import edu.school21.springboot42.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private UsersService usersService;

    @Override
    public void initialize(Unique unique) {
    }

    @Override
    public boolean isValid(Object email, ConstraintValidatorContext context) {
        return !usersService.findEmail((String)email);
    }
}
