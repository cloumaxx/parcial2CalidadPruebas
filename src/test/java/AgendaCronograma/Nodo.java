/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendaCronograma;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Carlos_Eduardo
 */
public class Nodo {
 
    private String nombre;
    private String descripcion;
    private Date fechaIni;
    private Date fechaFin;
    private int duracion;
    private String responsable;
    
    public Nodo(String name,String desc,Date inicio,Date fin,int tiempo,String encargado) {
        this.nombre = name;
        this.descripcion=desc;
        this.fechaIni=inicio;
        this.fechaFin=fin;
        this.duracion=tiempo;
        this.responsable=encargado;
    }
              
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    public String getInfoEvent(){
        String info="";
        SimpleDateFormat dateFormat = new 
                SimpleDateFormat ("yyyy-MM-dd");
        info="//////////////////////////////////////////\nNombre: " + nombre+"\n"+
             " * Descripcion: "+descripcion+"\n"+
             " * Responsable: "+responsable+"\n"+
             " * Fecha inicio: "+dateFormat.format(fechaIni)+"\n"+
             " * Fecha fin: "+dateFormat.format(fechaFin);
        return info;
    }
   
}
