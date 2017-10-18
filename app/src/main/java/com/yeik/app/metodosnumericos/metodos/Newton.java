package com.yeik.app.metodosnumericos.metodos;

public class Newton {
    private String fx;
    private String fdx;
    private double x0;
    private double err;

    public Newton(String fx, String fdx, double x0, double err) {
        this.fx = fx;
        this.fdx = fdx;
        this.x0 = x0;
        this.err = err;
    }

    public String getFx() {
        return fx;
    }

    public String getFdx() {
        return fdx;
    }

    public double getX0() {
        return x0;
    }

    public double getErr() {
        return err;
    }
}
