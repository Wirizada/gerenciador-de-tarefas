# API de Gerenciamento de Tarefas - Kanban

API RESTful completa desenvolvida em Java com Spring Boot para gerenciamento de projetos e tarefas, seguindo os princípios do método Kanban.

## ✨ Features Principais

* **Gestão de Projetos:** Criação e listagem de projetos.
* **Gestão de Tarefas:** CRUD completo de tarefas (título, descrição, status, prioridade).
* **Busca Inteligente:** Pesquisa de tarefas por título ou descrição.
* **Regras de Negócio Avançadas:**
    * **Limite de WIP:** Sistema impede que mais de 5 tarefas por projeto estejam em andamento (status "DOING") simultaneamente.
    * **Definition of Done:** Validação que exige uma descrição preenchida para que uma tarefa seja movida para "DONE".
* **Métricas Ágeis:** Endpoint para cálculo automático do **Lead Time** médio das tarefas concluídas em um projeto.
* **Arquivamento de Tarefas:** Funcionalidade de "soft delete" para arquivar tarefas sem perdê-las do histórico.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Persistência:** Spring Data JPA, Hibernate
* **Banco de Dados:** H2 (para testes), PostgreSQL (em desenvolvimento)
* **Testes:** JUnit 5, Mockito, Spring Boot Test
* **Validação:** Jakarta Bean Validation
* **Outros:** Lombok, Maven

## 🚀 Como Executar o Projeto

### Passos

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/Wirizada/gerenciador-de-tarefas
    cd gerenciador-de-tarefas
    ```

2.  **Execute a aplicação via Maven:**

    ```bash
    mvn spring-boot:run
    ```

3.  A API estará disponível em `http://localhost:8080`.

## 📝 Endpoints da API

* `POST /projetos` - Cria um novo projeto.
* `GET /projetos` - Lista todos os projetos.
* `POST /tarefas` - Cria uma nova tarefa.
* `PUT /tarefas/{id}` - Atualiza uma tarefa existente.
* `GET /metricas/lead-time?projetoId={id}` - Calcula o Lead Time médio do projeto.

---


