 🔗 Middleware BigQuery ↔️ Banco de Dados

Este projeto atua como um middleware entre o **Google BigQuery** e um **banco de dados relacional**, permitindo a sincronização de dados entre sistemas.

## 🎯 Objetivo

Oferecer uma camada intermediária para:

- 📡 **Sistemas legados** que não suportam conexão direta ao BigQuery.
- 🔄 **Facilitar integrações** com macros VBA, aplicações simples ou outras APIs que consumirão os dados.
- 🔐 **Aplicar segurança e controle de acesso**, garantindo que apenas os dados necessários sejam expostos, sem acesso irrestrito ao BigQuery.

## 🧱 Estrutura do Projeto

O projeto será implementado como uma API, com endpoints definidos por um time responsável pela camada de integração com BigQuery. A estrutura será pensada para:

- Receber requisições externas com critérios de consulta.
- Consultar, processar e filtrar dados do BigQuery conforme regras definidas.
- Persistir ou servir os dados em um banco de dados intermediário, quando necessário.
- Expor os dados de forma segura e controlada por meio de endpoints HTTP.

## 🛠️ Tecnologias Utilizadas

- **Google BigQuery** – fonte principal de dados.
- **MySQL** – banco de dados relacional utilizado atualmente para persistência intermediária.
- **Java 17 / Spring Boot** – base do desenvolvimento da API.

> Obs.: A arquitetura do projeto permite o uso de outros bancos relacionais, conforme a necessidade da equipe integradora.

