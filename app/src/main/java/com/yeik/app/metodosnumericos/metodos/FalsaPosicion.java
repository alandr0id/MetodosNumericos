package com.yeik.app.metodosnumericos.metodos;

public class FalsaPosicion {
    private String fx;
    private double xi;
    private double xu;
    private double error;
    private double xr;

    public FalsaPosicion(String fx, double xi, double xu, double error) {
        this.fx = fx;
        this.xi = xi;
        this.xu = xu;
        this.error = error;
    }

    public FalsaPosicion(String fx, double xi, double xu, double error, double xr) {
        this.fx = fx;
        this.xi = xi;
        this.xu = xu;
        this.error = error;
        this.xr = xr;
    }

    public String getFx() {
        return fx;
    }

    public double getXi() {
        return xi;
    }

    public double getXu() {
        return xu;
    }

    public double getError() {
        return error;
    }

    public double getXr() {
        return xr;
    }
}
