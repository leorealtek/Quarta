package RipassoVerifica;

public class Server extends Computer{

    public Server(int consumoWatt) {
        super(consumoWatt);
    }

    @Override
    public void accendi() {
        System.out.println("Server avviato.");
    }
    
}
