package com.leofee.annotation;

import java.lang.annotation.*;

/**
 * @author leofee
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogPrint {
}
