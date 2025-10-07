package br.com.kanban.gerenciadordetarefas.dto;

public record ProjetoResponse(
        Long id,
        String nome,
        boolean ativo
) {
}
