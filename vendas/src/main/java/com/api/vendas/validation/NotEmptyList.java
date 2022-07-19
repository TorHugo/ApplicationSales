package com.api.vendas.validation;

import com.api.vendas.validation.constraint.NotEmpityListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmpityListValidator.class)
public @interface NotEmptyList {

    String message() default "A lista não pode ser vazia.";

    Class<?>[] grups () default {};
    Class<? extends Payload>[] payload() default {};

}
