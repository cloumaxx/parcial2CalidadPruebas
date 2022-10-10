/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgendaCronograma;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Carlos_Eduardo
 */
public class Main {
   
    public static Grafo listado=new Grafo();
    
    public void addNormal(Nodo aux){
        listado.addNodo(aux);
    }
    public static int menu(){
        int elel=0;
        Scanner teclado = new Scanner(System.in);
        System.out.println("*************************");
        System.out.println("**     Cronograma      **");
        System.out.println("*************************");
        System.out.println("* 1)Agregar actividad   *");
        System.out.println("* 2)Mostrar cronograma  *");
        System.out.println("* 3)Mostrar actividades *");
        System.out.println("* 4)Estado cronograma   *");
        System.out.println("* 5)Salir               *");
        System.out.println("************************");
        elel=teclado.nextInt();
        return elel;
    }
    public static void estadoCronograma(){
        int tiempoTotal=listado.calcularTiempo();
        int tiempoMaximo=listado.tiempoMaximo();
        int cantidad=listado.getCantNodos();
        System.out.println("*******************************");
        System.out.println("* Actividades registradas: "+cantidad+"   *");
        System.out.println("* Tiempo total:   "+tiempoTotal+" dias   *");
        System.out.println("* Tiempo maximo:   "+tiempoMaximo+" dias   *");
        System.out.println("*******************************\n");
    }
    public static void ejemplos() throws ParseException{
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date objDate =null;
        int distancia1=0;
        int distancia2=1;
        int distancia3=5;
        int distancia4=0;
        Nodo nuevo = new Nodo("Desayuno","1",df.parse("01/01/2021"),df.parse("20/10/2002"),distancia1," ");
        listado.addNodo(nuevo);
        
         Nodo nuevo2 = new Nodo("almuerzo","12",df.parse("03/01/2021"),df.parse("10/10/2002"),distancia2," ");
        listado.addNodo(nuevo2);
        Nodo nuevo3 = new Nodo("cena","13",df.parse("05/01/2021"),df.parse("08/10/2002"),distancia3," ");
        listado.addNodo(nuevo3);
        Nodo nuevo4 = new Nodo("postre cena","13",df.parse("10/01/2022"),df.parse("08/10/2002"),distancia4," ");
        listado.addNodo(nuevo4);
        listado.addArista(nuevo, nuevo2, distancia1);
        listado.addArista(nuevo2, nuevo3, distancia2);
        listado.addArista(nuevo3, nuevo4, distancia3);
    }
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ejemplos();
       

        int eleccion=0;
        while(eleccion!=5){
            eleccion=menu();
            switch(eleccion){
                case 1:{
                    listado.crearEvento();
                    break;
                }
                case 2:{
                    System.out.println(listado.getDetalleAristas());
                    break;
                }
                case 3:{
                    System.out.println(listado.getDetallesGRafo());
                    break;
                }
                case 4:{
                    estadoCronograma();
                    break;
                }
                case 5:{
                    System.out.println("Fin");
                }
                default:{
                    if(eleccion!=5){
                        System.out.println("*** Ingresa una opcion valida ***");
                    }
                }
            }
        }
        
     
    }   
    
}
