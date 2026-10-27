package Lists.Triage;

import java.util.Random;

public class Persona implements Comparable<Persona> {
    private String nome;
    private int eta;
    private boolean gravidanza;
    private boolean handicap;
    private Codice codice;

    public Persona(String nome, int eta, boolean gravidanza, boolean handicap) {
        this.nome = nome;
        this.eta = eta;
        this.gravidanza = gravidanza;
        this.handicap = handicap;
        setCodice();
    }

    private void setCodice() {
        if ( (eta < 14 || eta > 80) || gravidanza || handicap) {
            codice = Codice.AZZURRO;
        }
        else {
            Random random = new Random();
            int numeroCodice;
            if (Math.random() > 0.50d) numeroCodice = random.nextInt(2);
            else numeroCodice = random.nextInt(3, 5);
            codice = Codice.values()[numeroCodice];
        }
    }

    @Override
    public int compareTo(Persona other) {
        int gravitaThis = this.codice.getGravita();
        int gravitaOther = other.codice.getGravita();
        return (gravitaThis == gravitaOther) ? 0 : (gravitaThis < gravitaOther) ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " Età: " + eta + " Gravidanza: " + gravidanza + " Handicap: " + handicap
                + " Codice: " + codice;
    }
    
}