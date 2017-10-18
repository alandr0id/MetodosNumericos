package com.yeik.app.metodosnumericos.metodos;

public class Secante {
    private String fx;
    private double x0;
    private double x1;
    private double error;

    public Secante(String fx, double x0, double x1, double error) {
        this.fx = fx;
        this.x0 = x0;
        this.x1 = x1;
        this.error = error;
    }

    public String getFx() {
        return fx;
    }

    public double getX0() {
        return x0;
    }

    public double getX1() {
        return x1;
    }

    public double getError() {
        return error;
    }
}
