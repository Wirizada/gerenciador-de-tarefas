package br.com.kanban.gerenciadordetarefas.controller;

import br.com.kanban.gerenciadordetarefas.dto.AtualizarTarefaRequest;
import br.com.kanban.gerenciadordetarefas.dto.CriarTarefaRequest;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import br.com.kanban.gerenciadordetarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody CriarTarefaRequest request){
        Tarefa tarefaSalva = tarefaService.criarTarefa(request);

        URI location = URI.create(String.format("/tarefas/" + tarefaSalva.getId()));
        return ResponseEntity.created(location).body(tarefaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@RequestParam Long projetoId){
        List<Tarefa> tarefas = tarefaService.listarPorProjeto(projetoId);
        return ResponseEntity.ok(tarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody AtualizarTarefaRequest request){
        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, request);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> arquivarTarefa(@PathVariable Long id){
        tarefaService.arquivarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Tarefa>> pesquisarTarefas(
            @RequestParam Long projetoId,
            @RequestParam String termo ){
        List<Tarefa> tarefasEncontradas = tarefaService.pesquisar(projetoId, termo);
        return ResponseEntity.ok(tarefasEncontradas);
    }
}
