package org.example.Threads.Sychronized;

class CompteMunoz {
    private int saldo;

    // constructor
    CompteMunoz(int s) {
        saldo = s; // inicialitza el saldo actual
    }// --------------------------------------

    // torna el saldo
    int getSaldo() {
        return this.saldo;
    }// --------------------------------

    // resta la quantitat al saldo
    void restar(int cantidad) {
        this.saldo -= cantidad;
    }// -----------------------------------

    // comprova que es puga retirar diners i els retira
    synchronized void RetirarDiners(int cant, String nom) {
        if (getSaldo() >= cant) {
            System.out.println(nom + " va a retirar saldo (actual és: " + getSaldo() + ")");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            restar(cant);
            System.out.println(nom + " retira => : " + cant + ". Saldo Actual: " + getSaldo() + "");
        } else {
            System.out.println(nom + " No pot retirar diners no hi ha saldo. Saldo Actual: " + getSaldo() + "");
        }

    }// fi retiras diners------------------------------

}// Compte
