package edu.blatt9_t20;

import java.util.ArrayList;

public class Simulation {

    Fabrik fabrik;
    ArrayList<Elf> arbeiter = new ArrayList<Elf>();

    public Simulation() {}

    public void start(int anzahl_geschenke, int anzahl_elfen) {
        this.fabrik = new Fabrik(anzahl_geschenke);
        this.init_elfen(anzahl_elfen);

        //Starte die Elfen Threads

        System.out.println("Arbeit erledigt");
    }

    private void init_elfen(int anzahl_elfen) {
        for (int i = 0; i < anzahl_elfen; i++) {
            arbeiter.add(new Elf(this.fabrik));
        }
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.start(500, 60);
    }

}
