package Lists.Merce;

import Lists.LinkedList;

public class Gestore {
    private Convoglio[] convogli;
    private LinkedList<Vagone> parcheggio;

    public Gestore() {
        this.parcheggio = new LinkedList<>();
    }

    public void aggiungiVagone(Vagone v, int nConvoglio) {
        if (nConvoglio < 0 || nConvoglio > 4) throw new ArrayIndexOutOfBoundsException("Scegli un numero tra 0 e 3");
        convogli[nConvoglio].aggiungiVagone(v);
    }

    public void creaConvoglio(Locomotiva testa, Locomotiva coda, int nConvoglio) {
        if (nConvoglio < 0 || nConvoglio > 4) throw new ArrayIndexOutOfBoundsException("Scegli un numero tra 0 e 3");

        if (convogli[nConvoglio] != null) {
            throw new IllegalArgumentException("Il convoglio è gia creato.");
        }
        else {
            convogli[nConvoglio] = new Convoglio(testa, coda);
        }
    }

    public void spostaVagoneDaConvoglioAParcheggio(Vagone v) {
        Convoglio convoglioTrovato = null;
        boolean trovato = false;
        for (Convoglio convoglio : convogli) {
            if (!convoglio.getLista().contains(v)) trovato = true;
            else convoglioTrovato = convoglio;
        }

        if (!trovato) throw new IllegalArgumentException("Il vagone non è in nessun parcheggio");

        convoglioTrovato.getLista().remove(v);
        parcheggio.add(v);
    }

    public void spostaVagoneDaParcheggioAConvoglio(Vagone v, int nConvoglio) {
        if (!parcheggio.contains(v)) throw new IllegalArgumentException("Il vagone non è nel parcheggio");
        if (nConvoglio < 0 || nConvoglio > 4) throw new ArrayIndexOutOfBoundsException("Scegli un numero tra 0 e 3");

        parcheggio.remove(v);
        convogli[nConvoglio].aggiungiVagone(v);
    }


    public void spostaVagoneDaConvoglioAConvoglio(Vagone v, int convoglio1, int convoglio2) {
        if (convoglio1 < 0 || convoglio1 > 4) throw new ArrayIndexOutOfBoundsException("Scegli un numero tra 0 e 3");
        if (convoglio2 < 0 || convoglio2 > 4) throw new ArrayIndexOutOfBoundsException("Scegli un numero tra 0 e 3");
        if (convoglio1 == convoglio2) return;

        if (!convogli[convoglio1].getLista().contains(v)) throw new IllegalArgumentException("Il convoglio non contiene il vagone");
        convogli[convoglio1].getLista().remove(v);
        convogli[convoglio2].aggiungiVagone(v);
    }

    public void contaNum() {
        int count;
        for (Convoglio convoglio : convogli) {
            count = 0;
            if (convoglio != null) {
                for (Locomotiva l : convoglio.getLista()) {
                    if (l instanceof Vagone v) {
                        count += v.getNum();
                    }
                }
            }
            System.out.println(count);
        }
    }
}