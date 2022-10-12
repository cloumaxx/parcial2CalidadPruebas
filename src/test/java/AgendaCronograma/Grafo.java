
package AgendaCronograma;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Carlos_Eduardo
 */
public class Grafo {
    
private ArrayList<Nodo> actividades;
    private ArrayList<Arista>conexiones;
    
    public void crearEvento(){
        String titulo="";
        String nombre="";
        String descripcion="";
        String fechaIni="";
        String diaI,mesI="";
        String añoI="2021",añoF="2021";
        String diaF,mesF="";
        String fechaFin="";
        int duracion=0;
        String responsable="";
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escribe el titulo del evento:");
            nombre = teclado.nextLine();
        System.out.println("De que trata el evento:");
            descripcion = teclado.nextLine();
        System.out.println("Escribe el encargado: ");
            responsable = teclado.nextLine();
        System.out.println("¿Cunato dias durara?");
            duracion = teclado.nextInt();
        System.out.println("¿Cuando inicia?");
        
        System.out.println("->Dia:");
            diaI = teclado.next();
        System.out.println("->Mes:");
            mesI = teclado.next();
        
        fechaIni = diaI+"/"+mesI+"/"+añoI;

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date testDateIni = null;
        try{
            testDateIni = df.parse(fechaIni);
            
        } catch (Exception e){ System.out.println("invalid format");}
        System.out.println("¿Cunado termina?");
        System.out.println("->Dia:");
            diaF = teclado.next();
        System.out.println("->Mes:");
            mesF = teclado.next();
        
        fechaFin = diaF+"/"+mesF+"/"+añoF;
        Date testDateFin = null;
        try{
            testDateFin = df.parse(fechaFin);
            
        } catch (Exception e){ System.out.println("invalid format");}
        
        Nodo nuevo = new Nodo(nombre,descripcion,testDateIni,testDateFin,duracion,responsable);
        System.out.println(nuevo.getInfoEvent()+"\n");
        addNodo(nuevo);
        addAristaEntre(nuevo, duracion);
        
    }
    public Grafo(){
        actividades=new ArrayList();
        conexiones=new ArrayList();
    }
    public void addNodo(Nodo aux){
        actividades.add(aux);
    }
    public void addArista(Nodo origen, Nodo destino, int  distancia){
        Arista arista = new Arista(distancia,origen,destino);
        conexiones.add(arista);
    }
    public void cargarDeTxt(){
        
    }
    public void Ordenar(){ 
       for (int i = 0; i < actividades.size()-1; i++) {
            for (int j = 0; j < actividades.size()-i-1; j++){  
                Date fecha1= actividades.get(j).getFechaIni();
                
                Date fecha2 = actividades.get(j+1).getFechaIni();
                int comparacion= fecha1.compareTo(fecha2);
                if(comparacion>0){
                    Nodo aux=actividades.get(j);
                    actividades.set(j, actividades.get(j+1));
                    actividades.set(j+1, aux);
                }
            }
        }
    }
    public int tiempoMaximo(){
        int maximo=0;   
        ArrayList<Arista>listaAux=new ArrayList<Arista>(conexiones);        
        
        ordenarAristas();
        int contador=0;
        for (int x = 0; x < listaAux.size()-1; x++) {
            Object auxil = listaAux.get(x).getOrigen().getFechaIni();
            Object aux2 =listaAux.get(x+1).getOrigen().getFechaIni();
            if(auxil.equals(aux2)){
                contador++;
            }
        }
        if(contador==0){
            for (int i = 0; i < listaAux.size(); i++) {
                maximo += listaAux.get(i).getOrigen().getDuracion();
            }
        }else{
        for (int i = 0; i < listaAux.size()-1; i++) {
            Object antes = listaAux.get(i).getOrigen().getFechaIni(); 
            Object siguiente = listaAux.get(i+1).getOrigen().getFechaIni(); 
            boolean terminado=false;
            for (int j = 0; j < listaAux.size(); j++) {
                if(antes==siguiente){
                   int duracion1=listaAux.get(i).getOrigen().getDuracion();
                   int duracion2=listaAux.get(j).getOrigen().getDuracion();
                   if(duracion1==duracion2){
                       maximo+=duracion1;
                       listaAux.remove(i);
                   }
                }
           
            }
            }
       
        boolean fin=false;
            for (int i = 0; i < listaAux.size()-1; i++) {
                Date antes = listaAux.get(i).getOrigen().getFechaIni(); 
                Date siguiente= listaAux.get(i+1).getOrigen().getFechaIni();
                int duracion1= listaAux.get(i).getOrigen().getDuracion();
                int comparacion = antes.compareTo(siguiente);   
                if(comparacion==0){
                    int duracion2= listaAux.get(i+1).getOrigen().getDuracion();
                    if(duracion2>duracion1){
                        listaAux.remove(i);
                        i=i-1;
                    }
                }
            }
            for (int x = 0; x < listaAux.size(); x++) {
            maximo += listaAux.get(x).getDistancia();
            //System.out.println("~~~ "+dateFormat.format(listaAux.get(x).getOrigen().getFechaIni())+" : "+listaAux.get(x).getDistancia()+" = "+listaAux.get(x).getOrigen().getNombre());
        }
    }
        return maximo;
    }
    public void caminoCorto(){
        SimpleDateFormat dateFormat = new 
               SimpleDateFormat ("yyyy-MM-dd");
       ArrayList<Nodo>camino=new ArrayList<Nodo>();
       Nodo inicio= conexiones.get(0).getOrigen();
       Nodo termina= conexiones.get(conexiones.size()-1).getOrigen();
       boolean fin=true;
       
       
       
       System.out.println(dateFormat.format(inicio.getFechaIni())+" : "+dateFormat.format(camino.get(0).getFechaIni()));
   }
    
