package com.omniselling.common.util.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author xslong
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validate
{
    public abstract boolean required() default true;

    public abstract boolean trim() default true;

    public abstract int maxLength() default -1;

    public abstract int minLength() default -1;

    public abstract String regex() default "";
    
    public abstract String description() default "";
}
