package com.company;

public class Main {

    public static void main(String[] args) {


        Computadora computadora01 = new Computadora("INTEL CORE I7", 50);
        Robot robot1 = new Robot("ROJO", 15, 5, 10, computadora01);

        Computadora computadora02 = new Computadora("INTEL CORE I10", 50);
        Robot robot2 = new Robot("ROJO", 15, 5, 10, computadora02);




        System.out.println("Procesador Robot 01:" + robot1.getComputadora().getProcesador());
        System.out.println("Procesador Robot 02:" + robot2.getComputadora().getProcesador());



    }
}
