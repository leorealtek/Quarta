package EsercitazioneRIG;

public class Main {

    public static void main(String[] args) {
        Gestore gestore = new Gestore(4);

        Classifica<PassioneLetteraria> libri = new Classifica<>();
        Classifica<PassioneMusicale> dischi = new Classifica<>();
        Classifica<PassioneCinematografica> film = new Classifica<>();
        Classifica<PassioneGenerica> passioni = new Classifica<>();

        gestore.aggiungiClassifica(libri);
        gestore.aggiungiClassifica(dischi);
        gestore.aggiungiClassifica(film);
        gestore.aggiungiClassifica(passioni);

        PassioneLetteraria[] biblioteca = {
            new PassioneLetteraria("Dune", "Frank", 500, "001"),
            new PassioneLetteraria("1984", "George", 300, "002"),
            new PassioneLetteraria("Il piccolo principe", "Antoine", 120, "003"),
            new PassioneLetteraria("Il nome della rosa", "Umberto", 600, "004"),
            new PassioneLetteraria("Il signore degli anelli", "Tolkien", 1000, "005"),
            new PassioneLetteraria("Lo Hobbit", "Tolkien", 350, "006")
        };

        System.out.println("Ricerca nella classifica vuota: "+ gestore.cercaElemento(libri, biblioteca[0]));

        boolean rimosso = gestore.inserisciNuovoElemento(libri, biblioteca[0], 0);

        System.out.println("Elemento espulso al primo inserimento: "+ rimosso);

        for (int i = 1; i < 5; i++) {
            gestore.inserisciNuovoElemento(libri, biblioteca[i], i);
        }

        gestore.inserisciNuovoElemento(dischi,
            new PassioneMusicale("The Wall", "Pink Floyd", 26), 0);

        gestore.inserisciNuovoElemento(dischi,
            new PassioneMusicale("Thriller", "Michael Jackson", 9), 1);

        PassioneCinematografica duneFilm = new PassioneCinematografica(
                "Dune", 2021, "Denis Villeneuve", "Timothee Chalamet", "Rebecca Ferguson");

        gestore.inserisciNuovoElemento(film, duneFilm, 0);

        gestore.inserisciNuovoElemento(film,
            new PassioneCinematografica("Interstellar", 2014, "Christopher Nolan", "Matthew McConaughey", "Anne Hathaway"), 1);

        gestore.inserisciNuovoElemento(passioni, new PassioneGenerica("Scacchi"), 0);

        gestore.inserisciNuovoElemento(passioni, biblioteca[0], 2);

        System.out.println("\n--- Tutte le classifiche ---");
        System.out.println("Ordine: libri, dischi, film, passioni generiche");
        gestore.stampaClassifiche();

        System.out.println("\n--- Primi elementi, da stampare con toString() ---");
        gestore.primoElemento(libri);
        gestore.primoElemento(dischi);
        gestore.primoElemento(film);
        gestore.primoElemento(passioni);

        System.out.println("\n--- Ricerca per titolo ---");

        PassioneLetteraria stessaPassione = new PassioneLetteraria(
                "Dune", "Altro autore", 10, "ALTRO-ISBN");

        System.out.println("Oggetto diverso, stesso titolo: "+ gestore.cercaElemento(libri, stessaPassione));

        System.out.println("Titolo assente: " + 
        gestore.cercaElemento(libri, new PassioneLetteraria("Assente", "Autore", 100, "X")));

        System.out.println("Ricerca dopo un posto vuoto e con categoria diversa: " + gestore.cercaElemento(passioni, duneFilm));

        System.out.println("\n--- Inserimento nella top 5 piena ---");

        rimosso = gestore.inserisciNuovoElemento(libri, biblioteca[5], 0);

        System.out.println("Elemento espulso: "+ rimosso);

        System.out.println("Ricerca del vecchio quinto libro: "+ gestore.cercaElemento(libri, biblioteca[4]));

        libri.stampaClassifica();

        System.out.println("\n--- Rimozione di Dune ---");

        int posizione = gestore.cercaElemento(libri, biblioteca[0]);

        if (posizione != -1) {
            gestore.rimuoviElemento(libri, posizione - 1);
        }

        System.out.println("Ricerca dopo la rimozione: "+ gestore.cercaElemento(libri, biblioteca[0]));

        libri.stampaClassifica();

        System.out.println("\n--- Controlli degli errori ---");

        try {
            gestore.rimuoviElemento(libri, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Indice errato: " + e.getMessage());
        }

        Classifica<PassioneLetteraria> nonRegistrata = new Classifica<>();

        try {
            gestore.primoElemento(nonRegistrata);
        } catch (IllegalArgumentException e) {
            System.out.println("Classifica non registrata: " + e.getMessage());
        }
    }
}