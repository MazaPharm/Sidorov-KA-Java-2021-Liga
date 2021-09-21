package com.bad.code2;

import com.bad.code2.base.Shape2D;
import com.bad.code2.base.Shape3D;
import com.bad.code2.shapes.Qube;
import com.bad.code2.shapes.Square;

public class BadCode2 {
    public static void main(String... args) {
        Shape3D qube = new Qube(10d);
        System.out.println("Qube volume: " + qube.getVolume());

        Shape2D square = new Square( 5d);
        System.out.println("Square perimeter: " + square.getPerimeter());

    }

}
