package EsercitazioneMulti;

public class Main {

    private static int ID = 0;

    public static int assegnaID() {
        return ++ID;
    }

    public static void main(String[] args) {
        Gestore g = new Gestore(4);

        Generico g1 = new Generico("KLHJFB2EG7");
        Generico g2 = new Generico("PFOUJ3HBE8");
        Generico g3 = new Generico("09834U59HN");
        Generico g4 = new Generico("JVBNR94UH4");
        Generico g5 = new Generico("O0VIH4R398");

        Veicolo v1 = new Veicolo("NDIIW3GD87", "FA757KM", 500, "Leo");
        Veicolo v2 = new Veicolo("UIO4F397EE", "KH452JQ", 350, "Leo");
        Veicolo v3 = new Veicolo("CKLJWEBFH2", "HG300KH", 100, "Tippete");
        Veicolo v4 = new Veicolo("FCIUJERGH3", "YT109VV", 444, "Tippete");
        Veicolo v5 = new Veicolo("2378YRBNDD", "SD764ER", 1000, "Nessuno");
        
        Elettronico e1 = new Elettronico("KJCB43IR23", "BASE", "Nessuno");
        Elettronico e2 = new Elettronico("SDF3R45TSS", "MEDIO", "Base");
        Elettronico e3 = new Elettronico("COIN3298Y4", "BASE", "Medio");
        Elettronico e4 = new Elettronico("CO2WIEHER3", "AVANZATO", "Avanzato");
        Elettronico e5 = new Elettronico("GOPQI456HJ", "MEDIO", "Rotto");

        Strumentazione s1 = new Strumentazione("CVIU34H3TR", "BASE", 1, "SE912876349721");
        Strumentazione s2 = new Strumentazione("OVJH459T08", "BASE", 1, "SE223895460586");
        Strumentazione s3 = new Strumentazione("FOP3U4GHRT", "MEDIO", 1, "SE320894723434");
        Strumentazione s4 = new Strumentazione("NDS9U2F234", "AVANZATO", 1, "SE234243242234");
        Strumentazione s5 = new Strumentazione("AOKMCO3MUL", "AVANZATO", 1, "SE190823475076");

        Coda<Generico> codaGenerica = new Coda<>(assegnaID());
        Coda<Veicolo> codaVeicoli = new Coda<>(assegnaID());
        Coda<Elettronico> codaElettronici = new Coda<>(assegnaID());
        Coda<Strumentazione> codaStrumentazione = new Coda<>(assegnaID());

        g.aggiungiCoda(codaGenerica);
        g.aggiungiCoda(codaVeicoli);
        g.aggiungiCoda(codaElettronici);
        g.aggiungiCoda(codaStrumentazione);

        g.trovaCodaDaID(codaVeicoli.getID()).aggiungiElemento(v1, 4);
    }

}