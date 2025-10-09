package br.com.kanban.gerenciadordetarefas.repository;

import br.com.kanban.gerenciadordetarefas.dto.TarefaResponse;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
class TarefaRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve retornar uma lista de tarefas")
    void findByProjetoIdAndStatusError(){


    }

    private Tarefa criaTarefa(TarefaResponse tarefaResponse) {
        return Tarefa.builder()
                .id(tarefaResponse.id())
                .titulo(tarefaResponse.titulo())
                .descricao(tarefaResponse.descricao())
                .status(tarefaResponse.status())
                .prioridade(tarefaResponse.prioridade())
                .build();
    }

}