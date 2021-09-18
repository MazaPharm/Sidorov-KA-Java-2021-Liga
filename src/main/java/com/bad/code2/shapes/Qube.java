package com.bad.code2.shapes;

import com.bad.code2.base.Shape3D;

public class Qube implements Shape3D {

    private Double edgeSize;

    public Qube(Double s) {
        this.edgeSize = s;
    }


    /**
     * @return Cube volume
     */
    @Override
    public Double getValue() {
        return Math.pow(edgeSize, 3);
    }
}
