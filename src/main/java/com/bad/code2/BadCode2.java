package com.bad.code2;

public class BadCode2 {
    public static void main(String... args) {
        Shape3D qube = new Qube(10d);
        System.out.println("Qube volume: " + qube.getValue());

        Shape3D square = new Square( 5d);
        System.out.println("Square perimeter: " + square.getValue());
    }

}
