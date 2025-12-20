package org.example.Sockets.TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
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
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                System.out.println("Client socket sent:" + dis.readUTF());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new T3S1P1ServidorEternMunoz();
    }
}
