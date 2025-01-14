package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
	    System.out.println("9NA CLASE");

	    Pimiento pimiento1 = new Pimiento();
	    pimiento1.setColor("Rojo");
	    pimiento1.setPeso(1000);

	    Pimiento pimiento2 = new Pimiento();
	    pimiento2.setColor("Amarillo");
	    pimiento2.setPeso(150);

        Pimiento pimiento3 = new Pimiento();
        pimiento3.setColor("Amarillo");
        pimiento3.setPeso(50);

        Pimiento pimiento4 = new Pimiento();
        pimiento4.setColor("Amarillo");
        pimiento4.setPeso(50);

	    if(pimiento1.compareTo(pimiento2) > 0)
        {
            System.out.println("Pimiento Rojo es mayor al Pimiento Amarillo");
        }else if(pimiento1.compareTo(pimiento2) < 0)
        {
            System.out.println("Pimiento Amarillo es mayor al Pimiento Rojo");
        }else{

            System.out.println("Los pimientos son iguales");
        }


	    //ArrayList
	    ArrayList pimientosArrayList = new ArrayList();

	    //respeta el orden de inserción
        //guarda nulos y objetos iguales
	    pimientosArrayList.add(pimiento1);
        pimientosArrayList.add(pimiento2);
        pimientosArrayList.add(pimiento3);
        pimientosArrayList.add(pimiento1);
        pimientosArrayList.add(pimiento4);

        //pimientosArrayList.sort(null);
        System.out.println("Acceder al elemento de la posicion 3 del ArrayList");
        Pimiento pimiento = (Pimiento) pimientosArrayList.get(3);
        System.out.println(pimiento.getColor() + " " + pimiento.getPeso());
        System.out.println();

        System.out.println("Recorriendo un ArrayList");
        System.out.println();
        for(Object o: pimientosArrayList){
            if(o instanceof Pimiento) {
                Pimiento p = (Pimiento) o;
                System.out.println(p.getColor() + " " + p.getPeso());
            }
        }
        System.out.println();

        //No respeta el orden de inserción
        //No guarda nulos ni objetos iguales
        HashSet pimientosSet = new HashSet();

        pimientosSet.add(pimiento1);
        pimientosSet.add(pimiento2);
        pimientosSet.add(pimiento3);
        pimientosSet.add(pimiento1);
        pimientosSet.add(pimiento4);

        System.out.println("Recorriendo un Set");
        System.out.println();
        for(Object o: pimientosSet){
            if(o instanceof Pimiento) {
                Pimiento p = (Pimiento) o;
                System.out.println(p.getColor() + " " + p.getPeso());
            }
        }
        System.out.println();


        //Los inserta ordenados --> Debemos implementar la interface Comparable
        //No guarda nulos ni objetos iguales
        TreeSet pimientosTreeSet = new TreeSet();

        pimientosTreeSet.add(pimiento1);
        pimientosTreeSet.add(pimiento2);
        pimientosTreeSet.add(pimiento3);
        pimientosTreeSet.add(pimiento1);
        pimientosTreeSet.add(pimiento4);

        System.out.println("Recorriendo un TreeSet");
        System.out.println();
        for(Object o: pimientosTreeSet){
            if(o instanceof Pimiento) {
                Pimiento p = (Pimiento) o;
                System.out.println(p.getColor() + " " + p.getPeso());
            }
        }
        System.out.println();


        //Guardan a traves de clave-valor
        HashMap pimientosHashMap = new HashMap();

        pimientosHashMap.put("ROJO",pimiento1);
        pimientosHashMap.put("AMARILLO",pimiento2);
        pimientosHashMap.put("AMARILLO-2",pimiento3);
        pimientosHashMap.put("ROJO-2",pimiento1);
        pimientosHashMap.put("AMARILLO-3",pimiento4);

        System.out.println("Acceder al elemento cuya clave es AMARILLO del HashMap");
        System.out.println();
        Pimiento p = (Pimiento) pimientosHashMap.get("AMARILLO");
        System.out.println(p.getColor() + " " + p.getPeso());
        System.out.println();

        System.out.println("Recorriendo un HashMap");
        System.out.println();
        for(Object o: pimientosHashMap.values()){
            if(o instanceof Pimiento) {
                p = (Pimiento) o;
                System.out.println(p.getColor() + " " + p.getPeso());
            }
        }
        System.out.println();


        int numero = 10;
        int numero2 = 0;


        //Atrapar excepciones
        try{
            int resultado = numero / numero2;
            System.out.println(resultado);

        }catch(Exception ex){
            //Se ejecuta cuando sucede una excepcion dentro del try
            System.out.println("Se produjo un error: " + ex.getMessage());

        }finally{
            //Se ejecuta siempre inclusive cuando se produce una excepcion dentro del catch
            System.out.println("Se ejecuta siempre");
        }

        System.out.println("El programa sigue ejecutando...");
        

    }
}
