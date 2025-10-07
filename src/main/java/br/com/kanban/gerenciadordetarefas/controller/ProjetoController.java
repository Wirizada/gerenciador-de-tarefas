package br.com.kanban.gerenciadordetarefas.controller;

import br.com.kanban.gerenciadordetarefas.dto.CriarProjetoRequest;
import br.com.kanban.gerenciadordetarefas.dto.ProjetoResponse;
import br.com.kanban.gerenciadordetarefas.model.Projeto;
import br.com.kanban.gerenciadordetarefas.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponse> criarProjeto(@Valid @RequestBody CriarProjetoRequest request) {
        Projeto novoProjeto = Projeto.builder()
                .nome(request.nome())
                .build();

        Projeto projetoSalvo = projetoService.criarProjeto(novoProjeto);

        ProjetoResponse projetoResponse = new ProjetoResponse(
                projetoSalvo.getId(),
                projetoSalvo.getNome(),
                projetoSalvo.isAtivo()
        );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(projetoResponse.id()).toUri();

        return ResponseEntity.created(location).body(projetoResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponse>> listarProjetos() {
        List<Projeto> projetos = projetoService.listarTodosProjetos();

        List<ProjetoResponse> projetoResponse = projetos.stream()
                .map(projeto -> new ProjetoResponse(projeto.getId(), projeto.getNome(), projeto.isAtivo()))
                .toList();

        return ResponseEntity.ok(projetoResponse);
    }
}
