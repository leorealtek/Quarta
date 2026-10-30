package Lists.Laurea;

public class Tesi {
    private final String titolo;

    public Tesi(String titolo) {
        this.titolo = titolo;
    }

    public String getTitolo() {
        return titolo;
    }

    @Override
    public String toString() {
        return "Tesi [Titolo: " + titolo + "]";
    }

}