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
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorBanco {

    public static void main(String[] args) {

        try {
            int port = 5002;
            ServerSocket server;
            
            server = new ServerSocket(port);
            System.out.println("Se inicio el server");
            
            Socket client;
            PrintStream toClient;
            client = server.accept();
            
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("Cliente se conecto");
            
            String recibido = fromClient.readLine();
            System.out.println("Cliente pidio: ");
            System.out.println(recibido);
            String respuesta = procesarPeticion(recibido);
            out.println(respuesta);
        } catch (IOException ex) {
            Logger.getLogger(ServidorBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static String procesarPeticion(String peticion){
        IRegistroDeuda ruat;
        String enviar[] = peticion.split(":");
        String ci = enviar[1];
        String respuesta="";
        try {
            ruat = (IRegistroDeuda) Naming.lookup("rmi://localhost/Ruat");
        if(enviar[0].equals("Deuda")){
            List<Deuda> deudas_list = ruat.buscar(ci);
            for(Deuda deudas : deudas_list){
                respuesta += deudas.getAnio() + "," + deudas.getImpuesto().toString() + "," + deudas.getMonto() + ";";
            }
            respuesta = respuesta.substring(0, respuesta.length() - 1);
            return "deudas:"+respuesta;
        }
        else if(enviar[0].equals("Pagar"){
            String[] valores=enviar[1].split(",");
            Deuda deuda=new Deuda(valores[0],Integer.valueOf(valores[1]),Impuesto.valueOf(valores[2]),Double.valueOf(valores[3]));
            if (ruat.pagar(deuda)) {
                return "resultado:true";
            }
            else {
                return "resultado:false";
            } 
        }
        } catch (NotBoundException ex) {
            Logger.getLogger(ServidorBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
