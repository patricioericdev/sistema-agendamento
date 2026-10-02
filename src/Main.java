public static void main(String[] args) {
    Servico corte = new Servico("Corte", 30);
    Servico barba = new Servico("Barba", 20);
    Servico combo = new Servico("Corte + Barba", 50);

    Agenda agenda = new Agenda(LocalTime.of(8, 0), LocalTime.of(18, 0));

    System.out.println(agenda.agendar("João", corte, LocalDateTime.of(2026, 10, 5, 9, 0)));
    System.out.println(agenda.agendar("Maria", barba, LocalDateTime.of(2026, 10, 5, 9, 30)));
    System.out.println(agenda.agendar("Pedro", corte, LocalDateTime.of(2026, 10, 5, 9, 15)));
    System.out.println(agenda.agendar("Ana", combo, LocalDateTime.of(2026, 10, 5, 17, 30)));

    System.out.println();
    agenda.mostrarAgenda();
}