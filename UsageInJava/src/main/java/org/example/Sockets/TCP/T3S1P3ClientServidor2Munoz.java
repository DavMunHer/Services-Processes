package org.example.Sockets.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Client2 {
    private static final int SERVER_PORT = 5000;

    public Client2(String serverName) {
        try {
            Socket socket = new Socket(serverName, SERVER_PORT);
            InputStream is = socket.getInputStream();
            DataInputStream inputFlow = new DataInputStream(is);
            OutputStream os = socket.getOutputStream();
            DataOutputStream outputFlow = new DataOutputStream(os);
            Scanner kb = new Scanner(System.in);

            while (true) {
                System.out.println("Servidor: " + inputFlow.readUTF());
                System.out.print("> ");
                String msg = kb.nextLine();
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

class Servidor2 {
    private static final int SERVER_PORT = 5000;

    public Servidor2() {
        try (ServerSocket ss = new ServerSocket(SERVER_PORT)) {

            System.out.println("Servidor a la escucha...");

            while (true) {
                Socket clientSocket = ss.accept();
                System.out.println("Cliente conectado");

                atenderCliente(clientSocket);

                System.out.println("Cliente desconectado");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void atenderCliente(Socket clientSocket) {
        try (
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())
        ) {
            out.writeUTF("Serveisc al client");

            while (true) {
                String msg = in.readUTF();

                if (msg.equals("Adeu")) {
                    out.writeUTF("Fins despres");
                    break;
                }

                switch (msg) {
                    case "Hola":
                        out.writeUTF("Hola soc el servidor");
                        break;
                    case "¿Com estas?":
                        out.writeUTF("Molt be");
                        break;
                    default:
                        System.out.println("Cliente: " + msg);
                        out.writeUTF("No entenc el missatge");
                }
            }

        } catch (IOException e) {
            // Cliente cerró conexión
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignored) {
            }
        }
    }
}


public class T3S1P3ClientServidor2Munoz {
    public static void main(String[] args) {
        new Thread(() -> new Servidor2()).start();
        new Thread(() -> new Client2("localhost")).start();
    }
}
