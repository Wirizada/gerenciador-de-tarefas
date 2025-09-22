package br.com.kanban.gerenciadordetarefas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean ativo = true;
}
