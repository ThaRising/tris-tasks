package edu.blatt9_t21;

import java.io.Serializable;

public class Geschenk implements Serializable {
    private final String bezeichnung;
    private final double preis;
    private final byte prioritaet;

    public Geschenk(String bezeichnung, double preis, byte prio) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.prioritaet = prio;
    }

    public String toString() {
        return this.__repr__();
    }

    private String __repr__() {
        return String.format(
                "Geschenk '%s' - Preis %f€; Priorität %d",
                this.bezeichnung, this.preis, this.prioritaet
        );
    }
}
