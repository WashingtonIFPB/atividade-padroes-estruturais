package br.edu.ifpb.padroes.estruturais.relatorios;

public class GeradorRelatorioPDF implements GeradorRelatorio {
    @Override
    public String gerar(String dadosBrutos) {
        // Simula processamento pesado de geração do relatório em PDF
        return "[PDF] Relatorio gerado a partir de: " + dadosBrutos;
    }
}
