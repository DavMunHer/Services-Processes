package org.example.Threads.Sychronized.WaitAndNotify.Barber;

public class Client extends Thread {
    private ButacaBarber butacaBarber;
    private CadiresEspera cadiresEspera;
    private int clientId;

    public Client(ButacaBarber butacaBarber, CadiresEspera cadiresEspera, int clientId) {
        this.butacaBarber = butacaBarber;
        this.cadiresEspera = cadiresEspera;
        this.clientId = clientId;
    }

    @Override
    public void run() {
        if (!this.cadiresEspera.isFull()) {
            this.cadiresEspera.ocuparCadira(this.clientId);
//            this.butacaBarber.wakeUpBarber();
            butacaBarber.useButaca(this.clientId);
            this.cadiresEspera.lliberarCadira();
        } else {
            System.out.println("-> Client" + this.clientId + " SE VA DE LA BARBERIA");
        }
    }
}
