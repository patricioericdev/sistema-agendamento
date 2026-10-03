import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Agenda {

    private LocalTime abertura;
    private LocalTime fechamento;
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Agenda(LocalTime abertura, LocalTime fechamento) {
        this.abertura = abertura;
        this.fechamento = fechamento;
    }

    private boolean temConflito(LocalDateTime inicio, LocalDateTime fim) {
        for (Agendamento a : agendamentos) {
            if (inicio.isBefore(a.getFim()) && fim.isAfter(a.getInicio())) {
                return true;
            }
        }
        return false;
    }

    private boolean dentroDoExpediente(LocalDateTime inicio, LocalDateTime fim) {
        return !inicio.toLocalTime().isBefore(abertura)
                && !fim.toLocalTime().isAfter(fechamento)
                && inicio.toLocalDate().equals(fim.toLocalDate());
    }

    public boolean agendar(String cliente, Servico servico, LocalDateTime inicio) {
        LocalDateTime fim = inicio.plusMinutes(servico.getDuracaoMinutos());

        if (!dentroDoExpediente(inicio, fim)) {
            return false;
        }
        if (temConflito(inicio, fim)) {
            return false;
        }

        agendamentos.add(new Agendamento(cliente, servico, inicio));
        return true;
    }

    public List<LocalTime> horariosLivres(LocalDate dia, Servico servico){
        List<LocalTime> livres = new ArrayList<>();
        LocalTime hora = abertura;

        while (!hora.isAfter(fechamento)){
            LocalDateTime inicio = LocalDateTime.of(dia, hora);
            LocalDateTime fim = inicio.plusMinutes(servico.getDuracaoMinutos());

            if (dentroDoExpediente(inicio, fim) && !temConflito(inicio, fim)){
                livres.add(hora);
            }

            hora = hora.plusMinutes(30);
        }

        return livres;
    }

    public void mostrarAgenda() {
        if (agendamentos.isEmpty()){
            System.out.println("Nenhum agendamento ainda.");
            return;
        }
        for (Agendamento a : agendamentos) {
            System.out.println(a);
        }
    }
}