package io.alxndr.chapter1;

import java.util.function.Function;

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


    }
}
