package edu.blatt9_t20;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Fabrik {

    ConcurrentLinkedQueue<Geschenk> im_lager_queue = new ConcurrentLinkedQueue<Geschenk>();
    ConcurrentLinkedQueue<Geschenk> produktpruefung_queue = new ConcurrentLinkedQueue<Geschenk>();
    ConcurrentLinkedQueue<Geschenk> einpacken_queue = new ConcurrentLinkedQueue<Geschenk>();
    ConcurrentLinkedQueue<Geschenk> qualitaetssicherung_queue = new ConcurrentLinkedQueue<Geschenk>();
    ConcurrentLinkedQueue<Geschenk> im_versandtlager_queue = new ConcurrentLinkedQueue<Geschenk>();
    ArrayList<Geschenk> abgefertigt_list = new ArrayList<Geschenk>();

    // Using an AtomicInteger ensures that reading and writing this value
    // can be done in a thread-safe manner
    private final AtomicInteger in_bearbeitung = new AtomicInteger(0);
    long anzahl_geschenke;

    public Fabrik(long anzahl_geschenke) {
        this.anzahl_geschenke = anzahl_geschenke;
        this.init_lager_queue(anzahl_geschenke);
    }

    private void init_lager_queue(long anzahl_geschenke) {
        for (int i = 0; i < anzahl_geschenke; i++) {
            im_lager_queue.add(new Geschenk());
        }
    }

    public synchronized Geschenk get_arbeit() {
        in_bearbeitung.incrementAndGet();

        ArrayList<String> arr = new ArrayList<String>();
        if (im_lager_queue.size() > 0) {
            arr.add("lager");
        }
        if (produktpruefung_queue.size() > 0) {
            arr.add("pruefung");
        }
        if (einpacken_queue.size() > 0) {
            arr.add("einpacken");
        }
        if (qualitaetssicherung_queue.size() > 0) {
            arr.add("qualitaet");
        }
        if (im_versandtlager_queue.size() > 0) {
            arr.add("versandt");
        }

        Object[] available_cards = arr.toArray();
        java.util.Random random = new java.util.Random();
        if (available_cards.length <= 0) {
            // Decrement work-value and return null to signal that thread has finished
            in_bearbeitung.decrementAndGet();
            return null;
        }
        int random_computer_card = random.nextInt(available_cards.length);
        String next_work = (String) available_cards[random_computer_card];

        return switch (next_work) {
            case "lager" -> im_lager_queue.poll();
            case "pruefung" -> produktpruefung_queue.poll();
            case "einpacken" -> einpacken_queue.poll();
            case "qualitaet" -> qualitaetssicherung_queue.poll();
            case "versandt" -> im_versandtlager_queue.poll();
            default -> null;
        };
    }

    public synchronized void abstellen(@NotNull Geschenk geschenk) {
        this.print_lagerstand();

        switch (geschenk.current_step) {
            case ImLager -> this.im_lager_queue.add(geschenk);
            case Produktpruefung -> this.produktpruefung_queue.add(geschenk);
            case Einpacken -> this.einpacken_queue.add(geschenk);
            case Qualitaetssicherung -> this.qualitaetssicherung_queue.add(geschenk);
            case ImVersandtLager -> this.im_versandtlager_queue.add(geschenk);
            case Abgefertigt -> this.abgefertigt_list.add(geschenk);
            default -> {
            }
        }

        in_bearbeitung.decrementAndGet();
    }


    public void print_lagerstand() {
        System.out.println("###############################################");
        System.out.println("# In Bearbeitung:  " + in_bearbeitung.get());
        System.out.println("# Lager:           " + im_lager_queue.size());
        System.out.println("# Produktpruefung: " + produktpruefung_queue.size());
        System.out.println("# Einpacken:       " + einpacken_queue.size());
        System.out.println("# Qualitaetssicherung:       " + qualitaetssicherung_queue.size());
        System.out.println("# Versandtlager:       " + im_versandtlager_queue.size());
        System.out.println("# Abgefertigt:       " + abgefertigt_list.size());
        System.out.println("###############################################");
    }

}
