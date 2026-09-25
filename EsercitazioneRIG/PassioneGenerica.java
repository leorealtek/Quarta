package EsercitazioneRIG;

public class PassioneGenerica{
    
    protected String titolo;

    public PassioneGenerica(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String toString() {
        return "\"" + titolo + "\"";
    }

    public String getTitolo() {
        return titolo;
    }

}