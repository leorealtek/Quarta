package Lists.Laurea;

import Lists.List;

public class Assegnazioni {
    private List<Tesi> tesi;
    private List<Studente> laureandi;

    public Assegnazioni() {
        tesi = new List<>();
        laureandi = new List<>();;
    }

    public void aggiungiTesi(Tesi tesi) {
        this.tesi.add(tesi);
    }

    public void aggiungiStudente(Studente studente, Tesi tesi) {
        if (!this.tesi.contains(tesi)) this.tesi.add(tesi);
        else laureandi.add(studente);
        studente.setTesi(tesi);
    }

    public void laureato(Studente studente) {
        tesi.remove(studente.getTesi());
        laureandi.remove(studente);
    }

    public void liberaTesi(Tesi tesi) {
        
    }

    public int disponibili() {
        return -1;
    }

}