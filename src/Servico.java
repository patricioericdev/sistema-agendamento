public class Servico {

    private String nome;
    private int duracaoMinutos;

    public Servico(String nome, int duracaoMinutos) {
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    @Override
    public String toString() {
        return nome + " (" + duracaoMinutos + " min)";
    }
}
