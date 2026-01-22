package com.math;

public class App {
    public static void main(String[] a) {
        MathServer ms = new MathServer();
        try {
            ms.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
