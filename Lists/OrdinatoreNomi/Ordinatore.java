package Lists.OrdinatoreNomi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Lists.List;
import Lists.SortedList;

public class Ordinatore {
    private File file;

    public Ordinatore(String path) {
        this.file = new File(path);
    }

    public List<String> leggiFile() {
        SortedList<String> nomi = new SortedList<>();
        Scanner s = null;
        try { s = new Scanner(file); } catch (FileNotFoundException e) { e.printStackTrace(); }
        while (s.hasNext()) {
            nomi.addSorted(s.nextLine());
        }
        s.close();
        return nomi;
    }

    public void scriviFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Lists/OrdinatoreNomi/NomiOrdinati.txt"))) {
            List<String> nomiOrdinati = leggiFile();
            nomiOrdinati.forEach(nome -> writer.write(nome + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "OrdinatoreNomi/Nomi.txt";
        Ordinatore o = new Ordinatore(path);
        o.leggiFile().print();
        o.scriviFile();
    }
}