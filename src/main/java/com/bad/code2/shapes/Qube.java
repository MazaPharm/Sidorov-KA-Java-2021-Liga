package com.bad.code2.shapes;

import com.bad.code2.base.Shape3D;

public class Qube implements Shape3D {


    private Double edgeSize;

    public Qube(Double edgeSize) {
        this.edgeSize = edgeSize;
    }

    @Override
    public Double getVolume() {
        return (Math.pow(edgeSize, 3));
    }
}
