package com.springboot.annotation;

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
        //获取类上的注解
        MyAnnotationType type = AnnotationTest.class.getAnnotation(MyAnnotationType.class);
        System.out.println("类注解---->"+type.value());
        //根据反射获取方法
        try {
            Method method = AnnotationTest.class.getDeclaredMethod("test", String.class);
            MyAnnotationMethod myAnnotationMethod = method.getAnnotation(MyAnnotationMethod.class);
            System.out.println("方法注解---->"+myAnnotationMethod.value());
            //获取方法上的所有参数注解  循环所有注解找到MyAnnotationParameter注解
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                for (Annotation annotation : parameterAnnotation) {
                    if (annotation instanceof MyAnnotationParameter){
                        System.out.println("参数上注解---->"+((MyAnnotationParameter)annotation).value());
                    }
                }
            }
            method.invoke(new AnnotationTest(),"改变参数");
            //获取属性上注解
            MyAnnotationField myAnnotationField = AnnotationTest.class.getDeclaredField("name").getAnnotation(MyAnnotationField.class);
            System.out.println("属性字段属性---->"+myAnnotationField.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @MyAnnotationMethod
    public static void test(@MyAnnotationParameter String name){
        System.out.println("参数值---->"+name);
    }
}
