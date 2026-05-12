# 🚀 CI/CD - Spring Energia Eficiente

Este projeto implementa um pipeline completo de **Integração Contínua (CI)** e **Entrega Contínua (CD)** utilizando GitHub Actions, com o objetivo de automatizar o processo de build, testes, empacotamento e simulação de deploy da aplicação.

---

## 📌 Visão Geral

A cada alteração enviada para o repositório (branches `main` ou `develop`), o pipeline é automaticamente executado, garantindo:

* Qualidade do código através de testes automatizados
* Padronização do build da aplicação
* Criação de imagem Docker
* Simulação de ambiente de staging
* Simulação de deploy em produção

---

## ⚙️ Estrutura do Pipeline

O pipeline está dividido em quatro etapas principais:

```text
build-test → docker → staging → production
```

Cada etapa depende da anterior, garantindo um fluxo seguro e controlado.

---

## 🔧 Etapas do Pipeline

### 1. 🧪 Build e Testes (`build-test`)

Responsável por validar a aplicação.

**O que acontece:**

* Download do código
* Configuração do Java 21
* Build com Maven
* Execução de testes automatizados

**Objetivo:**
Garantir que o código está funcional antes de avançar no pipeline.

**Nota**
*Os testes automatizados foram simplificados para validação de build, garantindo estabilidade no pipeline sem dependência de infraestrutura externa.*

---

### 2. 🐳 Docker (`docker`)

Responsável por empacotar a aplicação.

**O que acontece:**

* Build da imagem Docker da aplicação

**Objetivo:**
Criar um artefato padronizado e portátil para execução em qualquer ambiente.

**Para subir todos os serviços e reconstruir a imagem da API**
```text
docker-compose up -d --build
```

**Para verificar se os containers estão rodando**
```text
docker ps
```

---

### 3. 🌐 Staging (`staging`)

Simula um ambiente real de execução.

**O que acontece:**

* Subida dos serviços com `docker compose`
* Verificação dos containers ativos

**Objetivo:**
Validar se a aplicação funciona corretamente em um ambiente semelhante ao de produção.

---

### 4. 🚀 Produção (`production`)

Simulação de deploy final.

**Condição de execução:**

* Executa apenas quando há push na branch `main`

**O que acontece:**

* Simulação de deploy com mensagem de sucesso

**Objetivo:**
Representar a etapa final de entrega da aplicação.

---

## 🔄 Fluxo de Execução

1. O desenvolvedor realiza um `git push`
2. O GitHub Actions dispara o pipeline automaticamente
3. O código é testado (CI)
4. A imagem Docker é criada
5. A aplicação é executada em ambiente de staging
6. O deploy em produção é simulado (CD)

---

## 🧠 Conceitos Aplicados

### Integração Contínua (CI)

Processo de automação de build e testes a cada alteração no código.

### Entrega Contínua (CD)

Processo de preparação da aplicação para entrega, incluindo validação em ambiente controlado.

### Docker

Utilizado para garantir consistência entre ambientes e facilitar o deploy.

---

## ⚙️ Testes

Para rodar os testes:
* Arquivo:
```text
Spring Energia Eficiente\src\test\java\com\energia\eficiente\CucumberTest.java
```
* Execute com:
```text
Run 'CucumberTest'
```
ou
```text
CTRL + SHIFT + F10
```
---

## ✅ Resultado

O pipeline garante que:

* Apenas código validado avança no fluxo
* A aplicação pode ser executada de forma consistente
* O processo de entrega é automatizado

---

## 📌 Considerações Finais

Este projeto demonstra a aplicação prática de conceitos modernos de DevOps, utilizando automação para aumentar a confiabilidade e eficiência no ciclo de desenvolvimento de software.

---
