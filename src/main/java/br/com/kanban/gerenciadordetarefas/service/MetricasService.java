package br.com.kanban.gerenciadordetarefas.service;

import br.com.kanban.gerenciadordetarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricasService {

    private TarefaRepository tarefaRepository;

    @Autowired
    public MetricasService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

}
