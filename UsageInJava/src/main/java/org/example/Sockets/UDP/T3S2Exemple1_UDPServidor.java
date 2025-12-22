package org.example.Sockets.UDP;

import java.net.*;
import java.io.*;

public class T3S2Exemple1_UDPServidor {
	public static void main(String args[]) {

		DatagramSocket dSocket = null;

		if (args.length < 1) { // PORT comes from the args
			System.out.println("Utilització: java T3S2Exemple1_UDPServidor <número de port>");
			System.exit(1);
		}

		try {
			int socket_no = Integer.valueOf(args[0]).intValue();
			dSocket = new DatagramSocket(socket_no);
			byte[] missatgeRebut;

			while (true) {
				missatgeRebut = new byte[1000];
				DatagramPacket dpRebut = new DatagramPacket(missatgeRebut, missatgeRebut.length);
				dSocket.receive(dpRebut);
				System.out.println("Rep del client: " + new String(dpRebut.getData()).trim());

				// ENVIE EL DATAGRAMA a dpRebut.getAddress() I AL PORT dpRebut.getPort() (DEL QUE HE REBUT)
				// ENVIE EL MATEIX MISSATGE QUE REP: dpRebut.getData()
				DatagramPacket dpResposta = new DatagramPacket(dpRebut.getData(), dpRebut.getLength(),
						dpRebut.getAddress(), dpRebut.getPort());
				dSocket.send(dpResposta);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (dSocket != null)
				dSocket.close();
		}
	}
}