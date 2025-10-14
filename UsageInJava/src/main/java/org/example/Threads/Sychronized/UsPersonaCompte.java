package org.example.Threads.Sychronized;

public class UsPersonaCompte {
	public static void main(String[] args) {
		CompteMunoz c = new CompteMunoz(40);
		Persona h1 = new Persona("Anna", c);
		Persona h2 = new Persona("Joan", c);

		h1.start();
		h2.start();
	}

}