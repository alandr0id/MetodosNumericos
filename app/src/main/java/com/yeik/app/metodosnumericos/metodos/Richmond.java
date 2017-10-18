package com.yeik.app.metodosnumericos.metodos;

public class Richmond {
    private String fx;
    private String fdx;
    private String fddx;
    private double x;
    private double error;

    public Richmond(String fx, String fdx, String fddx, double x) {
        this.fx = fx;
        this.fdx = fdx;
        this.fddx = fddx;
        this.x = x;
        error = 1;
    }

    public Richmond(String fx, String fdx, String fddx, double x, double error) {
        this.fx = fx;
        this.fdx = fdx;
        this.fddx = fddx;
        this.x = x;
        this.error = error;
    }

    public String getFx() {
        return fx;
    }

    public String getFdx() {
        return fdx;
    }

    public String getFddx() {
        return fddx;
    }

    public double getX() {
        return x;
    }

    public double getError() {
        return error;
    }
}