package RipassoVerifica;

public class Main {
    public static void main(String[] args) {
        Computer c = new Computer(50);
        Cella<Computer> cella = new Cella<Computer>(c);
        Dispositivo d = cella.estraiElemento();
        d.accendi();


        Computer c2 = new Computer(50);
        Cella<Computer> cella2 = new Cella<Computer>(c2);
        Server s = new Server(100);
        Cella<Server> cella3 = new Cella<Server>(s);
        System.out.println(CalcolatoreDispositivi.sommaConsumi(cella2, cella3));
        Server s2 = new Server(50);
        Cella<Server> cella4 = new Cella<Server>(s2);
        Cella<Dispositivo> cella5 = new Cella<Dispositivo>(null);
        CalcolatoreDispositivi.trasferisci(cella4, cella5);


        Server s3 = new Server(250);
        FiltraConsumoEccessivo<Server> filtro = new FiltraConsumoEccessivo<>(200);
        StampaComputer<Server> stampa = new StampaComputer<>();
        GestorePipeline.applicaSeAccettato(s3, filtro, stampa);
    }
}