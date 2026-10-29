package Lists.Triage;

import Lists.SortedList;

public class Main {
    public static void main(String[] args) {
        SortedList<Persona> listaPazienti = new SortedList<>();
        Persona p1 = new Persona("Tippete", 17, false, true);
        Persona p2 = new Persona("Borto", 17, false, false);
        Persona p3 = new Persona("Precision", 50, false, false);
        Persona p4 = new Persona("Giacchetto", 42, true, true);
        
        listaPazienti.addSorted(p1);
        listaPazienti.addSorted(p2);
        listaPazienti.addSorted(p3);
        listaPazienti.addSorted(p4);

        listaPazienti.printLine();
    }
}