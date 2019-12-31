package com.springboot.annotationSource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Administrator
 * @description 自定义注解使用
 * @date 2019/12/31
 */
@MyAnnotationType
public class AnnotationTest {
    @MyAnnotationField
    private String name="Column";

    public static void main(String[] args) {

    }
    @MyAnnotationMethod
    public static void test(@MyAnnotationParameter String name){
        System.out.println("参数值---->"+name);
    }
}
