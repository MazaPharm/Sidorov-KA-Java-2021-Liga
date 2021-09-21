package com.bad.code2.shapes;

import com.bad.code2.base.Shape2D;

public class Square implements Shape2D {

    private Double edgeSize;

    public Square(Double edgeSize) {
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getPerimeter() {
        return edgeSize*edgeSize;
    }
}
