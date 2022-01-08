package edu.blatt9_t21;

import java.io.*;
import java.util.ArrayList;

public class Verwaltung {
    public Verwaltung() {
        this.geschenke = new ArrayList<>();
    }

    private final ArrayList<Geschenk> geschenke;

    public void add_geschenk(String bezeichnung, double preis, byte prio) {
        Geschenk geschenk_to_add = new Geschenk(bezeichnung, preis, prio);
        this.geschenke.add(geschenk_to_add);
    }

    public void print_geschenk(int index) {
        try {
            Geschenk geschenk_to_print = this.geschenke.get(index);
            System.out.println(geschenk_to_print.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("No Object at Index '%d'%n", index);
        }
    }

    public void save(String relative_path) throws IOException {
        FileOutputStream fout_data = new FileOutputStream(relative_path);
        ObjectOutputStream fout = new ObjectOutputStream(fout_data);
        fout.writeInt(this.geschenke.size());
        for (Geschenk geschenk: this.geschenke) {
            fout.writeObject(geschenk);
        }
        fout.flush();
        fout.close();
    }

    public void load(String relative_path) throws IOException {
        FileInputStream fin_data = new FileInputStream(relative_path);
        ObjectInputStream fin = new ObjectInputStream(fin_data);
        try (fin) {
            int geschenke_count = fin.readInt();
            for (int _i = 0; _i < geschenke_count; _i++) {
                geschenke.add((Geschenk) fin.readObject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        this.geschenke.clear();
    }

    public Geschenk[] get_geschenke() {
        return this.geschenke.toArray(new Geschenk[0]);
    }

    public static void main(String[] args) throws IOException {
        Verwaltung verwaltung = new Verwaltung();
        // Populate with test objects
        String[][] test_geschenke = {
                {"Random", "13.233", "6"},
                {"Stuff", "17.233", "7"},
                {"Literally Dunno", "24.233", "9"}
        };
        for (String[] test_geschenk: test_geschenke) {
            verwaltung.add_geschenk(
                    test_geschenk[0],
                    Double.parseDouble(test_geschenk[1]),
                    Byte.parseByte(test_geschenk[2])
            );
        }
        // Try out all methods
        verwaltung.print_geschenk(1);
        Geschenk[] out_geschenke = verwaltung.get_geschenke();
        // Save, clear, make sure its empty and load again
        String filename = "savefile.save";
        verwaltung.save(filename);
        verwaltung.clear();
        verwaltung.print_geschenk(0);
        verwaltung.load(filename);
        verwaltung.print_geschenk(2);
    }
}
