package com.gavin.basicTest.LookUpAlgorithmLearning.Annotation;

public @interface LookupMethod {
    String name() default "";
    int id();
}
