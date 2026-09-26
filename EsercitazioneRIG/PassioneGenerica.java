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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PassioneGenerica))
            return false;

        PassioneGenerica altra = (PassioneGenerica) obj;

        return titolo.equals(altra.titolo);
    }

}