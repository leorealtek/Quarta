package RipassoVerifica;

public class StampaComputer<T> implements Elaboratore<Computer> {

    @Override
    public void elabora(Computer elemento) {
        elemento.accendi(); 
    }

}
