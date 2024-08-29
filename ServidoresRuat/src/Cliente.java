/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cuboz
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
       int port = 5002;
       int ci;
       int anio;
       String impuesto;
       int monto;
       
       try{
         Socket client = new Socket("localhost", port);  
         System.out.println("Conexion establecida con el banco");
         PrintStream toServer = new PrintStream(client.getOutputStream());
         BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

         while(true){
            int opcion = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Para consultar deudas presione 1: ");
            System.out.println("Para pagar deudas presione 2: ");
            opcion=sc.nextInt();
            switch(opcion){
                case 1:
                System.out.println("Ingrese el CI: ");
                ci = sc.nextInt();
                String cadenaCI= String.valueOf(ci);
                toServer.println("Deuda:"+cadenaCI);
                String respuesta = fromServer.readLine();
                System.out.println("Servidor dice: "+ respuesta);
                break;
                case 2:  
                System.out.println("Ingrese el CI: ");
                ci = sc.nextInt();
                System.out.println("Ingrese el anio: ");
                anio = sc.nextInt();
                System.out.println("Ingrese el impuesto: ");
                impuesto = sc.next();
                System.out.println("Ingrese el monto: ");
                monto = sc.nextInt();
                String cadenaCi=String.valueOf(ci);
                String cadenaAnio=String.valueOf(anio);
                String cadenaMonto=String.valueOf(monto);
                toServer.println("Pagar:"+cadenaCi+","+cadenaAnio+","+impuesto+","+cadenaMonto);
                String respuestaPagar = fromServer.readLine();
                String estadoPago=respuestaPagar.split(":")[1];
                if(estadoPago.equals("true")){
                    System.out.println("Respuesta del servidor: Si se pudo pagar la deuda");
                }
                else{
                    System.out.println("Respuesta del servidor: No se pudo pagar la deuda");
                }
                break;
            }
         }
       }
       catch(IOException ex){
           System.out.println(ex.getMessage());
       }
             
    }
    
}
