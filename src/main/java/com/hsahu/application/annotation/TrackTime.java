package com.hsahu.application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * custom marker annotation indicates method needs to be tracked for it's execution time.
 * <p>
 * Creating custom annotation with AOP :-
 * 1. create annotation
 * 2. create aspect that intercept methods based on annotation
 * 3. implement advice for aspect
 * 4. annotate method to take effect of the aspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackTime {
}
