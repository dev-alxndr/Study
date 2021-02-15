package io.alxndr.chapter1;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReference {

    public static void main(String[] args) {
        // 스태틱 메소드 참조
        UnaryOperator<String> hi = Greeting::hi;
        hi.apply("alxndr");

        // 특정객체의 인스턴스 메소드 참조
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        hello.apply("alxndr");

        // 생성자 참조 (기본)
        Supplier<Greeting> newGreeting = Greeting::new;
        Greeting greeting1 = newGreeting.get();

        // 생성자 참조
        Function<String, Greeting> functionGreeting = Greeting::new;
        Greeting alxndr = functionGreeting.apply("alxndr");
        System.out.println("alxndr.getName() = " + alxndr.getName());

        // 임의 객체의 인스턴스 메소드 참조
        String[] names = {"alxndr", "sooyeon", "kim"};
        Arrays.sort(names, String::compareToIgnoreCase);

        System.out.println("names = " + Arrays.toString(names));
    }
}
