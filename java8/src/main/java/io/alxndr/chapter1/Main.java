package io.alxndr.chapter1;

public class Main {
    public static void main(String[] args) {

        // anonymous inner class
        RunSomething runSomething1 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("익명 내부클래스다");
            }
        };
        runSomething1.doIt();

        // 한줄
        RunSomething runSomething2= () -> System.out.println("Hello");
        runSomething2.doIt();

        // 여러줄
        RunSomething runSomething3 = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };
        runSomething3.doIt();
    }
}
