package EsercitazioneRIG;

public class PassioneLetteraria extends PassioneGenerica implements Classificabile{

    private String nomeAutore;
    private int numeroPagine;
    private String ISBN;

    public PassioneLetteraria(String titolo, String nomeAutore, int numeroPagine, String ISBN) {
        super(titolo);
        this.nomeAutore = nomeAutore;
        this.numeroPagine = numeroPagine;
        this.ISBN = ISBN;
    }

    @Override
    public String nomeClassifica() {
        return nomeAutore + " " + super.toString() + " (ISBN: " + ISBN + ")";
    }

    public String getNomeAutore() {
        return nomeAutore;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public String toString() {
        return "[LIBRO] " + super.toString();
    }

}