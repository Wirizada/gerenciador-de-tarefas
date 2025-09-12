package br.com.kanban.gerenciadordetarefas.repository;

import br.com.kanban.gerenciadordetarefas.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    Optional<Projeto> findByNome(String nome);
}
