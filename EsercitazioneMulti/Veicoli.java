package EsercitazioneMulti;

public class Veicoli extends Generica {

    private String targa;
    private int kmAttuali;
    private String propietario;

    public Veicoli(String codiceIdentificativo, String targa, int kmAttuali, String propietario) {
        super(codiceIdentificativo);
        this.targa = targa;
        this.kmAttuali = kmAttuali;
        this.propietario = propietario;
    }

    @Override
    public String processa() {
        return targa + " (Proprietario: " + propietario + ") - KM: " + kmAttuali + " (Codice: " + super.toString() + ")";
    }

    @Override
    public String toString() {
        return "[VEICOLO] " + super.toString();
    }

}