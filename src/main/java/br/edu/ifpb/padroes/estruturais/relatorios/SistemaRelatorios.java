package br.edu.ifpb.padroes.estruturais.relatorios;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe cliente do sistema de relatórios.
 *
 * Após a refatoração, esta classe NÃO contém mais nenhuma regra de negócio:
 * ela apenas escolhe o gerador base (PDF/Excel), decide QUAIS decorators
 * empilhar (cache? log?) e envolve tudo com o proxy de controle de acesso.
 * Toda a lógica de "o que" cada funcionalidade faz está isolada nas classes
 * de PROXY e DECORATOR correspondentes.
 *
 * Isso resolve o problema original: adicionar uma nova combinação de
 * cache/log não exige nenhum novo parâmetro nem "if" aqui — só depende de
 * quais decorators já existem, e eles podem ser combinados livremente.
 */
public class SistemaRelatorios {

    // Cache compartilhado entre chamadas, assim como no código original
    // (era um campo de instância de SistemaRelatorios).
    private final Map<String, String> cache = new HashMap<>();

    public String gerarRelatorio(Usuario usuario, String dadosBrutos, String tipo,
                                  boolean usarCache, boolean gerarLog) {

        GeradorRelatorio gerador = criarGeradorBase(tipo);

        if (gerarLog) {
            gerador = new LogGeradorRelatorioDecorator(gerador, usuario.getNome(), tipo);
        }

        if (usarCache) {
            gerador = new CacheGeradorRelatorioDecorator(gerador, cache, tipo, usuario.getNome());
        }

        GeradorRelatorio geradorProtegido = new GeradorRelatorioAcessoProxy(gerador, usuario);

        return geradorProtegido.gerar(dadosBrutos);
    }

    private GeradorRelatorio criarGeradorBase(String tipo) {
        if (tipo.equals("PDF")) {
            return new GeradorRelatorioPDF();
        } else if (tipo.equals("EXCEL")) {
            return new GeradorRelatorioExcel();
        } else {
            throw new IllegalArgumentException("Tipo de relatorio desconhecido: " + tipo);
        }
    }
}
