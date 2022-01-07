package edu.blatt9_t20;

public class Elf implements Runnable {

    Fabrik meine_fabrik;

    public Elf(Fabrik fabrik) {
        this.meine_fabrik = fabrik;
    }

    public void arbeit() {
        Geschenk arbeit_geschenk = meine_fabrik.get_arbeit();
        do {
            try {
                if (arbeit_geschenk != null) {
                    this.verarbeite_geschenk(arbeit_geschenk);
                    meine_fabrik.abstellen(arbeit_geschenk);
                    arbeit_geschenk = meine_fabrik.get_arbeit();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (arbeit_geschenk != null);
    }

    private void verarbeite_geschenk(Geschenk geschenk) throws InterruptedException {
        int verarbeitungsZeit_inSec = geschenk.current_step.getBearbeitsZeit_inSec();
        Thread.sleep(1000L * verarbeitungsZeit_inSec);
        geschenk.next_step();
    }

    @Override
    public void run() {
        System.out.println("Running Thread");
    }
}
