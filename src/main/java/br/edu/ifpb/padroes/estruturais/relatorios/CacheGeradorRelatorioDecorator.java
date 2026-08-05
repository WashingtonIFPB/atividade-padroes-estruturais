package br.edu.ifpb.padroes.estruturais.relatorios;

import java.util.Map;

/**
 * Decorator concreto responsável apenas pela funcionalidade de CACHE.
 * Antes de delegar a geração ao objeto decorado, verifica se já existe um
 * resultado para a mesma chave (tipo + dados brutos) no mapa de cache
 * (compartilhado e injetado pelo {@link SistemaRelatorios}, para que o
 * cache continue valendo entre chamadas diferentes, como no código original).
 * Se houver acerto de cache, o objeto decorado (por exemplo, o decorator
 * de log, ou o gerador propriamente dito) NUNCA é chamado — por isso, quando
 * o cache acerta, as mensagens de log de "solicitação" e "geração concluída"
 * não aparecem, e apenas a mensagem de acerto de cache é exibida. Esse é o
 * pequeno ajuste na ordem/quantidade de mensagens em relação ao código
 * original, e ele é intencional: antes, a mensagem de "veio do cache" só
 * aparecia se o parâmetro gerarLog fosse true; agora, cache e log são
 * responsabilidades independentes, então o próprio CacheDecorator informa
 * (sempre) quando o resultado veio do cache, independente de um LogDecorator
 * estar ou não presente na cadeia.
 */
public class CacheGeradorRelatorioDecorator extends GeradorRelatorioDecorator {

    private final Map<String, String> cache;
    private final String tipo;
    private final String nomeUsuario;

    public CacheGeradorRelatorioDecorator(GeradorRelatorio gerador,
                                           Map<String, String> cache,
                                           String tipo,
                                           String nomeUsuario) {
        super(gerador);
        this.cache = cache;
        this.tipo = tipo;
        this.nomeUsuario = nomeUsuario;
    }

    @Override
    public String gerar(String dadosBrutos) {
        String chave = tipo + ":" + dadosBrutos;

        if (cache.containsKey(chave)) {
            System.out.println("[LOG] Retornando resultado do cache para " + nomeUsuario);
            return cache.get(chave);
        }

        String resultado = gerador.gerar(dadosBrutos);
        cache.put(chave, resultado);
        return resultado;
    }
}
