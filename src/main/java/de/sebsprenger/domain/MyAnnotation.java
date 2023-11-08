package de.sebsprenger.domain;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER
})
@Inherited
@Documented
public @interface MyAnnotation {

    String value() default "empty";

}
