package br.edu.ifpb.padroes.estruturais.relatorios;

public class Main {
    public static void main(String[] args) {
        SistemaRelatorios sistema = new SistemaRelatorios();

        Usuario admin = new Usuario("Ana", true, "ADMIN");
        Usuario visitante = new Usuario("Bruno", true, "VISITANTE");
        Usuario naoLogado = new Usuario("Carla", false, "OPERADOR");

        System.out.println("--- Cenario 1: admin, com cache e log ---");
        System.out.println(sistema.gerarRelatorio(admin, "vendas-julho", "PDF", true, true));

        System.out.println("--- Cenario 2: mesma consulta (deve vir do cache) ---");
        System.out.println(sistema.gerarRelatorio(admin, "vendas-julho", "PDF", true, true));

        System.out.println("--- Cenario 3: admin, sem cache, com log, outro tipo ---");
        System.out.println(sistema.gerarRelatorio(admin, "vendas-agosto", "EXCEL", false, true));

        System.out.println("--- Cenario 4: visitante (sem permissao) ---");
        System.out.println(sistema.gerarRelatorio(visitante, "vendas-julho", "PDF", true, false));

        System.out.println("--- Cenario 5: usuario nao autenticado ---");
        System.out.println(sistema.gerarRelatorio(naoLogado, "vendas-julho", "PDF", true, true));
    }
}
