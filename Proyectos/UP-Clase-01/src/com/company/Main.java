package com.company;

import java.util.Scanner;

public class Main {



    public static void main(String[] args)
    {
        System.out.println("Clase 1");

        //Tipos de datos primitivos: int/long/float/double/char
        //tipo nombreVariable;
        int edad = 17;
        long edad2 = 40;

        float peso = 10.2f;
        double peso2 = 30.5;

        char letra = 'B';
        String nombre = "Santiago";
        boolean mayorEdad = true;

        edad = 12;

        long edadTotal = edad + edad2;
        double edadTotalDecimal = edad + 0.5;

        nombre = "Santiago" + " Suarez";

        edad++;
        edad+=1;
        edad = edad + 1;


        System.out.println("Nombre: " + nombre + " edad:" + edad + " peso:" + peso);

        //Estructuras Condicionales

        if( edad > 18 ) {
            mayorEdad = true;
            System.out.println("Es mayor");
        }else if(edad == 18){
            System.out.println("Ahora sos mayor");
        }else{
            System.out.println("Es menor");
        }

        //Ingreso por teclado/consola
        Scanner entradaTeclado = new Scanner(System.in);
        letra = (char) entradaTeclado.next().charAt(0);


        if(letra == 'A') {
            System.out.println("Abrir programa");
        }else if(letra == 'S') {
            System.out.println("Salir del programa");
        }else if(letra == 'G') {
            System.out.println("Guardar");
        }else if(letra == 'P') {
            System.out.println("Pausar");
        }

        switch(letra) {
            case 'A':
                System.out.println("Abrir programa");
                break;
            case 'S':
                System.out.println("Salir del programa");
                break;
            case 'G':
                System.out.println("Guardar");
                break;
            case 'P':
                System.out.println("Pausar");
                break;
        }

        //Estructuras de repeticion

        //siempre que vamos a iterar toda la condición
        for(int i=0; i<10; i++) {
            System.out.println("for repetición:" + i);
        }


        //Usar cuando tenemos que iterar hasta encontrar un valor
        int i=0;
        boolean encontre = false;
        while(i < 10 && !encontre){

            if(i == 7)
                encontre = true;

            System.out.println("while repetición:" + i);


            i++;
        }

        int temp = sumar(10,20);

        System.out.println(temp);


    }

    static int sumar(int nro1, int nro2)
    {
        int resultado = nro1 + nro2;

        return resultado;
    }




}
