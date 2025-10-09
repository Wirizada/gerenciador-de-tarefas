package br.com.kanban.gerenciadordetarefas;

import br.com.kanban.gerenciadordetarefas.dto.AtualizarTarefaRequest;
import br.com.kanban.gerenciadordetarefas.exception.BusinessRuleException;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import br.com.kanban.gerenciadordetarefas.repository.ProjetoRepository;
import br.com.kanban.gerenciadordetarefas.repository.TarefaRepository;
import br.com.kanban.gerenciadordetarefas.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void deveLancarExceptionAoMoverParaDoneUmaTarefaSemDescricao() {

        Long idTarefa = 1L;
        AtualizarTarefaRequest atualizarTarefaRequest = new AtualizarTarefaRequest(null, null, Status.DONE, null);

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idTarefa);
        tarefa.setDescricao(null);

        when(tarefaRepository.findById(idTarefa)).thenReturn(Optional.of(tarefa));

        assertThrows(BusinessRuleException.class, () -> {
            tarefaService.atualizarTarefa(idTarefa, atualizarTarefaRequest);
        });

        verify(tarefaRepository, never()).save(any(Tarefa.class));
    }

    @Test
    void deveAtualizarTarefaStatusDaTarefaParaDoneQuandoHouverDescricao() {

        Long idTarefa = 1L;
        AtualizarTarefaRequest atualizarTarefaRequest = new AtualizarTarefaRequest(null, null, Status.DONE, null);

        Tarefa tarefa = new Tarefa();
        tarefa.setId(idTarefa);
        tarefa.setDescricao("Teste");

        when(tarefaRepository.findById(idTarefa)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(idTarefa, atualizarTarefaRequest);

        assertEquals(Status.DONE, tarefaAtualizada.getStatus());
        verify(tarefaRepository, times(1)).save(tarefa);
    }
}
