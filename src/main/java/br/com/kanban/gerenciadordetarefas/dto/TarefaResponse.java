package br.com.kanban.gerenciadordetarefas.dto;

import br.com.kanban.gerenciadordetarefas.model.enums.Prioridade;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TarefaResponse(
        Long id,
        String titulo,
        String descricao,
        Status status,
        Prioridade prioridade,

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime atualizadoEm,
        Long projetoId
) {

}
