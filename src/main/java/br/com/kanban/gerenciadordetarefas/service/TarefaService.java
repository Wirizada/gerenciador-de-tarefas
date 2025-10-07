package br.com.kanban.gerenciadordetarefas.service;

import br.com.kanban.gerenciadordetarefas.dto.AtualizarTarefaRequest;
import br.com.kanban.gerenciadordetarefas.dto.CriarTarefaRequest;
import br.com.kanban.gerenciadordetarefas.exception.BusinessRuleException;
import br.com.kanban.gerenciadordetarefas.model.Projeto;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import br.com.kanban.gerenciadordetarefas.model.enums.Prioridade;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import br.com.kanban.gerenciadordetarefas.repository.ProjetoRepository;
import br.com.kanban.gerenciadordetarefas.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final ProjetoRepository projetoRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository, ProjetoRepository projetoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.projetoRepository = projetoRepository;
    }

    private static final int LIMITE_WIP_POR_PROJETO = 5;

    public Tarefa criarTarefa(CriarTarefaRequest dto){

        Projeto projeto = projetoRepository.findById(dto.projetoId())
                .orElseThrow(() -> new EntityNotFoundException("Projeto com o ID " + dto.projetoId() + " não existe."));

        if (!projeto.isAtivo()){
            throw new BusinessRuleException("Não é possível adicionar tarefas a um projeto inativo.");
        }

        Tarefa novaTarefa = Tarefa.builder()
                .projeto(projeto)
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .status(Status.TODO)
                .prioridade(dto.prioridade() != null ? dto.prioridade() : Prioridade.BAIXA)
                .build();

        return tarefaRepository.save(novaTarefa);
    }

    public List<Tarefa> listarPorProjeto(Long projetoId){
        return tarefaRepository.findAllActiveByProjectId(projetoId, Status.ARCHIVED);
    }

    public Tarefa atualizarTarefa(@PathVariable Long id, AtualizarTarefaRequest dto){
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa com o ID " + id + " não existe."));

        if(dto.status() == Status.DOING){
            long tarefasEmDoing = tarefaRepository.countByProjetoAndStatus(tarefa.getProjeto(), Status.DOING);
            if(tarefasEmDoing >= LIMITE_WIP_POR_PROJETO){
                throw new BusinessRuleException("Limite de tarefas em 'DOING' atingido para o projeto.");
            }
        }

        if(dto.titulo() != null){
            tarefa.setTitulo(dto.titulo());
        }

        if(dto.descricao() != null){
            tarefa.setDescricao(dto.descricao());
        }

        if(dto.status() != null){
            tarefa.setStatus(dto.status());
        }

        if(dto.prioridade() != null){
            tarefa.setPrioridade(dto.prioridade());
        }

        if (dto.status() == Status.DONE && (tarefa.getDescricao() == null || tarefa.getDescricao().isBlank())) {
            throw new BusinessRuleException("Não é possível mover a tarefa para 'DONE' sem uma descrição.");
        }

        return tarefaRepository.save(tarefa);
    }

    public void arquivarTarefa(Long id){
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa com o ID " + id + " não existe."));
        tarefa.setStatus(Status.ARCHIVED);
        tarefaRepository.save(tarefa);
    }

    public List<Tarefa> pesquisar(Long projetoId, String termo){
        if(termo == null || termo.isBlank()){
            return listarPorProjeto(projetoId);
        }
        return tarefaRepository.searchByTermInProject(projetoId, termo);
    }
}
