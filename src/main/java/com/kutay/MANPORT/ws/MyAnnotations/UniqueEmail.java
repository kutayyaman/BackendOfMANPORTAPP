package com.kutay.MANPORT.ws.MyAnnotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD}) //Nerelerde kullanicagimizi belirtiyoruz.
@Retention(RUNTIME) // RUNTIME yani calisma aninda devreye girecegini soyluyoruz.
@Constraint(validatedBy = {UniqueEmailValidator.class})
public @interface UniqueEmail {
    String message() default "Mail must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
