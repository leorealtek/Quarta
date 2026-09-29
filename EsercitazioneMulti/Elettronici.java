package EsercitazioneMulti;

public class Elettronici extends Generica{

    private String modello;
    private String tipoGuasto;

    public Elettronici(String codiceIdentificativo, String modello, String tipoGuasto) {
        super(codiceIdentificativo);
        this.modello = modello;
        this.tipoGuasto = tipoGuasto;
    }

    @Override
    public String processa() {
        return modello + " - Guasto: " + tipoGuasto + " (" + super.toString() + ")";
    }

    @Override
    public String toString() {
        return "[ELETTRONICO] " + super.toString();
    }

}