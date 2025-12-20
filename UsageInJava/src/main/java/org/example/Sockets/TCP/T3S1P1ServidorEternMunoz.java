package org.example.Sockets.TCP;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class T3S1P1ServidorEternMunoz {
    static final int PORT = 2500;

    public T3S1P1ServidorEternMunoz() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                OutputStream os = clientSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("Connection established successfully!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new T3S1P1ServidorEternMunoz();
    }
}
