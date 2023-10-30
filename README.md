# Genroom - Sistema de Gerenciamento de Salas para Faculdades
Genroom é uma aplicação back-end desenvolvida para facilitar o gerenciamento de salas de aula em instituições de ensino superior. 
A API fornece funcionalidades para criar, atualizar, deletar e consultar informações sobre salas, reservas e eventos.

### Tecnologias Utilizadas
* Java 17
* Spring Boot
* Postgres

### Arquitetura
A aplicação segue uma arquitetura em camadas, dividindo a lógica do sistema entre a interface do usuário, lógica de negócios e armazenamento de dados.

* Interface do Usuário: Camada responsável pela interação com os usuários através de endpoints da API REST.
* Lógica de Negócios: Contém as regras de negócios, serviços de aplicação e transformações de dados.
* Armazenamento de Dados: Camada onde residem os repositórios responsáveis por acessar e persistir os dados do sistema.

### API Endpoints
A API Genroom oferece uma série de endpoints para manipulação e consulta de dados relacionados a salas, reservas e eventos.
![Screenshot_1](https://github.com/jcr04/Genroom.java/assets/70778525/3b1010c1-e028-47e5-a28b-7be0072d9b90)

### Salas

- **Listar todas as salas:** `GET /api/salas`
- **Buscar sala por ID:** `GET /api/salas/{id}`
- **Criar nova sala:** `POST /api/salas`
- **Atualizar sala existente:** `PUT /api/salas/{id}`
- **Deletar sala:** `DELETE /api/salas/{id}`
- **Buscar salas por nome:** `GET /api/salas/nome/{nome}`
- **Buscar salas por capacidade:** `GET /api/salas/capacidade/{capacidade}`
- **Buscar salas disponíveis em um intervalo de tempo:** `GET /api/salas/disponiveis?inicio={inicio}&fim={fim}`

### Reservas

- **Listar todas as reservas:** `GET /api/reservas`
- **Buscar reserva por ID:** `GET /api/reservas/{id}`
- **Criar nova reserva:** `POST /api/reservas`
- **Atualizar reserva existente:** `PUT /api/reservas/{id}`
- **Deletar reserva:** `DELETE /api/reservas/{id}`
- **Buscar reservas por intervalo de tempo:** `GET /api/reservas/intervalo?start={start}&end={end}`
- **Buscar reservas por sala:** `GET /api/reservas/sala/{salaId}`

### Eventos

- **Listar todos os eventos:** `GET /api/eventos`
- **Buscar evento por ID:** `GET /api/eventos/{id}`
- **Criar novo evento:** `POST /api/eventos`
- **Atualizar evento existente:** `PUT /api/eventos/{id}`
- **Deletar evento:** `DELETE /api/eventos/{id}`
- **Buscar eventos por título:** `GET /api/eventos/titulo/{titulo}`
- **Buscar eventos por descrição:** `GET /api/eventos/descricao?descricao={descricao}`

### Recursos

- **LIstar todos os Recursos:** `GET /api/recurso`
- **Buscar Recurso por ID:** `GET /api/recurso/{id}`
- **Criar novo Recurwso:** `POST /api/recurso`
- **Atualizar um Recruso Exitente:** `PUT /api/recursos/{id}`
- **Deletar um Recurso:** `DELETE /api/recurso/{id}`

## Como Executar

1. Clone o repositório para sua máquina local.
2. Certifique-se de ter o Java 17 e Maven instalados.
3. configure o application.properties para seu dba em Postgresql exemplo:
```batch
spring.datasource.url=jdbc:postgresql://localhost:5432/genroom
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
debug=true
server.port=8081
```
5. Navegue até o diretório do projeto e execute o comando: `mvn spring-boot:run`.
6. A aplicação estará rodando no endereço: `http://localhost:8081`.

Para testar os endpoints, você pode usar ferramentas como Postman ou Curl. As requisições devem ser feitas para a base da URL `http://localhost:8081/api`.

#### Exemplo de um Endpoint:
- /api/reservas (GET): Listar Todas as Reservas
* - ![Screenshot_4](https://github.com/jcr04/Genroom.java/assets/70778525/4b1ab399-6dea-4d2b-8308-eff1674bfb7e)
