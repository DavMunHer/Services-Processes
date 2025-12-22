package org.example.Sockets.UDP;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class T3P4ClientSolucioMunoz {
	// REBEM EN args MISSATGE, HOST DESTINACI� I PORT
	public static void main(String args[]) {

		DatagramSocket dSocket = null;
        Scanner kb = null;
		// CONTROL ENTRADA ARGUMENTS
		if (args.length < 3) {
			System.out.println("Utilització: java T3S2Exemple1_UDPClient <nom del Host> <número de port>");
			System.exit(1);
		}
		try {

			// ENVIAMENT DEL DATAGRAMA
			dSocket = new DatagramSocket();
            kb = new Scanner(System.in);
            System.out.print("Introduzca el mensaje a enviar: ");
			byte[] missatgeEnviat = kb.nextLine().getBytes();
			InetAddress aHost = InetAddress.getByName(args[0]); // RECUPERE EL HOST DES DE L'ARGUMENT
			int serverPort = Integer.valueOf(args[1]).intValue(); // RECUPERE EL PORT DES DE L'ARGUMENT
			DatagramPacket dpEnviament = new DatagramPacket(missatgeEnviat, args[0].length(), aHost, serverPort); // DATAGRAMA A ENVIAR
			dSocket.send(dpEnviament); // ENVIE EL DATAGRAMA

			// RECEPCIÓ DEL DATAGRAMA
			byte[] missatgeRebut = new byte[1000];
			DatagramPacket dpResposta = new DatagramPacket(missatgeRebut, missatgeRebut.length);
			dSocket.receive(dpResposta); // REP EL DATAGRAMA
			System.out.println("Resposta: " + new String(dpResposta.getData()));

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (dSocket != null) // SI EL SOCKET EXISTEIX
				dSocket.close(); // TANQUE
		}
	}
}