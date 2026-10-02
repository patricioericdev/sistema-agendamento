import java.time.LocalDateTime;

public class Agendamento {

    private String cliente;
    private Servico servico;
    private LocalDateTime inicio;

    public Agendamento(String cliente, Servico servico, LocalDateTime inicio){
        this.cliente = cliente;
        this.servico = servico;
        this.inicio = inicio;
    }

    public String getCliente(){
        return cliente;
    }

    public Servico getServico(){
        return servico;
    }

    public LocalDateTime getInicio(){
        return inicio;
    }

    public LocalDateTime getFim(){
        return inicio.plusMinutes(servico.getDuracaoMinutos());
    }

    @Override
    public String toString() {
        return inicio.toLocalTime() + " ás " + getFim().toLocalTime()
                + " | " + cliente + " | " + servico.getNome();
    }
}
