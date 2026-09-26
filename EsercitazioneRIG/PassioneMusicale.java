package EsercitazioneRIG;

public class PassioneMusicale extends PassioneGenerica implements Classificabile{

    private String nomeArtista;
    private int numeroTracce;

    public PassioneMusicale(String titolo, String nomeArtista, int numeroTracce) {
        super(titolo);
        this.nomeArtista = nomeArtista;
        this.numeroTracce = numeroTracce;
    }

    @Override
    public String nomeClassifica() {
        return nomeArtista + " - " + super.toString().replace("\"", "");
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public int getNumeroTracce() {
        return numeroTracce;
    }

    @Override
    public String toString() {
        return "[DISCO]" + super.toString();
    }

}