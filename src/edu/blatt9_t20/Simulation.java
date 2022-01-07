package edu.blatt9_t20;

import java.util.ArrayList;

public class Simulation {

    Fabrik fabrik;
    ArrayList<Elf> arbeiter = new ArrayList<Elf>();

    public Simulation() {}

    public void start(long anzahl_geschenke, long anzahl_elfen) {
        /*
        Main Entrypoint.
        Creates the Factory acting as the primary Queue,
        as well as the Program Threads, and starts them.
        */
        this.fabrik = new Fabrik(anzahl_geschenke);

        // Create Threads
        this.init_elfen(anzahl_elfen);
        // Start every thread of List
        System.out.println("Starting " + String.valueOf(anzahl_elfen) + " Threads");
        this.start_elfen();
    }

    private void init_elfen(long anzahl_elfen) {
        /* Creates the specified number of 'Elf' threads */
        for (int i = 0; i < anzahl_elfen; i++) {
            arbeiter.add(
                    new Elf(this.fabrik)
            );
        }
    }

    public void start_elfen() {
        /* Start all Threads in the 'arbeiter' List */
        for (Elf elf : this.arbeiter) {
            Thread thread = new Thread(elf);
            thread.start();
        }
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.start(500, 60);
    }

}
