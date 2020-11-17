package com.leofee.annotation;

import java.lang.annotation.*;

/**
 * @author leofee
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timer {
}
