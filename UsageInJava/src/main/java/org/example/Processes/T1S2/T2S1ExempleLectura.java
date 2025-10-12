package org.example.Processes.T1S2;

import java.io.*;

public class T2S1ExempleLectura{
	public static void main (String[] args){
	InputStreamReader in=new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader (in);
	String texto;
	
	try{
		System.out.println("Introdueix una cadena...");
		texto=br.readLine();
		System.out.println("Cadena escrita: "+texto);
		in.close();
		}catch (Exception e) {e.printStackTrace();}

	}//fi main
}//T2S1ExempleLectura
