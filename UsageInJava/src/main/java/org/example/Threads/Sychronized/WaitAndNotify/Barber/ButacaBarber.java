package org.example.Threads.Sychronized.WaitAndNotify.Barber;

public class ButacaBarber {
    private int currentClientId;
    private boolean isOccupied;

    public ButacaBarber() {
        this.isOccupied = false;
    }


    public synchronized void cutHair() {
        try {
            this.wait();
            this.isOccupied = true;
            this.wait(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("-> Barber ha tallat el cabell a " + this.currentClientId);
        this.isOccupied = false;
        this.notifyAll();
    }


    public synchronized void wakeUpBarber(int clientId) {
        while (isOccupied) {
            try {
                System.out.println("-> Client"+ clientId +" ha intentat seure en BUTACA pero no pot.");
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        this.currentClientId = clientId;
        System.out.println("-> Client" + this.currentClientId + " seu en la BUTACA");
        this.notify();
    }

}
