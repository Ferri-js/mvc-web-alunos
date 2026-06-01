// src/main/java/com/exemplo/mvc/controller/AlunoController.java
package com.exemplo.mvc.controller;

import com.exemplo.mvc.model.Aluno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * ═══════════════════════════════════════════
 *  CAMADA  →  CONTROLLER
 * ═══════════════════════════════════════════
 *
 * Responsabilidades desta camada:
 *   ✔ Receber requisições HTTP e mapear para ações
 *   ✔ Chamar o Model para executar regras de negócio
 *   ✔ Preparar os dados (Model/View) que a View irá exibir
 *   ✔ Decidir qual View retornar
 *
 * O que NÃO pertence aqui:
 *   ✘ Regras de negócio (ex: "nome obrigatório" → isso é do Model)
 *   ✘ Geração de HTML (isso é da View)
 *   ✘ Queries SQL diretas (isso seria responsabilidade de um Repository)
 */
@Controller
public class AlunoController {

    /**
     * Lista em memória — simula persistência para fins didáticos.
     * Em produção, esta lista seria substituída por um @Repository / banco de dados.
     */
    private final List<Aluno> alunos = new ArrayList<>();

    // ── GET /alunos ───────────────────────────────────────────────────────────

    /**
     * Exibe o formulário de cadastro.
     *
     * O Controller apenas indica qual View renderizar.
     * Nenhum dado precisa ser injetado no Model aqui — o formulário está vazio.
     *
     * @return nome lógico da View → Thymeleaf resolve para templates/alunos-form.html
     */
    @GetMapping("/alunos")
    public String exibirFormulario() {
        return "alunos-form";
    }

    // ── POST /alunos ──────────────────────────────────────────────────────────

    /**
     * Processa o cadastro de um novo aluno.
     *
     * Fluxo MVC:
     *  1. Controller recebe os dados do formulário via @RequestParam
     *  2. Controller delega a criação ao Model (new Aluno) — regra de negócio fica lá
     *  3. Controller injeta a lista atualizada no Model do Spring (org.springframework.ui.Model)
     *  4. Controller retorna o nome da View que irá exibir o resultado
     *
     * @param nome            nome digitado no formulário
     * @param matricula       matrícula digitada no formulário
     * @param model           objeto do Spring MVC para passar dados à View
     * @param redirectAttrs   atributos para redirect em caso de erro
     * @return nome lógico da View
     */
    @PostMapping("/alunos")
    public String cadastrarAluno(
            @RequestParam String nome,
            @RequestParam String matricula,
            Model model,
            RedirectAttributes redirectAttrs
    ) {
        try {
            // ── Delegação ao Model ─────────────────────────────────────────
            // A regra "nome obrigatório" vive em Aluno.java, não aqui.
            alunos.add(new Aluno(nome, matricula));

        } catch (IllegalArgumentException e) {
            // Controller captura a exceção do Model e redireciona com mensagem
            redirectAttrs.addFlashAttribute("erro", e.getMessage());
            return "redirect:/alunos";
        }

        // ── Preparação dos dados para a View ──────────────────────────────
        // Controller injeta dados; View apenas os consome.
        model.addAttribute("alunos", alunos);

        return "alunos-lista";
    }
}
