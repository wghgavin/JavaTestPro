package com.gavin.basicTest.LopkUpAlgorithmLearning.Annotation;

public @interface LookupMethod {
    String name() default "";
    int id();
}
