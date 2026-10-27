# 🏋️ Spring Gym API

API REST para gerenciamento de academia: alunos, treinos, exercícios e avaliações físicas, com autenticação **JWT** e controle de acesso por perfis (`ALUNO` / `ADMIN`).

Projeto desenvolvido com foco em aprendizado e boas práticas do ecossistema **Spring**.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?logo=jsonwebtokens)

## 📑 Sumário

- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Endpoints](#-endpoints)
- [Como executar](#-como-executar)
- [Variáveis de ambiente](#-variáveis-de-ambiente)
- [Documentação da API](#-documentação-da-api-swagger)
- [Próximos passos](#-próximos-passos)
- [Autor](#-autor)

## ✨ Funcionalidades

- Cadastro e login de usuários com **JWT** (token com expiração configurável)
- Autorização por perfil: `ROLE_ALUNO` e `ROLE_ADMIN`
- CRUD de **alunos**
- Cadastro e consulta de **exercícios**, com filtro por grupo muscular
- Criação e listagem de **treinos**
- Registro de **avaliações físicas** (peso, altura, % de gordura corporal) com **paginação**
- Tratamento global de exceções com respostas de erro padronizadas
- Documentação interativa com **Swagger UI** (OpenAPI)

## 🛠 Tecnologias

| Camada            | Tecnologia                                  |
|-------------------|---------------------------------------------|
| Linguagem         | Java 21                                     |
| Framework         | Spring Boot 4.1.1 (Spring MVC)              |
| Persistência      | Spring Data JPA / Hibernate                 |
| Banco de dados    | MySQL                                       |
| Segurança         | Spring Security + JWT (jjwt 0.13)           |
| Documentação      | springdoc-openapi (Swagger UI)              |
| Utilitários       | Lombok                                      |
| Build             | Maven               |

## 🧱 Arquitetura

Organização em camadas, separando responsabilidades:

```
src/main/java/com/vidigal/sprint_boot_essencials
├── config/        # Segurança, filtro JWT e provedor de tokens
├── controller/    # Endpoints REST
├── service/       # Regras de negócio
├── database/
│   ├── model/     # Entidades JPA
│   └── repository/# Repositórios Spring Data
├── dto/           # Objetos de entrada/saída (e projections)
├── enums/         # Perfis de acesso
├── exception/     # Exceções de domínio e ErrorResponse
└── handler/       # GlobalExceptionHandler
```

## 🔌 Endpoints

Base URL: `http://localhost:8082`

| Método | Rota                                  | Descrição                          | Acesso                 |
|--------|---------------------------------------|------------------------------------|------------------------|
| POST   | `/v1/auth/register`                   | Registrar usuário                  | Público                |
| POST   | `/v1/auth/login`                      | Autenticar e obter token JWT       | Público                |
| GET    | `/v1/alunos`                          | Listar alunos                      | Autenticado            |
| POST   | `/v1/alunos`                          | Criar aluno                        | Autenticado            |
| DELETE | `/v1/alunos/{alunoId}`                | Remover aluno                      | Autenticado            |
| GET    | `/v1/alunos/{alunoId}/avaliacao`      | Avaliação do aluno                 | Próprio aluno ou ADMIN |
| GET    | `/v1/exercicios`                      | Listar exercícios                  | Autenticado            |
| POST   | `/v1/exercicios`                      | Criar exercício                    | Autenticado            |
| GET    | `/v1/exercicios/grupos/{grupo}`       | Filtrar por grupo muscular         | Autenticado            |
| GET    | `/v1/treinos`                         | Listar treinos                     | Autenticado            |
| POST   | `/v1/treinos`                         | Criar treino                       | Autenticado            |
| GET    | `/v1/avaliacoes`                      | Listar avaliações físicas          | Autenticado            |
| GET    | `/v1/avaliacoes/page/{page}/size/{size}` | Listar avaliações (paginado)    | Autenticado            |
| POST   | `/v1/avaliacoes`                      | Registrar avaliação física         | ADMIN                  |

Envie o token nas requisições protegidas:

```
Authorization: Bearer <seu_token>
```

## 🚀 Como executar

### Pré-requisitos

- JDK 21+
- Maven 3.9+
- MySQL em execução (porta `3306`)
- Git

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/<seu-usuario>/<nome-do-repo>.git
cd <nome-do-repo>

# 2. Configure as variáveis de ambiente (veja a seção abaixo)

# 3. Execute a aplicação
mvn spring-boot:run
```

Ou rode a classe `SprintBootEssencialsApplication` direto pela sua IDE.

A API sobe em **http://localhost:8082**. O banco `gym` é criado automaticamente caso não exista, e as tabelas são geradas pelo Hibernate (`ddl-auto: update`).

## 🔐 Variáveis de ambiente

| Variável              | Obrigatória | Descrição                                          | Padrão             |
|-----------------------|:-----------:|----------------------------------------------------|--------------------|
| `DATABASE_USERNAME`   | ✅          | Usuário do MySQL                                   | —                  |
| `DATABASE_PASSWORD`   | ✅          | Senha do MySQL                                     | —                  |
| `JWT_KEY`             | ✅          | Chave secreta usada para assinar o JWT             | —                  |
| `JWT_EXPIRATION`      | ❌          | Tempo de expiração do token em ms                  | `900000` (15 min)  |

Exemplo (PowerShell):

```powershell
$env:DATABASE_USERNAME = "root"
$env:DATABASE_PASSWORD = "sua_senha"
$env:JWT_KEY = "uma-chave-longa-e-aleatoria-com-pelo-menos-32-caracteres"
```

> ⚠️ Nunca versione credenciais ou chaves. Use sempre variáveis de ambiente.

## 📖 Documentação da API (Swagger)

Com a aplicação rodando, acesse:

- Swagger UI: http://localhost:8082/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8082/v3/api-docs

> A documentação é pública, mas os endpoints da API continuam protegidos: gere um token em `/v1/auth/login` para chamá-los.

## 🗺 Próximos passos

- [ ] Testes unitários e de integração
- [ ] Migrations com Flyway (substituir `ddl-auto: update`)
- [ ] Dockerfile + `docker-compose` (API + MySQL)
- [ ] Validação de entrada com Bean Validation
- [ ] Pipeline de CI com GitHub Actions

## 👤 Autor

**João Vidigal**

[![GitHub](https://img.shields.io/badge/GitHub-<seu--usuario>-181717?logo=github)](https://github.com/<seu-usuario>)
