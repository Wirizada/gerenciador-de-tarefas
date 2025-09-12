package br.com.kanban.gerenciadordetarefas.service;

import br.com.kanban.gerenciadordetarefas.model.Projeto;
import br.com.kanban.gerenciadordetarefas.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Autowired
    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public Projeto criarProjeto(Projeto projeto) {
        Optional<Projeto> ProjetoExistente = projetoRepository.findByNome(projeto.getNome());
        if (ProjetoExistente.isPresent()) {
            throw new IllegalArgumentException("Projeto com o Nome " + projeto.getNome() + " já existe.");
        }
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> obterProjetoPorId(Long id) {
        return projetoRepository.findById(id);
    }
}
