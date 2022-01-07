package edu.blatt9_t20;

import com.company.Bearbeitungsschritt;

public class Geschenk {
    Bearbeitungsschritt current_step;

    public Geschenk() {
        current_step = Bearbeitungsschritt.ImLager;
    }

    public void next_step() {
        switch (current_step) {
            case ImLager:
                this.current_step = Bearbeitungsschritt.Produktpruefung;
                break;
            case Produktpruefung:
                this.current_step = Bearbeitungsschritt.Einpacken;
                break;
            case Einpacken:
                this.current_step = Bearbeitungsschritt.Qualitaetssicherung;
                break;
            case Qualitaetssicherung:
                this.current_step = Bearbeitungsschritt.ImVersandtLager;
                break;
            case ImVersandtLager:
                this.current_step = Bearbeitungsschritt.Abgefertigt;
            default:
                break;
        }
    }
}
