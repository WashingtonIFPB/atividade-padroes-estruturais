package br.edu.ifpb.padroes.estruturais.relatorios;

public class GeradorRelatorioExcel implements GeradorRelatorio {
    @Override
    public String gerar(String dadosBrutos) {
        // Simula processamento pesado de geração do relatório em Excel
        return "[XLSX] Relatorio gerado a partir de: " + dadosBrutos;
    }
}
