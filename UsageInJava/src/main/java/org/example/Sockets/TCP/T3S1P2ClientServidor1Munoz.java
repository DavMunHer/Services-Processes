package org.example.Sockets.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Client {
    private static final int SERVER_PORT = 5000;

    public Client(String serverName) {
        try {
            Socket socket = new Socket(serverName, SERVER_PORT);
            InputStream is = socket.getInputStream();
            DataInputStream inputFlow = new DataInputStream(is);
            OutputStream os = socket.getOutputStream();
            DataOutputStream outputFlow = new DataOutputStream(os);

            for (int i = 0; i < 3; i++) {
                System.out.println("Servidor: " + inputFlow.readUTF());
                String msg = "";
                switch (i) {
                    case 0:
                        msg = "Hola";
                        break;
                    case 1:
                        msg = "¿Com estas?";
                        break;
                    case 2:
                        msg = "Adeu";
                        break;
                }
                System.out.println(msg);
                outputFlow.writeUTF(msg);
                if (msg.equals("Adeu")) {
                    System.out.println("Servidor: " + inputFlow.readUTF());
                    break;
                }
            }


            inputFlow.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Servidor {
    private static final int SERVER_PORT = 5000;

    public Servidor() {
        try {
            ServerSocket ss = new ServerSocket(SERVER_PORT);

            Socket clientSocket = ss.accept();
            OutputStream os = clientSocket.getOutputStream();
            InputStream is = clientSocket.getInputStream();
            DataInputStream inputFlow = new DataInputStream(is);
            DataOutputStream outputFlow = new DataOutputStream(os);
            outputFlow.writeUTF("Serveisc al client");

            String msg;
            while (true) {
                msg = inputFlow.readUTF();
                if (msg.equals("Adeu")) {
                    outputFlow.writeUTF("Fins despres");
                    break;
                }
                switch (msg) {
                    case "Hola":
                        outputFlow.writeUTF("Hola soc el servidor");
                        break;
                    case "¿Com estas?":
                        outputFlow.writeUTF("Molt be");
                        break;
                    default:
                        System.out.println("Cliente: " + msg);
                        outputFlow.writeUTF("No entenc el missatge");
                }
            }
            System.out.println("Tancant connexió...");
            outputFlow.close();
            inputFlow.close();
            clientSocket.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class T3S1P2ClientServidor1Munoz {
    public static void main(String[] args) {
        new Thread(() -> new Servidor()).start();
        new Thread(() -> new Client("localhost")).start();
    }
}
