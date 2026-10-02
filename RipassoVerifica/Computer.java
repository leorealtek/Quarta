package RipassoVerifica;

public class Computer extends Dispositivo{

    public Computer(int consumoWatt) {
        super(consumoWatt);
    }

    @Override
    public void accendi() {
        System.out.println("Computer avviato.");
    }
    
}