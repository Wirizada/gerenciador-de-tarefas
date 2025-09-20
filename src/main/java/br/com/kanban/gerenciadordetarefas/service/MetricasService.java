package br.com.kanban.gerenciadordetarefas.service;

import br.com.kanban.gerenciadordetarefas.dto.MetricaLeadTimeResponse;
import br.com.kanban.gerenciadordetarefas.model.Tarefa;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import br.com.kanban.gerenciadordetarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class MetricasService {

    private TarefaRepository tarefaRepository;

    @Autowired
    public MetricasService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public MetricaLeadTimeResponse calcularLeadTimeMedio(Long projetoId){
        List<Tarefa> tarefasConcluidas = tarefaRepository.findByProjetoIdAndStatus(projetoId, Status.DONE);

        if(tarefasConcluidas.isEmpty()){
            return new MetricaLeadTimeResponse(0, "Nenhuma tarefa concluida");
        }

        long totalSegundos = tarefasConcluidas.stream()
                .mapToLong(tarefa -> Duration.between(tarefa.getCriadoEm(), tarefa.getAtualizadoEm()).toSeconds())
                .sum();

        long mediaSegundos = totalSegundos / tarefasConcluidas.size();
        Duration mediaLeadTime = Duration.ofSeconds(mediaSegundos);

        String leadTimeFormatado = formatarDuracao(mediaLeadTime);

        return new MetricaLeadTimeResponse(tarefasConcluidas.size(), leadTimeFormatado);
    }

    private String formatarDuracao(Duration duration){
        long dias = duration.toDays();
        long horas = duration.toHoursPart();
        long minutos = duration.toMinutesPart();
        return String.format("%d dias, %d horas, %d minutos", dias, horas, minutos);

    }
}
