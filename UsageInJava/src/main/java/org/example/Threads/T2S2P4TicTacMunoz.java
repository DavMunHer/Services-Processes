package org.example.Threads;


public class T2S2P4TicTacMunoz extends Thread {
    private int nextTic = 1000;

    @Override
    public void run() {
        while (true) {
            try {
                if (this.getName().equals("TIC")) {
                    this.sleep(nextTic);
                    this.nextTic = 2000;
                } else {
                    this.sleep(2000);
                }
                System.out.print(this.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        T2S2P4TicTacMunoz t1 = new T2S2P4TicTacMunoz();
        t1.setName("TIC");
        T2S2P4TicTacMunoz t2 = new T2S2P4TicTacMunoz();
        t2.setName("TAC");

        t1.start();
        t2.start();

    }
}
