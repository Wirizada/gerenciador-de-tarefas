package br.com.kanban.gerenciadordetarefas.dto;

import br.com.kanban.gerenciadordetarefas.model.enums.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarTarefaRequest(
        @NotNull(message = "O ID do projeto é obrigatório.")
        Long projetoId,
        @NotBlank(message = "O titúlo não pode ser vazio.")
        @Size(max = 255, message = "A descrição pode ter no máximo 2000 caracteres.")
        String titulo,
        @Size(max = 2000, message = "A descrição pode ter no máximo 2000 caracteres.")
        String descricao,
        Prioridade prioridade
) {}
