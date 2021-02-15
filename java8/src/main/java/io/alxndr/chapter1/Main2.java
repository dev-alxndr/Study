package io.alxndr.chapter1;

import java.util.Enumeration;
import java.util.function.*;

public class Main2 {
    public static void main(String[] args) {
        // case 1
        Plus10 plus10 = new Plus10();
        Integer apply = plus10.apply(10);
        System.out.println("apply = " + apply);

        // case 2
        Function<Integer, Integer> plus101 = (i) -> {
            return i + 10;
        };

        Integer apply1 = plus101.apply(1);
        System.out.println("apply1 = " + apply1);

        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        Integer apply2 = multiply2.apply(2);
        System.out.println("apply2 = " + apply2);

        // 조합
        /*compose(A) apply 전에 A를 실행하고
        * 결과값을 plus10 apply를 계산함
        *
        * > multiply2 실행 후 plus10 실행
        * = 14
        * */
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        Integer apply3 = multiply2AndPlus10.apply(2);
        System.out.println("apply3 = " + apply3);


        /* AndThen()
        * plus10 실행 후 multiply2 실행
        * = 24
        * */
        Integer apply4 = plus10.andThen(multiply2).apply(2);
        System.out.println("apply4 = " + apply4);

        /*
        Consumer
        * T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
        * void Accept(T t)
        * */
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        /*
        *   Supplier
        *   T 타입의 값을 제공하는 함수 인터페이스
        *   T get()
        * */
        Supplier<Integer> get10 = () -> 10;
        System.out.println("get10. = " + get10.get());

        /*
        * T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
        * - boolean test(T t)
        *
        * 함수 조합용 메소드
        *   - And
        *   - Or
        *   - Negate (not)
        * */
        Predicate<String> startsWithA = (s) -> s.startsWith("A");
        System.out.println("startsWithA.test(\"Alxndr\") = " + startsWithA.test("Alxndr"));
        Predicate<Integer> isEven = (num) -> num % 2 == 0;
        System.out.println("isEven.test(\"20\") = " + isEven.test(20));

        /*
        * UnaryOperator 입력값과 결과값의 타입이 같은 경우
        * */
        UnaryOperator<Integer> plus100 = (i) -> i + 100;
        System.out.println("plus100.apply(1) = " + plus100.apply(1));


    }
}
