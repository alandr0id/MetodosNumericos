package com.yeik.app.metodosnumericos.metodos;

public class PuntoFijo {
    private String fx;
    private String gx;
    private double x0;
    private double error;

    public PuntoFijo(String fx, String gx, double x0, double error) {
        this.fx = fx;
        this.gx = gx;
        this.x0 = x0;
        this.error = error;
    }

    public String getFx() {
        return fx;
    }

    public String getGx() {
        return gx;
    }

    public double getX0() {
        return x0;
    }

    public double getError() {
        return error;
    }
}
