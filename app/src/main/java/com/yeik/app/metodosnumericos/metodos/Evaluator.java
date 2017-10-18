package com.yeik.app.metodosnumericos.metodos;

import java.util.Stack;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Evaluator {
    private double Error;
    private int iter;

    public Evaluator(double Error) {
        this.Error = Error;
        iter = 20;
    }

    public Evaluator(double error, int iter) {
        Error = error;
        this.iter = iter;
    }

    public Stack<PuntoFijo> evaluate(PuntoFijo puntoFijo) {
        Stack<PuntoFijo> result = new Stack<>();
        int iter = 1;
        result.push(puntoFijo);
        double x0 = puntoFijo.getX0();
        double err = puntoFijo.getError();

        Expression evalgx = new ExpressionBuilder(puntoFijo.getGx()).variable("x").build();
//        double evalgx = new Expression(puntoFijo.getGx()).with("x", String.valueOf(x0)).eval().doubleValue();
//
//        if (Math.abs(evalgx) > 1) {
//            return null;
//        }

        while (err > Error && iter < this.iter) {
            double x = evalgx.setVariable("x", result.peek().getX0()).evaluate();
            err = Math.abs(x - result.peek().getX0());
            result.push(new PuntoFijo(puntoFijo.getFx(), puntoFijo.getGx(), x, err));
            iter++;
        }

        return result;
    }

    public Stack<Biseccion> evaluate(Biseccion biseccion) {
        Stack<Biseccion> result = new Stack<>();
        int iter = 1;
        result.push(biseccion);
        double x0, x1, xm;
        double err = biseccion.getError();

        Expression evalFx = new ExpressionBuilder(biseccion.getFx()).variable("x").build();

        while (err > Error && iter < this.iter) {
            x0 = result.peek().getX0();
            x1 = result.peek().getX1();
            xm = result.peek().getXm();
            double fx0 = evalFx.setVariable("x", x0).evaluate();
            double fxm = evalFx.setVariable("x", xm).evaluate();

            if (fx0 * fxm < 0) {
                x1 = xm;
            } else {
                x0 = xm;
            }

            xm = (x1 + x0) / 2;
            err = Math.abs(xm - result.peek().getXm());

            Biseccion newBi = new Biseccion(result.peek().getFx(), x0, x1, xm, fx0, fxm, err);

            result.push(newBi);
            iter++;
        }
        return result;
    }

    public Stack<FalsaPosicion> evaluate(FalsaPosicion falsaPosicion) {
        Stack<FalsaPosicion> result = new Stack<>();
        int iter = 1;
        result.push(falsaPosicion);
        double xu, xi, xr, fxu, fxi, fxr;
        double err = result.peek().getError();

        Expression expression = new ExpressionBuilder(falsaPosicion.getFx()).variable("x").build();

        while (err > Error && iter < this.iter) {
            xu = result.peek().getXu();
            xi = result.peek().getXi();
            fxu = expression.setVariable("x", xu).evaluate();
            fxi = expression.setVariable("x", xi).evaluate();
            xr = xu - (fxu * (xi - xu)) / (fxi - fxu);
            fxr = expression.setVariable("x", xr).evaluate();

            if (fxi * fxr < 0) {
                xu = xr;
            } else if (fxi * fxr > 0) {
                xi = xr;
            } else if (fxi * fxr == 0) {
                break;
            }

            err = (iter == 0) ? 1 : Math.abs(xr - result.peek().getXr());
            result.push(new FalsaPosicion(falsaPosicion.getFx(), xi, xu, err, xr));
            iter++;
        }

        return result;
    }

    public Stack<Newton> evaluate(Newton newton) {
        Stack<Newton> result = new Stack<>();
        int iter = 1;
        result.push(newton);
        double fx0, fdx0, x0, x;
        double err = result.peek().getErr();

        Expression fxExpression = new ExpressionBuilder(newton.getFx()).variable("x").build();
        Expression fdxExpression = new ExpressionBuilder(newton.getFdx()).variable("x").build();

        while (err > Error && iter < this.iter) {
            x0 = result.peek().getX0();
            fx0 = fxExpression.setVariable("x", x0).evaluate();
            fdx0 = fdxExpression.setVariable("x", x0).evaluate();
            x = x0 - fx0 / fdx0;
            err = Math.abs(x - result.peek().getX0());

            result.push(new Newton(newton.getFx(), newton.getFdx(), x, err));
            iter++;
        }

        return result;
    }

    public Stack<Euler> evaluate(Euler euler) {
        Stack<Euler> result = new Stack<>();
        int iter = 1;
        double err = euler.getError();
        result.push(euler);

        while (err > Error && iter < this.iter) {
            double x = result.peek().getX();
            double fx = new ExpressionBuilder(euler.getFx()).variable("x").build()
                    .setVariable("x", x).evaluate();
            double fdx = new ExpressionBuilder(euler.getFdx()).variable("x").build()
                    .setVariable("x", x).evaluate();
            double fddx = new ExpressionBuilder(euler.getFddx()).variable("x").build()
                    .setVariable("x", x).evaluate();
            double u = fx / fdx;
            double v = u * (fddx / fdx);
            double w = Math.sqrt(1 - 2 * v);
            double xk = x - (2 * u) / (1 + w);
            err = Math.abs(xk - x);

            result.push(new Euler(euler.getFx(), euler.getFdx(), euler.getFddx(), xk, err));
            iter++;
        }

        return result;
    }

    public Stack<Richmond> evaluate(Richmond richmond) {
        Stack<Richmond> result = new Stack<>();
        int iter = 1;
        double err = richmond.getError();
        result.push(richmond);

        while (err > Error && iter < this.iter) {
            double x = result.peek().getX();
            double fx = new ExpressionBuilder(richmond.getFx()).variable("x").build()
                    .setVariable("x", x).evaluate();
            double fdx = new ExpressionBuilder(richmond.getFdx()).variable("x").build()
                    .setVariable("x", x).evaluate();
            double fddx = new ExpressionBuilder(richmond.getFddx()).variable("x").build()
                    .setVariable("x", x).evaluate();
            double u = fx / fdx;
            double v = u * (fddx / fdx);
            double h = - (2 * u) / (2 - v);
            double xk = x + h;
            err = Math.abs(xk - x);

            result.push(new Richmond(richmond.getFx(), richmond.getFdx(), richmond.getFddx(), xk, err));
            iter++;
        }

        return result;
    }

    public Stack<Secante> evaluate(Secante secante) {
        Stack<Secante> result = new Stack<>();
        int iter = 1;
        result.push(secante);

        Expression expression = new ExpressionBuilder(result.peek().getFx()).variable("x").build();

        double err = result.peek().getError();

        while (err > Error && iter < this.iter) {
            double x0 = result.peek().getX0();
            double x1 = result.peek().getX1();
            double fx0 = expression.setVariable("x", x0).evaluate();
            double fx1 = expression.setVariable("x", x1).evaluate();
            double p = x1 - fx1 * (x1 - x0) / (fx1 - fx0);
            double fp = expression.setVariable("x", p).evaluate();
            x0 = x1;
            x1 = p;

            err = Math.abs(fp);
            result.push(new Secante(result.peek().getFx(), x0, x1, err));
            iter++;
        }

        return result;
    }

    public Stack<Muller> evaluate(Muller muller) {
        Stack<Muller> result = new Stack<>();
        int iter = 1;
        result.push(muller);

        Expression expression = new ExpressionBuilder(muller.getFx()).variable("x").build();

        double err = result.peek().getError();

        while (err > Error && iter < this.iter) {
            double x0 = result.peek().getX0();
            double x1 = result.peek().getX1();
            double x2 = result.peek().getX2();
            double fx0 = expression.setVariable("x", x0).evaluate();
            double fx1 = expression.setVariable("x", x1).evaluate();
            double fx2 = expression.setVariable("x", x2).evaluate();
            double h0 = x1 - x0;
            double h1 = x2 - x1;
            double d0 = (fx1 - fx0) / (x1 - x0);
            double d1 = (fx2 - fx1) / (x2 - x1);
            double a = (d1 - d0) / (h1 + h0);
            double b = a * h1 + d1;
            double r = Math.sqrt(Math.pow(b, 2) - (4 * a * fx2));
            double den;

            if (b + r > b - r) {
                den = b + r;
            } else {
                den = b - r;
            }

            double h = (-2 * fx2) / den;

            double x3 = x2 + h;

            err = Math.abs(h);

            result.push(new Muller(result.peek().getFx(), x1, x2, x3, err));
            iter++;
        }

        return result;
    }

    public void setError(double error) {
        Error = error;
    }
}
