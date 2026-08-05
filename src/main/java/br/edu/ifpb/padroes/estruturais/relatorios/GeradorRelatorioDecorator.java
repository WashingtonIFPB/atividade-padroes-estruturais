package br.edu.ifpb.padroes.estruturais.relatorios;

/**
 * Classe base do padrão DECORATOR.
 * Todo decorator concreto (cache, log, ou qualquer funcionalidade futura)
 * estende esta classe, guarda uma referência para o {@link GeradorRelatorio}
 * decorado e delega a ele a chamada real de geração.
 * A grande vantagem: novas funcionalidades (ex: compressão, criptografia,
 * assinatura digital) podem ser adicionadas como novos decorators, e
 * combinadas livremente entre si, sem exigir novos parâmetros booleanos
 * nem novos "if" na classe cliente. A combinação é feita apenas empilhando
 * (ou não) o decorator desejado ao montar a cadeia de objetos.
 */
public abstract class GeradorRelatorioDecorator implements GeradorRelatorio {

    protected final GeradorRelatorio gerador;

    protected GeradorRelatorioDecorator(GeradorRelatorio gerador) {
        this.gerador = gerador;
    }
}
