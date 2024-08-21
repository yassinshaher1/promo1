package org.promo.data.test;

import java.util.ArrayList;
import java.util.List;

@interface Info {
    String author();
    String date();
    int version() default 1;
}

@Info(author = "Yassin Shaher", date = "2024-08-20")
public class ExampleClass {

    @Deprecated
    public void oldMethod() {
        System.out.println("This method is deprecated and should not be used.");
    }

    @SuppressWarnings("unchecked")
    public void newMethod() {
        List list = new ArrayList();
        list.add("Hello, World!");
        System.out.println(list.get(0));
    }

    @Override
    public String toString() {
        return "ExampleClass with annotations";
    }

    public static void main(String[] args) {
        ExampleClass example = new ExampleClass();
        example.oldMethod();
        example.newMethod();
        System.out.println(example.toString());
    }
}
