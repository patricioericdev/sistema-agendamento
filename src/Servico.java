public class Servico {

    private String nome;
    private int duracaoMinutos;

    public Servico(String nome, int duracaoMinutos) {
        if (duracaoMinutos <= 0) {
            throw new IllegalArgumentException("A duração deve ser maior que zero.");
        }
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
