package edu.blatt9_t20;

import org.jetbrains.annotations.NotNull;

public class Elf implements Runnable {

    Fabrik meine_fabrik;

    public Elf(Fabrik fabrik) {
        this.meine_fabrik = fabrik;
    }

    private void verarbeite_geschenk(@NotNull Geschenk geschenk) throws InterruptedException {
        int verarbeitungs_zeit_in_sec = geschenk.current_step.get_bearbeitungs_zeit_in_sec();
        Thread.sleep(100L * verarbeitungs_zeit_in_sec);
        geschenk.next_step();
    }

    @Override
    public void run() {
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
}
