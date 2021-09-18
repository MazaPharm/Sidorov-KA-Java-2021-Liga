package com.bad.code2.shapes;

import com.bad.code2.base.Shape3D;

public class Square implements Shape3D {

    private Double edgeSize;

    public Square(Double edgeSize) {
        this.edgeSize = edgeSize;
    }


    /**
     * @return Perimeter square
     */
    @Override
    public Double getValue() {
        return edgeSize * edgeSize;
    }

}
