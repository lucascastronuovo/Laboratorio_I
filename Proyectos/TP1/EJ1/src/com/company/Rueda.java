package com.company;

public class Rueda {
    private float radio;
    private String color;
    private String tipoMaterial;
    private boolean girando;


    public Rueda(float radio, String color, String tipoMaterial) {
        this.radio = radio;
        this.color = color;
        this.tipoMaterial = tipoMaterial;
        girando = false;
    }

   public void girar(){
        girando = true;
   }

   public float getRadio(){
        return radio;
   }

   public String getColor(){
        return color;
   }

   public String getTipoMaterial(){
        return  tipoMaterial;
   }

    public boolean getGirando(){
        return girando;
    }

}