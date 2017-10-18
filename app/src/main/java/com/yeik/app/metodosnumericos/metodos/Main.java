package com.yeik.app.metodosnumericos.metodos;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String fx = "x^3-13*x-12";//"1/x-sin(x)/e^x+3*x"; //  3*x+sin(x)-e^x
        String fdx = "3*x^2-13";//"-1/x^2-(cos(x)-sin(x))/e^x+3"; // -e^x+cos(x)+3
        String fddx = "6x";//"2/x^3+2/e^x*cos(x)"; // -e^x-sin(x)
        String gx = "(sin(x)/e^x-(1/x))/3"; // (e^x-sin(x))/3
        double x0 = 2.5;//-2;
        double x1 = 5;//-1;

        Evaluator eva = new Evaluator(0.0001f, 30);

        System.out.println("----------------PUNTO FIJO---------------------");
        Stack<PuntoFijo> result = eva.evaluate(new PuntoFijo(fx, gx, x0, 1));
        if (result != null) {
            for (PuntoFijo f : result) {
                System.out.println(f.getX0() + "\t" + f.getError());
            }
        }

        System.out.println("----------------BISECCION---------------------");

        Stack<Biseccion> res = eva.evaluate(new Biseccion(fx, x0, x1));
        for (Biseccion f : res) {
            System.out.println(f.getXm() + "\t" + f.getX0() + "\t" + f.getX1() + "\t" + f.getError());
        }

        System.out.println("----------------FALSA POSICION---------------------");

        Stack<FalsaPosicion> resFalsa = eva.evaluate(new FalsaPosicion(fx, x0, x1, 1.0f));
        for (FalsaPosicion f : resFalsa) {
            System.out.println(f.getXr() + "\t" + f.getXi() + "\t" + f.getXu() + "\t" + f.getError());
        }

        System.out.println("----------------NEWTON---------------------");

        Stack<Newton> resultNewtons = eva.evaluate(new Newton(fx, fdx, x1, 1));
        for (Newton f : resultNewtons) {
            System.out.println(f.getX0() + "\t" + f.getErr());
        }

        System.out.println("----------------EULER---------------------");

        Stack<Euler> resultEuler = eva.evaluate(new Euler(fx, fdx, fddx, x1));
        for (Euler f : resultEuler) {
            System.out.println(f.getX() + "\t" + f.getError());
        }

        System.out.println("----------------RICHMOND---------------------");

        Stack<Richmond> resultRichmonds = eva.evaluate(new Richmond(fx, fdx, fddx, x1));
        for (Richmond f : resultRichmonds) {
            System.out.println(f.getX() + "\t" + f.getError());
        }

        System.out.println("----------------SECANTE---------------------");

        Stack<Secante> resultSecantes = eva.evaluate(new Secante(fx, x0, x1, 1));
        for (Secante f : resultSecantes) {
            System.out.println(f.getX0() + "\t" + f.getX1() + "\t" + f.getError());
        }

        System.out.println("----------------MULLER---------------------");

        Stack<Muller> resultMullers = eva.evaluate(new Muller(fx, x0, x1, 5));
        for (Muller f : resultMullers) {
            System.out.println(f.getX0() + "\t" + f.getX1() + "\t" + f.getX2() + "\t" + f.getError());
        }

//        wolfram();
    }

//    public static void wolfram() {
//        WAEngine engine = new WAEngine();
//
//        engine.setAppID("LGXK2U-KHAGE45JE8");
//        engine.addFormat("plaintext");
//
//        WAQuery query = engine.createQuery();
//
//        query.setInput("d/dx x^3-13*x-12");
//
//        try {
//            WAQueryResult queryResult = engine.performQuery(query);
//
//            if (queryResult.isError()) {
//                System.out.println("Error de consulta");
//            } else if (!queryResult.isSuccess()) {
//                System.out.println("No se entendio la consulta, revise por favor");
//            } else {
//                System.out.println("Su consulta se proceso correctamente");
//                for (WAPod pod : queryResult.getPods()) {
//                    if (!pod.isError()) {
//                        System.out.println(pod.getTitle());
//                        System.out.println("------------");
//                        for (WASubpod subpod : pod.getSubpods()) {
//                            for (Object element : subpod.getContents()) {
//                                System.out.println(((WAPlainText)element).getText());
//                                System.out.println("");
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (WAException e) {
//            e.printStackTrace();
//        }
//    }
}
