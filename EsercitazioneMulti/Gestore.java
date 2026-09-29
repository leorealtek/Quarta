package EsercitazioneMulti;

public class Gestore {
    private Coda<Processabile>[] code;

    public Gestore(int numeroCode) {
        code = new Coda[numeroCode];
    }

    private boolean controllaID(int ID) {
        for (Coda<Processabile> coda : code) {
            if (coda != null && coda.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    private <T extends Processabile> boolean controllaTipo(T elemento, int ID) {
        if (!controllaID(ID)) {
            throw new IllegalArgumentException("ID non valido");
        }

        if (elemento instanceof code[ID].getTipo()) return true;
        
        return false;
    }

}