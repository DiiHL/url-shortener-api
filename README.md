# URL Shortener API

Uma API simples de encurtamento de URLs desenvolvida com **Spring Boot**, criada como projeto pessoal para aprimorar conhecimentos em back-end e boas práticas no desenvolvimento de APIs REST.

Este projeto tem como foco o **núcleo funcional** de um encurtador de URLs, priorizando organização de código, regras de negócio e clareza arquitetural.

---

## 🚀 Funcionalidades

* Criação de links curtos
* Redirecionamento para a URL original utilizando **HTTP 302 (Found)**
* Controle de expiração de links
* Remoção automática de links expirados utilizando **Scheduler do Spring**
* Separação clara de responsabilidades (Controller, Service e Repository)

---

## 🛠️ Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 (Banco de dados em memória)
* Maven

---

## 📌 Endpoints

### 1️⃣ Criar Link Curto

`POST /api/short`

**Body**

```json
{
  "url": "https://exemplo.com/minha-url"
}
```

**Response**

```json
{
  "short_url": "http://localhost:8080/abc123",
  "expireAt": "dd/MM/yyyy HH:mm"
}
```

---

### 2️⃣ Redirecionamento

Acessando diretamente o link curto:

```
GET /{shortUrl}
```

* Redireciona automaticamente para a URL original
* Retorna **HTTP 302 Found**

---

### 3️⃣ Listar Links Ativos

`GET /api/links`

**Response**

```json
[
  {
    "id": "1",
    "original_url": "https://exemplo.com",
    "short_url": "abc123",
    "timeValid": ""
  }
]
```

---

### 4️⃣ Excluir Link Curto

`DELETE /api/links/{shortUrl}`

* Retorna **HTTP 204 No Content**
---

## ⏰ Expiração de URLs

Cada link curto possui um tempo de validade definido.

* Links expirados são **bloqueados no momento do acesso**
* Um processo agendado remove periodicamente os links expirados do banco de dados

Essa abordagem garante consistência mesmo que o scheduler ainda não tenha sido executado.

---

## 🧹 Scheduler

O projeto utiliza o recurso `@Scheduled` do Spring para remover automaticamente URLs expiradas.

```java
@Transactional
@Scheduled(cron = "0 */10 * * * *")
public void cleanExpiredUrls(){
      urlRepository.deleteAllByTimeValidBefore(LocalDateTime.now());
}
```

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com fins de **aprendizado**, com foco em:

* Design de APIs REST
* Validação de regras de negócio
* Boas práticas de código
* Ecossistema Spring Boot

---

## 📂 Status do Projeto

✅ Funcionalidades principais implementadas

---

## 📎 Observações

Sinta-se à vontade para explorar o código, sugerir melhorias ou utilizar o projeto como referência de estudo.
