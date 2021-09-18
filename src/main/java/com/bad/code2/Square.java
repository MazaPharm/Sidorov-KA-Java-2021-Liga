package com.bad.code2;

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
