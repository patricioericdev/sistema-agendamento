public static void main(String[] args) {
    Servico corte = new Servico("Corte", 30);
    Servico barba = new Servico("Barba", 20);

    System.out.println(corte);
    System.out.println(barba);
    System.out.println("Duração do corte: " + corte.getDuracaoMinutos() + " minutos");
}