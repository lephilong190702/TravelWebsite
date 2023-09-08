/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lpl.validators;

import com.lpl.pojo.Tour;
import com.lpl.pojo.User;

import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *
 * @author lephi
 */
@Component
@Setter
public class WebAppValidator implements Validator {
    @Autowired
    private javax.validation.Validator beanValidator;
    
    private Set<Validator> springValidators = new HashSet<>();


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> beans = this.beanValidator.validate(target);
        for (ConstraintViolation<Object> obj : beans) {
            errors.rejectValue(obj.getPropertyPath().toString(),
                    obj.getMessageTemplate(), obj.getMessage());
        }

        for (Validator v : this.springValidators) {
            v.validate(target, errors);
        }
    }

}