    public void addAristaEntre(Nodo valAgregar,int duracion){
        
        SimpleDateFormat dateFormat = new 
                SimpleDateFormat ("yyyy-MM-dd");
        
        int i=0;
        boolean terminar=false;
        while ( i < actividades.size() && terminar==false) {
            Date fechaAux=actividades.get(i).getFechaIni();
           if(valAgregar.getFechaIni().equals(fechaAux)){
               int duracionPre=actividades.get(i-1).getDuracion();
                  //System.out.println("fechaAux: "+ dateFormat.format(fechaAux)+ ":: "+dateFormat.format(valAgregar.getFechaIni()));
                  try{
                    Object antes=conexiones.get(i).getOrigen().getNombre();
                    addArista(valAgregar, actividades.get(i+1),duracion);
                    addArista(actividades.get(i-1), valAgregar, duracionPre);
                }catch(Exception e){
                    System.out.println("La actividad va antes de la actividad principal");
                }
               terminar=true;
           }else{
                if(fechaAux.after(valAgregar.getFechaIni()) ){
                    int j=i;
                    Date fechaAux2=actividades.get(i-1).getFechaIni();

                    while(j<actividades.size() && terminar==false){
                        Date fechaDes=actividades.get(j).getFechaIni();
                        Date agregar=valAgregar.getFechaIni();
                        if(agregar.before(fechaDes)){    
                            Object antes=conexiones.get(i-1).getOrigen().getNombre();
                            if(antes.equals(actividades.get(i-1).getNombre())){
                               int duracionPre=actividades.get(j-1).getDuracion();
                               conexiones.remove(j-1);
                               addArista(actividades.get(j-1), valAgregar, duracionPre);
                               addArista(valAgregar,actividades.get(j), duracion);
                               // System.out.println(valAgregar.getInfoEvent());
                            }
                        terminar=true;
                        }
                        j++;                   
                    }
                }
           }
               i++;
        }
    }
    public String getDetallesGRafo(){
        Ordenar();
        String detalles= "";
        for (int i = 0; i < actividades.size(); i++) {
            detalles+=actividades.get(i).getInfoEvent()+"\n";
        }
        return detalles;
    }
    public String getFechasIni(){
        String detalles= "";
        SimpleDateFormat dateFormat = new 
                SimpleDateFormat ("yyyy-MM-dd");
        for (int i = 0; i < actividades.size(); i++) {
            detalles+= "->"+dateFormat.format(actividades.get(i).getFechaIni())+" ::  "+actividades.get(i).getNombre()+" -> "+actividades.get(i).getDuracion()+" dias \n";
        }
        return detalles;
    }
    public int getCantNodos(){
        int cantidad=actividades.size();
        return cantidad;
    }
   
 
    public void ordenarAristas(){
            
        for (int i = 0; i < conexiones.size()-1; i++) {
            for (int j = 0; j < conexiones.size()-i-1; j++){       
                Date fecha1 = conexiones.get(j).getOrigen().getFechaIni();
                Date fecha2 = conexiones.get(j+1).getOrigen().getFechaIni();
                int duracion1=conexiones.get(j).getOrigen().getDuracion();
                int duracion2 = conexiones.get(j+1).getOrigen().getDuracion();
                
                int comparacion= fecha1.compareTo(fecha2);
                if(comparacion>=0 ){
                    Arista aux=conexiones.get(j);
                        conexiones.set(j, conexiones.get(j+1));
                        conexiones.set(j+1, aux);
                       
                }
            }
        }
        for (int i = 0; i < conexiones.size(); i++) {
            for (int j = 0; j < conexiones.size()-1; j++){       
                int duracion1=conexiones.get(j).getOrigen().getDuracion();
                int duracion2 = conexiones.get(j+1).getOrigen().getDuracion();
                Date fecha1 = conexiones.get(j).getOrigen().getFechaIni();
                Date fecha2 = conexiones.get(j+1).getOrigen().getFechaIni();
                int comparacion= fecha1.compareTo(fecha2);
                
                if(duracion1>=duracion2 && comparacion==0){
                    Arista aux=conexiones.get(j);
                        conexiones.set(j, conexiones.get(j+1));
                        conexiones.set(j+1, aux);
                       
                }
            }
        }
    }
    public String getDetalleAristas(){
        String detalles = "";
        for(int i = 0; i < conexiones.size(); i++){
            detalles += conexiones.get(i).getDatos()+"\n";
        }
        return (detalles);
    }
   
    public int calcularTiempo(){
        int total=0;
        for (int j = 0; j < actividades.size(); j++) {
            total+= actividades.get(j).getDuracion();
        }
        return total;
    }
  
           
}

