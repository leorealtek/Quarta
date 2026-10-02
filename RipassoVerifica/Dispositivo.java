package RipassoVerifica;

public class Dispositivo {
    private int consumoWatt;

    public Dispositivo(int consumoWatt) {
        this.consumoWatt = consumoWatt;
    }

    public void accendi() {
        System.out.println("Dispositivo acceso.");
    }

    public int getConsumoWatt() {
        return consumoWatt;
    }

}