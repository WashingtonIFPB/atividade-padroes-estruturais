package br.edu.ifpb.padroes.estruturais.relatorios;

/**
 * Padrão PROXY (proxy de proteção / protection proxy).
 * Controla o acesso ao gerador de relatório real (que pode já estar
 * decorado com cache e/ou log). Antes de delegar a chamada, verifica se o
 * usuário está autenticado e se possui um papel autorizado (ADMIN ou
 * OPERADOR). Se o acesso for negado, nem chega a tocar no objeto real —
 * exatamente como a checagem "curto-circuitava" o método original antes de
 * qualquer outra lógica (cache, log, geração) ser executada.
 * Com isso, a regra de negócio de geração de relatório (e as decorações de
 * cache/log) ficam completamente livres de qualquer preocupação com
 * autenticação ou autorização.
 */
public class GeradorRelatorioAcessoProxy implements GeradorRelatorio {

    private final GeradorRelatorio geradorReal;
    private final Usuario usuario;

    public GeradorRelatorioAcessoProxy(GeradorRelatorio geradorReal, Usuario usuario) {
        this.geradorReal = geradorReal;
        this.usuario = usuario;
    }

    @Override
    public String gerar(String dadosBrutos) {
        if (!usuario.isAutenticado()) {
            System.out.println("[ERRO] Usuario nao autenticado tentou gerar relatorio.");
            return null;
        }

        if (!temPapelAutorizado()) {
            System.out.println("[ERRO] Usuario sem permissao para gerar relatorios: " + usuario.getNome());
            return null;
        }

        return geradorReal.gerar(dadosBrutos);
    }

    private boolean temPapelAutorizado() {
        String papel = usuario.getPapel();
        return "ADMIN".equals(papel) || "OPERADOR".equals(papel);
    }
}
