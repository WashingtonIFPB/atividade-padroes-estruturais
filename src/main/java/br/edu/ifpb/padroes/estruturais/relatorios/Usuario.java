package br.edu.ifpb.padroes.estruturais.relatorios;

public class Usuario {

    private String nome;
    private boolean autenticado;
    private String papel; // "ADMIN", "OPERADOR", "VISITANTE"

    public Usuario(String nome, boolean autenticado, String papel) {
        this.nome = nome;
        this.autenticado = autenticado;
        this.papel = papel;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public String getPapel() {
        return papel;
    }
}
