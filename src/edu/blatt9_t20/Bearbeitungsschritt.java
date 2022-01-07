package edu.blatt9_t20;

public enum Bearbeitungsschritt {
    ImLager(1),
    Produktpruefung(2),
    Einpacken(5),
    Qualitaetssicherung(2),
    ImVersandtLager(1),
    Abgefertigt(0);

    private final int bearbeitungs_zeit_in_sec;

    Bearbeitungsschritt(int bearbeitungs_zeit_in_sec) {
        this.bearbeitungs_zeit_in_sec = bearbeitungs_zeit_in_sec;
    }

    public int get_bearbeitungs_zeit_in_sec() {
        return this.bearbeitungs_zeit_in_sec;
    }
}
