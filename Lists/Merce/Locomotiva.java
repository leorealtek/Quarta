package Lists.Merce;

public class Locomotiva {
    private final String nome;

    public Locomotiva(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] Nome: "  + nome;
    }

    
    
}