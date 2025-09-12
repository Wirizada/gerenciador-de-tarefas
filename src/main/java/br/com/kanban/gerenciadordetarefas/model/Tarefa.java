package br.com.kanban.gerenciadordetarefas.model;

import br.com.kanban.gerenciadordetarefas.model.enums.Prioridade;
import br.com.kanban.gerenciadordetarefas.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 2000)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate criadoEm;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDate atualizadoEm;
}
