package org.example.Threads.Sychronized.WaitAndNotify.Barber;

public class Barber extends Thread {
    private ButacaBarber butacaBarber;

    public Barber(ButacaBarber b){
        this.butacaBarber = b;
    }

    @Override
    public void run() {
        try {
            this.sleep(1000);
            this.butacaBarber.callNextClient();
        } catch (InterruptedException e) {
        }

        while (true) {
            if (butacaBarber.isOccupied()) {
                try {
                    this.sleep(2000);
                    this.butacaBarber.freeButaca();

                } catch (InterruptedException e) {
                }
            }
        }
    }

}
