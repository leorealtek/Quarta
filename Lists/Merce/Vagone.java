package Lists.Merce;

public class Vagone extends Locomotiva{

    private final int num;

    public Vagone(String nome, int num) {
        super(nome);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return super.toString() + " Num: " + num;
    }

}