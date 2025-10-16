package org.example.Threads.Sychronized.WaitAndNotify;

public class CompteMunoz {
    private int saldo;
    private int max_saldo;

    public CompteMunoz(int saldo, int max_saldo) {
        this.saldo = saldo;
        this.max_saldo = max_saldo;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public synchronized void ingresarSaldo(int saldoAIngresar, String nom) {
        while ((this.saldo + saldoAIngresar) > max_saldo) {
            System.out.println(nom + " ha intentado añadir " + saldoAIngresar +", esto excedería el saldo máximo! Saldo actual: "+ this.saldo);
            try {
                System.out.println("Esperando algún reintegramiento para poder añadir saldo a la cuenta...");
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        this.saldo += saldoAIngresar;
        System.out.println(nom + " ha ingresado " + saldoAIngresar + ". Saldo actual: " + this.saldo);
    }

    public synchronized void reintegrament(int saldoARestar, String nom) {
        while (saldoARestar > this.saldo) {
            System.out.println(nom + " no tiene saldo suficiente!! Intentando sacar " + saldoARestar + " teniendo un saldo de " + this.saldo);
            try {
                System.out.println("Esperando algún ingreso para poder sacar dinero...");
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        this.saldo -= saldoARestar;
        this.notify();
        System.out.println(nom + " ha restado "+ saldoARestar + ". Saldo actual: " + this.saldo);
    }



}
