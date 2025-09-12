package br.com.kanban.gerenciadordetarefas.controller;

import br.com.kanban.gerenciadordetarefas.dto.CriarProjetoRequest;
import br.com.kanban.gerenciadordetarefas.model.Projeto;
import br.com.kanban.gerenciadordetarefas.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Projeto> criarProjeto(@Valid @RequestBody CriarProjetoRequest request) {
        Projeto novoProjeto = new Projeto();
        novoProjeto.setNome(request.nome());

        Projeto projetoSalvo = projetoService.criarProjeto(novoProjeto);

        URI location = URI.create(String.format("/projetos/" + projetoSalvo.getId()));
        return ResponseEntity.created(location).body(projetoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarProjetos() {
        List<Projeto> projetos = projetoService.listarTodosProjetos();
        return ResponseEntity.ok(projetos);
    }
}
