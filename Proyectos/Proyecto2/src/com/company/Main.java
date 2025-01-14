package com.company;

public class Main {

    public static void main(String[] args) {

        Colta colta = new Colta();


        System.out.println(colta.multiplicar(3));

	int edad = -20;

        if (edad < 18 && edad > 0){
            System.out.println("Sos menor");
        }
        else if(edad < 0){
            System.out.println("Error");

        }
        else {
            System.out.println("Sos Mayor");
        }
    }

}

