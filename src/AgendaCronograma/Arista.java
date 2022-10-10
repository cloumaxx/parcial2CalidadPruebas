/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.text.SimpleDateFormat;

/**
 *
 * @author Carlos_Eduardo
 */
public class Arista {
        private int duracion;
     private Nodo evenantes;
     private Nodo eventDes;
    public Arista(int num,Nodo aux,Nodo aux2) {
        this.duracion=num;
        this.evenantes= aux;
        this.eventDes=aux2;
    }

    public int getDistancia() {
        return duracion;
    }

    public void setDistancia(int distancia) {
        this.duracion = distancia;
    }

    public Nodo getOrigen() {
        return evenantes;
    }

    public void setOrigen(Nodo origen) {
        this.evenantes = origen;
    }

    public Nodo getDestino() {
        return eventDes;
    }

    public void setDestino(Nodo destino) {
        this.eventDes = destino;
    }

    
    public String getDatos(){
        SimpleDateFormat dateFormat = new 
                SimpleDateFormat ("yyyy-MM-dd");
        return ("Antes va: "+this.evenantes.getNombre() +" ,despues de: "+this.duracion+" dias, Sigue: "+
                this.eventDes.getNombre());
    }

    
     
}
