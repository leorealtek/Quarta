package Rischio;

/*
Interfaccia Verificabile.
Questo elemento non va modificato ma va utilizzato all'interno del codice
*/
interface Verificabile {
    double getValoreRischio();
    void riduci();
    String getTipo();
}

/*
Classe ContoBancario: Implementare il costruttore dopo aver aggiunto
gli attributi necessari, secondo la consegna ricevuta su classroom.
Inoltre, modificare la classe (compresa la sua dichiarazione) per implementare
i meccanismi necessari al funzionamento del metodo main, in conformità alle
istruzioni ricevute su classroom
*/
class ContoBancario implements Verificabile {
    private String iban;
    private double saldo;
    
    public ContoBancario(String iban, double saldo) {
        this.iban = iban;
        this.saldo = saldo;
    }

    @Override
    public double getValoreRischio() {
        return (saldo < 0) ? Math.abs(saldo) : 0;
    }

    @Override
    public void riduci() {
        if (saldo < 0) saldo /= 2;
    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName() + ": " + iban;
    }
    
}

/*
Classe ImmagineDigitale: Implementare il costruttore dopo aver aggiunto
gli attributi necessari, secondo la consegna ricevuta su classroom.
Inoltre, modificare la classe (compresa la sua dichiarazione) per implementare
i meccanismi necessari al funzionamento del metodo main, in conformità alle
istruzioni ricevute su classroom
*/
class ImmagineDigitale implements Verificabile {
    private String nomeFile;
    private double megaByte;
    
    public ImmagineDigitale(String nomeFile, double megaByte) {
        this.nomeFile = nomeFile;
        this.megaByte = megaByte;
    }

    @Override
    public double getValoreRischio() {
        return megaByte;
    }

    @Override
    public void riduci() {
        megaByte /= 2;
    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName() + ": " + nomeFile;
    }

}

/*
Classe PaccoSpedizione: Implementare il costruttore dopo aver aggiunto
gli attributi necessari, secondo la consegna ricevuta su classroom.
Inoltre, modificare la classe (compresa la sua dichiarazione) per implementare
i meccanismi necessari al funzionamento del metodo main, in conformità alle
istruzioni ricevute su classroom
*/
class PaccoSpedizione implements Verificabile {
    private String codiceTracciamento;
    private double pesoKg;
    
    public PaccoSpedizione(String codiceTracciamento, double pesoKg) {
        this.codiceTracciamento = codiceTracciamento;
        this.pesoKg = pesoKg;
    }

    @Override
    public double getValoreRischio() {
        return (pesoKg > 1.5) ? pesoKg : 0;
    }

    @Override
    public void riduci() {
        pesoKg *= 0.9;
    }

    @Override
    public String getTipo() {
        return getClass().getSimpleName() + ": " + codiceTracciamento;
    }

}

/*
Classe GestioneRischio. Implementare i metodi sotto indicati, secondo le istruzioni
ricevute su classroom
*/
class GestioneRischio {
    // Implementare con algoritmo RICORSIVO. Non modificare la firma
    public static double calcolaRischioTotale(Verificabile elementi[], int index) {
        double rischio;
        if (index == elementi.length) rischio = 0;
        else rischio = elementi[index].getValoreRischio() + calcolaRischioTotale(elementi, index + 1);
        return rischio;
    }
    
    // Implementare con algoritmo RICORSIVO. Non modificare la firma
    public static void ottimizzaTutti(Verificabile elementi[], int index) {
        if (index == elementi.length) return;
        elementi[index].riduci();
        ottimizzaTutti(elementi, index + 1);
    }
    
    // NON MODIFICARE!!
    public static void ispeziona(Verificabile elemento) {
        System.out.println("GENERICO ELEMENTO CON RISCHIO ASSOCIATO: " + elemento.getValoreRischio());
    }
    
    // NON MODIFICARE!!
    public static void ispeziona(ImmagineDigitale img) {
        System.out.println("IMMAGINE DIGITALE CON RISCHIO: " + img.getValoreRischio());
    }
}

/*
Questa classe contiene il metodo main e sono inseriti i test per il funzionamento del sistema.
Ad eccezione dell'ultima chiamata (metodo ispeziona), l'output deve essere il seguente:

--- VISUALIZZAZIONE SINGOLI RISCHI ---
[Conto corrente bancario IT1234]: 500.0
[Immagine Digitale foto_Vacanze.jpg]: 12.5
[Spedizione pacco TRK-9982]: 12.0

--- CALCOLO RISCHIO TOTALE ---
Rischio totale: 524.5

--- RIDUZIONE DI TUTTI I RISCHI ---
Nuovi rischi rivalutati: 
[Conto corrente bancario IT1234] -> 250.0
[Immagine Digitale foto_Vacanze.jpg] -> 6.25
[Spedizione pacco TRK-9982] -> 10.8

--- CALCOLO RISCHIO TOTALE RIVALUTATO ---
Rischio totale: 267.05

Prima di eseguire il codice, cercare di ipotizzare l'output corretto della chiamata
al metodo "ispeziona" (riga 131)
*/
public class DemoRischio {
    public static void main(String[] args) {
        Verificabile inventario[] = {
            new ContoBancario("IT1234", -500.0),
            new ImmagineDigitale("foto_Vacanze.jpg", 12.5),
            new PaccoSpedizione("TRK-9982", 12.0)
        };
        
        System.out.println("--- VISUALIZZAZIONE SINGOLI RISCHI ---");
        for (Verificabile v: inventario) {
            System.out.println(v.getTipo() + ": " + v.getValoreRischio());
        }
        
        System.out.println("\n--- CALCOLO RISCHIO TOTALE ---");
        double totale = GestioneRischio.calcolaRischioTotale(inventario, 0);
        System.out.println("Rischio totale: " + totale);
        
        System.out.println("\n--- RIDUZIONE DI TUTTI I RISCHI ---");
        GestioneRischio.ottimizzaTutti(inventario, 0);
        System.out.println("Nuovi rischi rivalutati: ");
        for (Verificabile v: inventario) {
            System.out.println(v.getTipo() + " -> " + v.getValoreRischio());
        }
        
        System.out.println("\n--- CALCOLO RISCHIO TOTALE RIVALUTATO ---");
        totale = GestioneRischio.calcolaRischioTotale(inventario, 0);
        System.out.println("Rischio totale: " + totale);
        
        System.out.println("\n--- VALUTAZIONE IMMAGINE ---");
        GestioneRischio.ispeziona(inventario[1]);
    }
}