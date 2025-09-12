package br.com.kanban.gerenciadordetarefas.dto;

import br.com.kanban.gerenciadordetarefas.model.enums.Prioridade;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import jakarta.validation.constraints.Size;

public record AtualizarTarefaRequest(
        @Size(max = 255)
        String titulo,
        @Size(max = 2000)
        String descricao,
        Status status,
        Prioridade prioridade
) {
}
