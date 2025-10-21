package org.example.Threads.Sychronized.WaitAndNotify.Barber;

public class ButacaBarber {
    private boolean occupied;
    private int currentClientId;

    public ButacaBarber() {
        this.occupied = false;
    }

    public synchronized void useButaca(int clientId) {
        try {
            this.wait();
        } catch (InterruptedException e) {
        }
        this.occupied = true;
        this.currentClientId = clientId;
        System.out.println("-> Client" + clientId + " seu en la BUTACA");
    }

    public synchronized void freeButaca() {
        this.occupied = false;
        System.out.println("-> Barber ha tallat el cabell a " + this.currentClientId);
        this.notify();
    }

    public synchronized boolean isOccupied() {
        return this.occupied;
    }

    public synchronized void callNextClient() {
        this.notify();
    }

    public synchronized void wakeUpBarber() {
        this.notify();
    }

}
