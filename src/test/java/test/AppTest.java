/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import AgendaCronograma.Grafo;
import AgendaCronograma.Nodo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Carlos_Eduardo
 */
public class AppTest {
    public static Grafo listado;

    public AppTest() {
    	
    }
    @BeforeClass
    public static void setUpClass() {
        listado = new Grafo();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // prueba que verifica que si se guarden objetos en el listado
    @Test
    public void testInsertarConcepto() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date objDate =null;

        Nodo nuevo = new Nodo("Desayuno","1",df.parse("01/01/2021"),df.parse("20/10/2002"),0," ");
        listado.addNodo(nuevo);
        
        Nodo nuevo2 = new Nodo("almuerzo","12",df.parse("03/01/2021"),df.parse("10/10/2002"),1," ");
        listado.addNodo(nuevo2);
        Nodo nuevo3 = new Nodo("cena","13",df.parse("05/01/2021"),df.parse("08/10/2002"),5," ");
        listado.addNodo(nuevo3);
        Nodo nuevo4 = new Nodo("postre cena","13",df.parse("10/01/2022"),df.parse("08/10/2002"),0," ");
        listado.addNodo(nuevo4);
        
        listado.addArista(nuevo, nuevo2, 0);
        int cantidadNodos=listado.getCantNodos();
        
        boolean existe = false;
        if (cantidadNodos==4) {
        	existe=true;
        }
        assertEquals(true, existe);
    }
    
    // prueba que verifica que se calcule el tiempo correcto
    @Test
    public void testCalcularTiempo() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date objDate =null;

        Nodo nuevo = new Nodo("Desayuno","1",df.parse("01/01/2021"),df.parse("20/10/2002"),0," ");
        listado.addNodo(nuevo);
        
        Nodo nuevo2 = new Nodo("almuerzo","12",df.parse("03/01/2021"),df.parse("10/10/2002"),1," ");
        listado.addNodo(nuevo2);
        Nodo nuevo3 = new Nodo("cena","13",df.parse("05/01/2021"),df.parse("08/10/2002"),5," ");
        listado.addNodo(nuevo3);
        Nodo nuevo4 = new Nodo("postre cena","13",df.parse("10/01/2022"),df.parse("08/10/2002"),0," ");
        listado.addNodo(nuevo4);
        
        listado.addArista(nuevo, nuevo2, 0);
        
        int tiempo = listado.calcularTiempo();
        assertEquals(12, tiempo);
    }

    // prueba negativa para comprobar la cantidad de nodosA
    @Test
    public void testCantNodos() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date objDate =null;

        Nodo nuevo = new Nodo("Desayuno","1",df.parse("01/01/2021"),df.parse("20/10/2002"),0," ");
        listado.addNodo(nuevo);
        
        Nodo nuevo2 = new Nodo("almuerzo","12",df.parse("03/01/2021"),df.parse("10/10/2002"),1," ");
        listado.addNodo(nuevo2);
        Nodo nuevo3 = new Nodo("cena","13",df.parse("05/01/2021"),df.parse("08/10/2002"),5," ");
        listado.addNodo(nuevo3);
        Nodo nuevo4 = new Nodo("postre cena","13",df.parse("10/01/2022"),df.parse("08/10/2002"),0," ");
        listado.addNodo(nuevo4);
        
        listado.addArista(nuevo, nuevo2, 0);
        
        int cantidad = listado.getCantNodos();
        assertNotEquals(4, cantidad);
    }
    

    // prueba que verifica que extraiga la arista correcta
    @Test
    public void testDetalleArista() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date objDate =null;

        Nodo nuevo = new Nodo("Desayuno","1",df.parse("01/01/2021"),df.parse("20/10/2002"),0," ");
        listado.addNodo(nuevo);
        
        Nodo nuevo2 = new Nodo("almuerzo","12",df.parse("03/01/2021"),df.parse("10/10/2002"),1," ");
        listado.addNodo(nuevo2);
        Nodo nuevo3 = new Nodo("cena","13",df.parse("05/01/2021"),df.parse("08/10/2002"),5," ");
        listado.addNodo(nuevo3);
        Nodo nuevo4 = new Nodo("postre cena","13",df.parse("10/01/2022"),df.parse("08/10/2002"),0," ");
        listado.addNodo(nuevo4);
        
        listado.addArista(nuevo, nuevo2, 0);
        
        String arista = listado.getDetalleAristas();
        assertNotEquals("Antes va: cena ,despues de: 0 dias, Sigue: desayuno", arista);
    }
    //
    @Test
    public void TestMesErroneo() throws ParseException{
    	SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
    	Date objDate = null;
    	boolean Sirve = true;
    	
    	try {
    		Nodo nuevo = new Nodo("Celebracion","12",df.parse("12/sep/2022"), df.parse("12/sep/2022"),1," ");
    		listado.addNodo(nuevo);
 
    	} catch(Exception e) {
    		assertEquals(true, Sirve);
    	}
    	
    	
    }
    
    //Prueba para saber que el programa no recibe cualquier parametro
    @Test
    public void TestFalseMesErroneo() throws ParseException{
    	SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
    	Date objDate = null;
    	boolean Sirvio = false;
    	
    	try {
    		Nodo nuevo = new Nodo("Celebracion","12",df.parse("hola"), df.parse("hola"),1," ");
    		listado.addNodo(nuevo);
 
    	} catch(Exception e) {
    	Sirvio = true;
    	}
    	assertEquals(true,Sirvio);
    	
    }
    
    //Prueba falsa que verifica que extraiga la arista correcta
    @Test
    public void testFallaCantNodos() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date objDate =null;
        boolean Con = false;

        try {
        	  Nodo nuevo = new Nodo("Desayuno","1",df.parse("No hay desayuno"),df.parse("No hay desayuno"),0," ");
              listado.addNodo(nuevo);
        } catch (Exception e) {
        	Con = true;
        }
        assertEquals(true,Con);
    }
}
