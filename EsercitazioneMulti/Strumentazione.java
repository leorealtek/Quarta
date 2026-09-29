package EsercitazioneMulti;

public class Strumentazione extends Generica {

    private String categoriaSrtumento;
    private int livelloPrecisione;
    private String numeroSerie;

    public Strumentazione(String codiceIdentificativo, String categoriaSrtumento, int livelloPrecisione, String numeroSerie) {
        super(codiceIdentificativo);
        this.categoriaSrtumento = categoriaSrtumento;
        this.livelloPrecisione = livelloPrecisione;
        this.numeroSerie = numeroSerie;
    }

    @Override
    public String processa() {
        return categoriaSrtumento + "[" + numeroSerie + "] - Precisione: " + livelloPrecisione + "(Codice: " + super.toString() + ")";
    }

    @Override
    public String toString() {
        return "[STRUMENTAZIONE] " + super.toString();
    }

}