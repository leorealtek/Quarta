package EsercitazioneRIG;

public class PassioneCinematografica extends PassioneGenerica implements Classificabile{

    private int annoUscita;
    private String nomeRegista;
    private String nomeAttore;
    private String nomeAttrice;

    public PassioneCinematografica(String titolo, int annoUscita, String nomeRegista, String nomeAttore, String nomeAttrice) {
        super(titolo);
        this.annoUscita = annoUscita;
        this.nomeRegista = nomeRegista;
        this.nomeAttore = nomeAttore;
        this.nomeAttrice = nomeAttrice;
    }

    @Override
    public String nomeClassifica() {
        return titolo + " di " + nomeRegista + " (starring " + nomeAttore + ", " + nomeAttrice + ")";
    }


    public int getAnnoUscita() {
        return annoUscita;
    }

    public String getNomeRegista() {
        return nomeRegista;
    }

    public String getNomeAttore() {
        return nomeAttore;
    }

    public String getNomeAttrice() {
        return nomeAttrice;
    }

    @Override
    public String toString() {
        return "[FILM] " + super.toString();
    }

}