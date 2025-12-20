package org.example.Threads.Sychronized.WaitAndNotify.Barber;

public class CadiresEspera {
    private int numeroCadires;
    private int cadiresOcupades;

    public CadiresEspera(int n) {
        this.numeroCadires = n;
        this.cadiresOcupades = 0;
    }

    public synchronized void ocuparCadira(int clientId) {
        this.cadiresOcupades++;
        System.out.println("-> Client" + clientId + " seu en una CADIRA d'espera. Total espera: " + cadiresOcupades);
    }

    public synchronized void lliberarCadira() {
        this.cadiresOcupades--;
    }

    public boolean isFull() {
        return cadiresOcupades == numeroCadires;
    }
}
