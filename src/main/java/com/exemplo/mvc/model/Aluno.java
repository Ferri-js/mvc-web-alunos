// src/main/java/com/exemplo/mvc/model/Aluno.java
package com.exemplo.mvc.model;

/**
 * ═══════════════════════════════════════════
 *  CAMADA  →  MODEL
 * ═══════════════════════════════════════════
 *
 * Responsabilidades desta camada:
 *   ✔ Representar os dados do domínio
 *   ✔ Encapsular regras de negócio
 *   ✔ Ser completamente independente de HTTP, HTML e frameworks web
 *
 * O que NÃO pertence aqui:
 *   ✘ Anotações como @RequestParam, @GetMapping
 *   ✘ Qualquer referência a HttpServletRequest / Model (Spring MVC)
 *   ✘ Lógica de apresentação (formatação de tela, mensagens de UI)
 */
public class Aluno {

    // ── Atributos privados ────────────────────────────────────────────────────
    private final String nome;
    private final String matricula;

    // ── Construtor com regra de negócio ───────────────────────────────────────

    /**
     * Cria um aluno validado.
     *
     * Regra de negócio: nome é obrigatório.
     * A validação vive aqui, no Model — nunca no Controller ou na View.
     *
     * @param nome      Nome completo do aluno (não pode ser nulo ou vazio)
     * @param matricula Código de matrícula (pode ser vazio neste exemplo)
     * @throws IllegalArgumentException se o nome for nulo ou em branco
     */
    public Aluno(String nome, String matricula) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatório");
        }
        this.nome = nome;
        this.matricula = (matricula != null) ? matricula.strip() : "";
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    // ── toString (útil para logs) ─────────────────────────────────────────────

    @Override
    public String toString() {
        return "Aluno{nome='" + nome + "', matricula='" + matricula + "'}";
    }
}
