package edu.palermo.lab1;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Clase 3");


	    int edad = 0;
	    edad = 10;

	    //Declarar un objeto Robot llamado robot0
	    Robot robot0 = null;
	    //Creando es decir instanciando a robot0 como un Robot
	    robot0 = new Robot("ROJO",90,10);
	    //invocando un metodo/operación al objeto robot0
	    robot0.encender();

	    //Creando es decir instanciando un objeto
	    Robot robot1 = new Robot("BLANCO",100,20);
	    //Creando o instanciando otro objeto
	    Robot robot2 = new Robot("VERDE", 150, 15);
	    //Creando o instanciando otro objeto
		Robot robot3 = new Robot("VERDE", 150, 15);


		robot1.avanzar();
	    robot1.encender();
	    robot1.avanzar();
	    System.out.println( "Color:" + robot1.obtenerColor() );
        System.out.println( "Peso: " + robot1.obtenerPeso() + "kg");
        System.out.println( "Cantidad Libertades: " + robot1.obtenerCantidadLibertades());
        System.out.println( "Encendido: " + robot1.estaEncendido() );
        System.out.println( "Porcentaje Bateria: " + robot1.obtenerPorcentajeBateria() + "%");
    }
}
