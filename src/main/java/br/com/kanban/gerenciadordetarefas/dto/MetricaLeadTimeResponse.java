package br.com.kanban.gerenciadordetarefas.dto;

public record MetricaLeadTimeResponse(
        long totalTarefasConcluidas,
        String leadTimeMedio
){}
