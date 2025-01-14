package com.company;

public class Main {

    public static void main(String[] args) {

        //POLIFORMISMO y CASTEO
        Impresora imp = null;

        imp = new Impresora();
        imp.imprimirTexto("ejemplo"); //Polimorfismo

        //operador instanceof que sirve para preguntar de que clase es el
        //objeto que almacena adentro de la variable/referencia imp
        if(imp instanceof ImpresoraHP) {
                                 //casteo
            ImpresoraHP impHP = (ImpresoraHP) imp;
            //invocando un método propio de IMpresoraHP
            impHP.impresionPruebaHP();
        }

        imp = new ImpresoraXerox();
        imp.imprimirTexto("ejemplo"); //Polimorfismo

        //En variables primitivas el Operdor == compara igualdad
        int a1 = 1;
        int a2 = 1;
        if(a1 == a2)
        {
            System.out.println("los números son iguales");
        }


        //En objetos el Operador == compara identidad
        ImpresoraXerox b1 = new ImpresoraXerox();
        b1.setColor("NEGRA");

        ImpresoraXerox b2 = new ImpresoraXerox();
        b2.setColor("negra");


        //b1 = b2;
        //Comparar identidad
        if(b1 == b2)
        {
            System.out.println("son el mismo objeto");
        }

        //comparar dos impresoras
        if(b1.equals(b2))
        {
            System.out.println("las impresoras son iguales");
        }else{
            System.out.println("las impresoras son diferentes");
        }


        String c1 = new String("HOLA COMO ESTAS");
        String c2 = new String("HOLA COMO ESTAS");

        //operador identidad
        if(c1 == c2){
            System.out.println("los string son el mismo objeto");
        }

        //metodo para comparar igualdad
        if(c1.equals(c2)){
            System.out.println("los string son iguales");
        }

        String [] nombres = new String[3];
        nombres[0] = "JUAN";
        nombres[1] = "PEDRO";
        nombres[2] = "PEDRO";

        for(String nombre : nombres)
        {
            if(nombre.equals("PEDRO"))
                System.out.println("Encontre a PEDRO");
        }

        if(nombres[1].equals(nombres[2]))
            System.out.println("Son identicos");





    }
}
