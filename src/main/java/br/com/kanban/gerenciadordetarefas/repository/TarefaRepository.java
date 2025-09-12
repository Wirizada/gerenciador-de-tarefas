package br.com.kanban.gerenciadordetarefas.repository;

import br.com.kanban.gerenciadordetarefas.model.Projeto;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
    long countByProjetoAndStatus(Projeto projeto, Status status);
    List<Tarefa> findByProjetoIdAndStatusNot(Long projetoId, Status status);
}
