package Lists.Triage;

import Lists.SortedList;

public class Main {
    public static void main(String[] args) {
        SortedList<Persona> listaPazienti = new SortedList<>();
        Persona p1 = new Persona("Tippete", 17, false, true);
        Persona p2 = new Persona("Borto", 17, false, false);
        Persona p3 = new Persona("Precision", 50, false, false);
        Persona p4 = new Persona("Giacchetto", 42, true, true);
        
        listaPazienti.insertSorted(p1);
        listaPazienti.insertSorted(p2);
        listaPazienti.insertSorted(p3);
        listaPazienti.insertSorted(p4);

        listaPazienti.forEach(p -> System.out.println(p));
    }
}