package br.com.kanban.gerenciadordetarefas.controller;

import br.com.kanban.gerenciadordetarefas.dto.AtualizarTarefaRequest;
import br.com.kanban.gerenciadordetarefas.dto.CriarTarefaRequest;
import br.com.kanban.gerenciadordetarefas.dto.TarefaResponse;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import br.com.kanban.gerenciadordetarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> criarTarefa(@Valid @RequestBody CriarTarefaRequest request){
        Tarefa tarefaSalva = tarefaService.criarTarefa(request);

        TarefaResponse tarefaResponse = new TarefaResponse(
                tarefaSalva.getId(),
                tarefaSalva.getTitulo(),
                tarefaSalva.getDescricao(),
                tarefaSalva.getStatus(),
                tarefaSalva.getPrioridade(),
                tarefaSalva.getCriadoEm(),
                tarefaSalva.getAtualizadoEm(),
                tarefaSalva.getProjeto().getId()
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tarefaResponse.id()).toUri();
        return ResponseEntity.created(location).body(tarefaResponse);
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listarTarefas(@RequestParam Long projetoId){
        List<Tarefa> tarefas = tarefaService.listarPorProjeto(projetoId);

        List<TarefaResponse> tarefaResponse = tarefas.stream()
                .map(tarefa -> new TarefaResponse(tarefa.getId(),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getStatus(),
                        tarefa.getPrioridade(),
                        tarefa.getCriadoEm(),
                        tarefa.getAtualizadoEm(),
                        tarefa.getProjeto().getId()
                        ))
                .toList();

        return ResponseEntity.ok(tarefaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody AtualizarTarefaRequest request){
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, request);

        TarefaResponse tarefaResponse = new TarefaResponse(
                tarefaAtualizada.getId(),
                tarefaAtualizada.getTitulo(),
                tarefaAtualizada.getDescricao(),
                tarefaAtualizada.getStatus(),
                tarefaAtualizada.getPrioridade(),
                tarefaAtualizada.getCriadoEm(),
                tarefaAtualizada.getAtualizadoEm(),
                tarefaAtualizada.getProjeto().getId()
        );

        return ResponseEntity.ok(tarefaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> arquivarTarefa(@PathVariable Long id){
        tarefaService.arquivarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<TarefaResponse>> pesquisarTarefas(
            @RequestParam Long projetoId,
            @RequestParam String termo ){
        List<Tarefa> tarefasEncontradas = tarefaService.pesquisar(projetoId, termo);

        List<TarefaResponse> tarefaResponse = tarefasEncontradas.stream()
                .map(tarefa -> new TarefaResponse(tarefa.getId(),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getStatus(),
                        tarefa.getPrioridade(),
                        tarefa.getCriadoEm(),
                        tarefa.getAtualizadoEm(),
                        tarefa.getProjeto().getId()
                ))
                .toList();

        return ResponseEntity.ok(tarefaResponse);
    }
}
