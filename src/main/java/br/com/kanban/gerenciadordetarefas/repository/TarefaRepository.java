package br.com.kanban.gerenciadordetarefas.repository;

import br.com.kanban.gerenciadordetarefas.model.Projeto;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
    long countByProjetoAndStatus(Projeto projeto, Status status);

    @Query("SELECT t FROM Tarefa t WHERE t.projeto.id = :projetoId AND t.status != :status ORDER BY t.atualizadoEm DESC")
    List<Tarefa> findAllAtivasPorProjetoId(
            @Param("projetoId") Long projetoId,
            @Param("status") Status status
    );

    @Query("SELECT t FROM Tarefa t WHERE t.projeto.id = :projetoId AND t.status !='ARCHIVED' AND "+
            "(LOWER(t.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR "+
            "LOWER(t.descricao) LIKE LOWER(CONCAT('%', :termo, '%')))"+
            "ORDER BY t.atualizadoEm DESC")
    List<Tarefa> pesquisarPorTermoNoProjeto(
            @Param("projetoId")Long projetoId,
            @Param("termo")String termo
    );

    @Query("SELECT t FROM Tarefa t WHERE t.projeto.id = :projetoId AND t.status = :status")
    List<Tarefa> findByProjetoIdAndStatus(
            @Param("projetoId") Long projetoId,
            @Param("status") Status status
    );
}
