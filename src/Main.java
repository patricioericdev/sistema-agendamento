import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda(LocalTime.of(8, 0), LocalTime.of(18, 0));

        List<Servico> servicos = new ArrayList<>();
        servicos.add(new Servico("Corte", 30));
        servicos.add(new Servico("Barba", 20));
        servicos.add(new Servico("Corte + Barba", 50));

        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1 -> listarServicos(servicos);
                case 2 -> verHorariosLivres(scanner, agenda, servicos);
                case 3 -> marcarHorario(scanner, agenda, servicos);
                case 4 -> agenda.mostrarAgenda();
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== SISTEMA DE AGENDAMENTO ===");
        System.out.println("1 - Ver serviços");
        System.out.println("2 - Ver horários livres");
        System.out.println("3 - Marcar horário");
        System.out.println("4 - Ver agenda");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static int lerInteiro(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void listarServicos(List<Servico> servicos) {
        System.out.println("Serviços:");
        for (int i = 0; i < servicos.size(); i++) {
            System.out.println((i + 1) + " - " + servicos.get(i));
        }
    }

    private static Servico escolherServico(Scanner scanner, List<Servico> servicos) {
        listarServicos(servicos);
        System.out.print("Escolha o serviço: ");
        int numero = lerInteiro(scanner);
        if (numero < 1 || numero > servicos.size()) {
            System.out.println("Serviço inválido.");
            return null;
        }
        return servicos.get(numero - 1);
    }

    private static LocalDate lerData(Scanner scanner) {
        System.out.print("Data (dd/mm/aaaa): ");
        try {
            return LocalDate.parse(scanner.nextLine().trim(), FORMATO_DATA);
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida.");
            return null;
        }
    }

    private static LocalTime lerHora(Scanner scanner) {
        System.out.print("Hora (hh:mm, ex: 09:30): ");
        try {
            return LocalTime.parse(scanner.nextLine().trim(), FORMATO_HORA);
        } catch (DateTimeParseException e) {
            System.out.println("Hora inválida.");
            return null;
        }
    }

    private static void verHorariosLivres(Scanner scanner, Agenda agenda, List<Servico> servicos) {
        Servico servico = escolherServico(scanner, servicos);
        if (servico == null) return;

        LocalDate dia = lerData(scanner);
        if (dia == null) return;

        List<LocalTime> livres = agenda.horariosLivres(dia, servico);
        if (livres.isEmpty()) {
            System.out.println("Nenhum horário livre nesse dia.");
        } else {
            System.out.println("Horários livres: " + livres);
        }
    }

    private static void marcarHorario(Scanner scanner, Agenda agenda, List<Servico> servicos) {
        Servico servico = escolherServico(scanner, servicos);
        if (servico == null) return;

        System.out.print("Nome do cliente: ");
        String cliente = scanner.nextLine().trim();
        if (cliente.isEmpty()) {
            System.out.println("O nome não pode ficar vazio.");
            return;
        }

        LocalDate dia = lerData(scanner);
        if (dia == null) return;

        LocalTime hora = lerHora(scanner);
        if (hora == null) return;

        boolean marcou = agenda.agendar(cliente, servico, LocalDateTime.of(dia, hora));
        if (marcou) {
            System.out.println("Horário marcado com sucesso!");
        } else {
            System.out.println("Horário indisponível. Veja os horários livres na opção 2.");
        }
    }
}