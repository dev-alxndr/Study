package io.alxndr.chapter1;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LambdaEx {
    public static void main(String[] args) {
        LambdaEx lambdaEx = new LambdaEx();
        lambdaEx.run();
    }

    public void run() {

        final int baseNumber = 10;

        /*
        * 이팩티브 final인 경우 아래 3가지 경우 모두 참조할 수 있다.
        * */

        // 로컬 클래스   쉐도잉 가능
        class LocalClass {
            void printBaseNumber() {
                System.out.println("baseNumber = " + baseNumber);
            }
        }

        // 익명 클래스   쉐도잉 가능
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("baseNumber = " + baseNumber);
            }
        };

        // 람다   쉐도잉 불가
        IntConsumer printInt = (i) -> System.out.println("i + baseNumber = " + i + baseNumber);

        printInt.accept(2);
    }
}
