package EsercitazioneMulti;

public class Generico implements Processabile {
    
    protected String codiceIdentificativo;

    public Generico(String codiceIdentificativo) {
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
        if (obj == null || !(obj instanceof Generico)) return false;

        Generico altra = (Generico) obj;

        return codiceIdentificativo.equals(altra.codiceIdentificativo);
    }

}