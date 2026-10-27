package Lists.Triage;

public enum Codice {
    ROSSO(1), ARANCIONE(2), AZZURRO(3), VERDE(4), BIANCO(5);
    private int gravita;

    private Codice(int gravita) {
        this.gravita = gravita;
    }

    public int getGravita() {
        return gravita;
    }
}