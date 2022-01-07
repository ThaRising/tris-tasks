package com.company;

public enum Bearbeitungsschritt {
    ImLager(1),
    Produktpruefung(2),
    Einpacken(5),
    Qualitaetssicherung(2),
    ImVersandtLager(1),
    Abgefertigt(0);

    private final int bearbeitsZeit_inSec;

    Bearbeitungsschritt(int bearbeitsZeit_inSec){
        this.bearbeitsZeit_inSec = bearbeitsZeit_inSec;
    }

    public int getBearbeitsZeit_inSec(){
        return this.bearbeitsZeit_inSec;
    }
}
