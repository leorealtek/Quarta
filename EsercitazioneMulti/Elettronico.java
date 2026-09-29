package EsercitazioneMulti;

public class Elettronico extends Generico {

    private String modello;
    private String tipoGuasto;

    public Elettronico(String codiceIdentificativo, String modello, String tipoGuasto) {
        super(codiceIdentificativo);
        this.modello = modello;
        this.tipoGuasto = tipoGuasto;
    }

    @Override
    public String processa() {
        return modello + " - Guasto: " + tipoGuasto + " " + super.processa();
    }

    @Override
    public String toString() {
        return "[ELETTRONICO] " + super.toString();
    }

}