package com.yeik.app.metodosnumericos.metodos;

public class Biseccion {
    private String fx;
    private double x0;
    private double x1;
    private double xm;
    private double fx0;
    private double fxm;
    private double error;

    public Biseccion(String fx, double x0, double x1) {
        this.fx = fx;
        this.x0 = x0;
        this.x1 = x1;
        this.xm = (x0 + x1) / 2;
        this.error = 1;
    }

    public Biseccion(String fx, double x0, double x1, double error) {
        this.fx = fx;
        this.x0 = x0;
        this.x1 = x1;
        this.xm = (x0 + x1) / 2;
        this.error = error;
    }

    public Biseccion(String fx, double x0, double x1, double xm, double error) {
        this.fx = fx;
        this.x0 = x0;
        this.x1 = x1;
        this.xm = xm;
        this.error = error;
    }

    public Biseccion(String fx, double x0, double x1, double xm, double fx0, double fxm, double error) {
        this.fx = fx;
        this.x0 = x0;
        this.x1 = x1;
        this.xm = xm;
        this.fx0 = fx0;
        this.fxm = fxm;
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

    public double getXm() {
        return xm;
    }

    public double getError() {
        return error;
    }

    public double getFx0() {
        return fx0;
    }

    public double getFxm() {
        return fxm;
    }
}
