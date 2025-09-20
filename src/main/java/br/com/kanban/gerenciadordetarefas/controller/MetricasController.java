package br.com.kanban.gerenciadordetarefas.controller;

import br.com.kanban.gerenciadordetarefas.dto.MetricaLeadTimeResponse;
import br.com.kanban.gerenciadordetarefas.service.MetricasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metricas")
public class MetricasController {

    private final MetricasService metricasService;

    @Autowired
    public MetricasController(MetricasService metricasService) {
        this.metricasService = metricasService;
    }

    @GetMapping("/lead-time")
    public ResponseEntity<MetricaLeadTimeResponse> getLeadTimeMedioPorProjeto(@RequestParam Long projetoId) {
        MetricaLeadTimeResponse metrica = metricasService.calcularLeadTimeMedio(projetoId);
        return ResponseEntity.ok(metrica);
    }
}
