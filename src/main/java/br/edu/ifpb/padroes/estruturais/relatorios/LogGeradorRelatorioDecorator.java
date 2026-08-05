package br.edu.ifpb.padroes.estruturais.relatorios;

/**
 * Decorator concreto responsável apenas pela funcionalidade de LOG.
 * Registra uma mensagem antes de delegar a geração ao objeto decorado e
 * outra mensagem após a geração ser concluída com sucesso. Como este
 * decorator só é adicionado à cadeia quando o log é desejado (ver
 * {@link SistemaRelatorios}), não é necessário nenhum parâmetro booleano
 * nem "if" dentro dele: a própria presença (ou ausência) do decorator na
 * cadeia é que liga/desliga o comportamento.
 */
public class LogGeradorRelatorioDecorator extends GeradorRelatorioDecorator {

    private final String nomeUsuario;
    private final String tipo;

    public LogGeradorRelatorioDecorator(GeradorRelatorio gerador, String nomeUsuario, String tipo) {
        super(gerador);
        this.nomeUsuario = nomeUsuario;
        this.tipo = tipo;
    }

    @Override
    public String gerar(String dadosBrutos) {
        System.out.println("[LOG] Usuario " + nomeUsuario + " solicitou relatorio tipo " + tipo);

        String resultado = gerador.gerar(dadosBrutos);

        System.out.println("[LOG] Relatorio gerado com sucesso para " + nomeUsuario);

        return resultado;
    }
}
