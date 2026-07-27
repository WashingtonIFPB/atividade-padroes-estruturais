package br.edu.ifpb.padroes.estruturais.relatorios;

import java.util.HashMap;
import java.util.Map;

public class SistemaRelatorios {

    private GeradorRelatorio geradorPDF = new GeradorRelatorioPDF();
    private GeradorRelatorio geradorExcel = new GeradorRelatorioExcel();

    private Map<String, String> cache = new HashMap<>();

    public String gerarRelatorio(Usuario usuario, String dadosBrutos, String tipo,
                                  boolean usarCache, boolean gerarLog) {

        // ---- Controle de acesso misturado com a regra de negócio ----
        if (!usuario.isAutenticado()) {
            System.out.println("[ERRO] Usuario nao autenticado tentou gerar relatorio.");
            return null;
        }
        if (!usuario.getPapel().equals("ADMIN") && !usuario.getPapel().equals("OPERADOR")) {
            System.out.println("[ERRO] Usuario sem permissao para gerar relatorios: " + usuario.getNome());
            return null;
        }

        String chaveCache = tipo + ":" + dadosBrutos;

        // ---- Cache espalhado dentro do método de negócio ----
        if (usarCache && cache.containsKey(chaveCache)) {
            if (gerarLog) {
                System.out.println("[LOG] Retornando resultado do cache para " + usuario.getNome());
            }
            return cache.get(chaveCache);
        }

        if (gerarLog) {
            System.out.println("[LOG] Usuario " + usuario.getNome() + " solicitou relatorio tipo " + tipo);
        }

        String resultado;
        if (tipo.equals("PDF")) {
            resultado = geradorPDF.gerar(dadosBrutos);
        } else if (tipo.equals("EXCEL")) {
            resultado = geradorExcel.gerar(dadosBrutos);
        } else {
            throw new IllegalArgumentException("Tipo de relatorio desconhecido: " + tipo);
        }

        if (usarCache) {
            cache.put(chaveCache, resultado);
        }

        if (gerarLog) {
            System.out.println("[LOG] Relatorio gerado com sucesso para " + usuario.getNome());
        }

        return resultado;
    }
}
