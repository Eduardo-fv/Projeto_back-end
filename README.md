# Documentação do Projeto

## 15/10/2023 - Início do Projeto
O projeto foi criado usando a ferramenta start.spring.io, disponível no site [Spring Initializer](https://start.spring.io/). Foram selecionadas as dependências necessárias para criar uma aplicação Spring Boot.

## 18/10/2023 - Implementação da Classe Client
Nesse dia, o foco principal foi na implementação da classe `Client` no projeto Delivery API. A classe `Client` representa os clientes que usam a aplicação de entrega e possui os seguintes atributos:

- `id` (Long): Identificador único do cliente (gerado automaticamente).
- `name` (String): Nome do cliente.
- `email` (String): Endereço de e-mail do cliente.
- `phone` (String): Número de telefone do cliente.

Essa classe foi adicionada no arquivo `src/main/java/edu/univille/deliveryapi/domain/model/Client.java`.

## 21/10/2023 - Implementação do CRUD para a Entidade Cliente

No dia 21/10/2023, foram adicionadas funcionalidades CRUD para a entidade `Client` no projeto Delivery API. Abaixo estão os detalhes das mudanças realizadas:

### `ClientController`

A classe `ClientController` foi atualizada para incluir endpoints para operações CRUD (Create, Read, Update, Delete) relacionadas aos clientes. Os seguintes endpoints foram adicionados:

- `POST /clients`: Cria um novo cliente.
- `DELETE /clients/{id}`: Exclui um cliente pelo seu ID.
- `GET /clients`: Retorna a lista de todos os clientes.
- `GET /clients/{id}`: Retorna um cliente específico pelo seu ID.

