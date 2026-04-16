# 🚀 Fluxo Alpha - Lead Ingestion Webhook Processor

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring--boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Visão Geral do Projeto

Este projeto é uma API de alta performance desenhada para atuar como um **"para-choque" de tráfego**.

Em cenários reais de marketing digital e gestão de tráfego pago, campanhas de sucesso podem gerar picos agressivos de acessos e cadastros de leads em questão de segundos. APIs tradicionais e síncronas tendem a travar o banco de dados e retornar erros de *Timeout* sob esse estresse, resultando na perda de leads valiosos.

O **Lead Ingestion Webhook Processor** resolve esse problema implementando uma **Arquitetura Orientada a Eventos (EDA)**. Ele recebe o payload HTTP da plataforma de anúncios, enfileira a mensagem em um *Message Broker* (RabbitMQ) e responde imediatamente com `202 Accepted`. O processamento real e a inserção no banco de dados ocorrem em *background* através de *Workers* assíncronos.

## 🎯 Principais Desafios e Soluções (Arquitetura)

- **Alta Disponibilidade (Throughput):** A API principal está isolada da latência do banco de dados. O tempo de resposta para a plataforma de origem é mantido na casa dos milissegundos.
- **Resiliência e Tolerância a Falhas:** Implementação de **Dead Letter Queues (DLQ)**. Se um lead chegar com dados corrompidos ou o banco de dados cair, a mensagem não é perdida. Após X tentativas (Retries), ela é movida para uma DLQ para análise manual ou reprocessamento posterior.
- **Design Baseado em Domínio (DDD):** A entidade `Lead` foi desenhada para não ser um modelo anêmico. O projeto aplica o princípio de *Always Valid State* (Estado Sempre Válido) com validações encapsuladas diretamente no Domínio, garantindo integridade antes de qualquer interação com a infraestrutura.

## 🏗️ Arquitetura do Fluxo de Dados

1. **Plataforma Externa** dispara um `POST` Webhook.
2. O **Controller** recebe o DTO e delega para o **Producer**.
3. O **Producer** publica a mensagem em uma *Exchange* do **RabbitMQ**.
4. O Webhook retorna `HTTP 202 (Accepted)` imediatamente.
5. Um **Worker/Consumer** lê a fila no próprio ritmo da aplicação.
6. O **Service** orquestra o Domínio e persiste no **PostgreSQL**.
   *(Falhas no step 6 redirecionam a mensagem para a DLQ).*

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Mensageria:** Spring AMQP / RabbitMQ
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL
* **Infraestrutura:** Docker & Docker Compose
* **Boilerplate Reduction:** Lombok

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
* Java 21+ instalado.
* Maven instalado.
* Docker e Docker Compose instalados.

### Passos

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/webhook-processor.git](https://github.com/seu-usuario/webhook-processor.git)
   cd webhook-processor