package EsercitazioneMulti;

public class Generica implements Processabile {
    
    protected String codiceIdentificativo;

    public Generica(String codiceIdentificativo) {
        this.codiceIdentificativo = codiceIdentificativo;
    }

    @Override
    public String toString() {
        return "{" + codiceIdentificativo + "}";
    }

    public String getTitolo() {
        return codiceIdentificativo;
    }

    @Override
    public String processa() {
        return toString();
    }

}