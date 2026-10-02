package RipassoVerifica;

public class FiltraConsumoEccessivo<T> implements Filtro<Dispositivo>{

    private int accettaPotenza;

    public FiltraConsumoEccessivo(int accettaPotenza) {
        this.accettaPotenza = accettaPotenza;
    }

    @Override
    public boolean accetta(Dispositivo elemento) {
        return elemento.getConsumoWatt() > accettaPotenza;
    }

}