package com.zyh.vote.model;

import com.zyh.vote.enmu.ModelType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsModel {

    ModelType value() default ModelType.User;

}
