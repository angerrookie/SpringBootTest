package com.springboot.annotation;

/**
 * @author Administrator
 * @description 自定义注解使用
 * @date 2019/12/31
 */
public class AnnotationTest {
    @MyAnnotation
    private String name="Column";

    public static void main(String[] args) {

        try {
            MyAnnotation myAnnotation = AnnotationTest.class.getDeclaredField("name").getAnnotation(MyAnnotation.class);
            System.out.println("----->"+myAnnotation.value());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
