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
## 29/10/2023 - Adição da Entidade de Entrega (`Delivery`) e Funcionalidades CRUD
Neste dia, a equipe adicionou uma nova entidade ao sistema: a `Delivery`, que representa uma entrega no contexto da aplicação. Além disso, foram implementadas funcionalidades CRUD (Create, Read, Update, Delete) relacionadas às entregas. A seguir, detalhamos as mudanças realizadas:

### `Delivery`

- **Descrição**: A entidade `Delivery` representa uma entrega no sistema.
- **Atributos**:
  - `id` (Long): Identificador único da entrega (gerado automaticamente).
  - `client` (Client): Cliente associado à entrega.
  - `receiver` (Receiver): Informações sobre o destinatário da entrega.
  - `tax` (BigDecimal): Valor da taxa de entrega.
  - `orderDate` (LocalDateTime): Data e hora do pedido de entrega.
  - `finalizationDate` (LocalDateTime): Data e hora de finalização da entrega.
  - `status` (DeliveryStatus): Status da entrega (PENDING, FINISHED, CANCELED).

### `DeliveryStatus`

- **Descrição**: Enumeração que define os possíveis status de uma entrega (`PENDING`, `FINISHED`, `CANCELED`).

### `Receiver`

- **Descrição**: A classe `Receiver` representa as informações do destinatário da entrega.
- **Atributos**:
  - `name` (String): Nome do destinatário.
  - `address` (String): Endereço de entrega.
  - `number` (String): Número do endereço.
  - `complement` (String): Complemento do endereço.
  - `district` (String): Bairro do endereço.

### Funcionalidades CRUD para Entregas

#### `DeliveryController`

- **Descrição**: O controlador `DeliveryController` foi adicionado para gerenciar as operações relacionadas às entregas.
- **Endpoints**:
  - `POST /deliveries`: Cria uma nova solicitação de entrega.
  - `GET /deliveries/{id}`: Obtém detalhes de uma entrega específica pelo seu ID.
  - `PUT /deliveries/{id}`: Atualiza informações de uma entrega existente.
  - `DELETE /deliveries/{id}`: Cancela uma entrega com base no ID fornecido.

#### `DeliveryRequestService`

- **Descrição**: O serviço `DeliveryRequestService` foi criado para manipular solicitações de entrega no sistema.
- **Método**:
  - `requestDelivery(Delivery delivery)`: Cria uma nova solicitação de entrega com status `PENDING` e define a data e hora do pedido. Retorna a entrega criada.

#### `DeliveryRepository`

- **Descrição**: A interface `DeliveryRepository` foi desenvolvida para permitir operações CRUD no banco de dados para a entidade `Delivery`.

# Atualização em 06/11/2023

Nesta atualização, foram realizadas modificações importantes no projeto Delivery API. Abaixo estão os detalhes das mudanças realizadas:

### `DeliveryController`
A classe `DeliveryController` foi atualizada para incluir um novo endpoint:
- `PUT /deliveries/{id}/finalizated`: Este endpoint permite finalizar uma entrega específica com base no seu ID. Ao chamar este endpoint, a entrega é marcada como finalizada e a data e hora de finalização são registradas.

### `DeliveryFinalizationService`
Um novo serviço chamado `DeliveryFinalizationService` foi criado para gerenciar a finalização das entregas. Ele inclui o método `finalize(Long deliveryId)` para marcar uma entrega como finalizada. Ao chamar este método, a entrega é finalizada e a data e hora de finalização são registradas.

### `DeliveryRepository`
A interface `DeliveryRepository` foi modificada para incluir um método adicional:
- `findById(Long id)`: Este método permite buscar uma entrega pelo seu ID. Ele retorna um objeto `Optional<Delivery>`, o que significa que pode ou não haver uma entrega com o ID fornecido.

# Atualização 12/11/2023

algumas modificações significativas foram realizadas no projeto Delivery API. Aqui estão os detalhes das alterações:

### Atualização de Dependências no arquivo `pom.xml`
O arquivo `pom.xml` foi atualizado para incluir novas versões de dependências:

### Modificações em `ClientController`
Na classe `ClientController`, foram feitas as seguintes alterações:
- Adicionado novo endpoint `PUT /clients/{id}`: Este endpoint permite a atualização de informações de um cliente existente com base no seu ID.

### Modificações em `Client`
Na classe `Client`, foi adicionado um método setter para o atributo `id`.

### Modificações em `Delivery`
Na classe `Delivery`, o método `finalize` foi renomeado para `finalizeDelivery`. Além disso, os atributos `orderDate` e `finalizationDate` foram alterados para usar `OffsetDateTime` em vez de `LocalDateTime`.

### Adição de Serviço `DeliveryFinalizationService`
Foi adicionado um novo serviço chamado `DeliveryFinalizationService` para gerenciar a finalização das entregas. Este serviço inclui o método `finalizeDelivery(Long deliveryId)`.

### Modificações em `DeliveryRequestService`
Na classe `DeliveryRequestService`, o método `requestDelivery` foi ajustado para definir a data do pedido usando `OffsetDateTime` em vez de `LocalDateTime`.

### Testes Unitários
Foram adicionados testes unitários para as classes `ClientService` e `DeliveryService` usando a biblioteca JUnit 5 e o framework Mockito.


