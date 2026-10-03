public static void main(String[] args) {
    Servico corte = new Servico("Corte", 30);
    Servico barba = new Servico("Barba", 20);

    Agenda agenda = new Agenda(LocalTime.of(8, 0), LocalTime.of(18, 0));
    LocalDate dia = LocalDate.of(2026, 10, 5);

    agenda.agendar("João", corte, LocalDateTime.of(dia, LocalTime.of(9, 0)));
    agenda.agendar("Maria", barba, LocalDateTime.of(dia, LocalTime.of(9, 30)));

    System.out.println("Agenda do dia:");
    agenda.mostrarAgenda();

    System.out.println();
    System.out.println("Horários livres para " + corte + ":");
    System.out.println(agenda.horariosLivres(dia, corte));
}