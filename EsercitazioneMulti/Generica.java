package EsercitazioneMulti;

public class Generica implements Processabile {
    
    protected String codiceIdentificativo;

    public Generica(String codiceIdentificativo) {
        this.codiceIdentificativo = codiceIdentificativo;
    }

    @Override
    public String toString() {
        return "(Codice: {" + codiceIdentificativo + "})";
    }

    public String getTitolo() {
        return codiceIdentificativo;
    }

    @Override
    public String processa() {
        return toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Generica)) return false;

        Generica altra = (Generica) obj;

        return codiceIdentificativo.equals(altra.codiceIdentificativo);
    }

}