package org.example.Threads.Sychronized;

class Persona extends Thread {
	private CompteMunoz c; // declare objecte compte c
	String nom;
	// constructor
	public Persona(String n, CompteMunoz c) {
		super(n);
		this.c = c;
	}
	// run
	public void run() {
		for (int x = 1; x <= 4; x++) {
			c.RetirarDiners(10, getName());
		}
	}
}