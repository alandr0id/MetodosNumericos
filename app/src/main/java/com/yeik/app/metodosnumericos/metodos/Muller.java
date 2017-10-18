package com.yeik.app.metodosnumericos.metodos;

public class Muller {
    private String fx;
    private double x0;
    private double x1;
    private double x2;
    private double error;

    public Muller(String fx, double x0, double x1, double x2) {
        this.fx = fx;
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
        error = 1;
    }

    public Muller(String fx, double x0, double x1, double x2, double error) {
        this.fx = fx;
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
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

    public double getX2() {
        return x2;
    }

    public double getError() {
        return error;
    }
}
