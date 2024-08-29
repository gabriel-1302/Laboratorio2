/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cuboz
 */


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Alcaldia {
    public static void main(String[] args) {
        int port = 6789;

        try {
            DatagramSocket socketUDP = new DatagramSocket(port);
            byte[] buffer = new byte[1000];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(receivedPacket);

                System.out.println("Datagrama recibido del host: " + receivedPacket.getAddress());
                System.out.println("Desde el puerto remoto: " + receivedPacket.getPort());

                String receivedData = new String(receivedPacket.getData());
                int CI = Integer.parseInt(receivedData.trim());
                System.out.println("CI consultado: " + CI);

                boolean hasObservations = hasObservations(CI);
                String response = String.valueOf(hasObservations);

                DatagramPacket responsePacket = new DatagramPacket(response.getBytes(), response.length(), receivedPacket.getAddress(), receivedPacket.getPort());
                socketUDP.send(responsePacket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasObservations(int CI) {
        return CI == 1234567;
    }
}
