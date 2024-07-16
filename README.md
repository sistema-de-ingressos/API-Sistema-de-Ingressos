[JAVA__BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRINGBOOT__BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MYSQL__BADGE]: https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white
[DOCKER__BADGE]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[FULLSTACK__URL]: https://github.com/sistema-de-ingressos/Fullstack-TicketFlow
[FRONTEND__URL]: https://github.com/sistema-de-ingressos/Front-End-Sistema-de-Ingressos

<h1 align="center">TicketFlow - Back-End 🎟️</h1>

<div align="center" style="padding-bottom: 6px">

  ![java][JAVA__BADGE] 
  ![springboot][SPRINGBOOT__BADGE] 
  ![mysql][MYSQL__BADGE] 
  ![docker][DOCKER__BADGE]
</div>

<div align="center">

  [Repositório Front-End][FRONTEND__URL] •
  [Repositório Full-Stack][FULLSTACK__URL]
  <div>
      <img src="https://github.com/user-attachments/assets/f4a69739-7381-421d-bd2b-6f318566e6be" height="80" alt="Logo TicketFlow">
  </div>
</div>

## 💬 Sobre

O **TicketFlow** é uma aplicação web full stack desenvolvida para o gerenciamento eficiente de ingressos em eventos. Utilizamos React para o front-end e Spring Boot para o back-end, integrando design, desenvolvimento e gestão de dados.

## 📌 Funcionalidades

- Gerenciamento de eventos e ingressos.
- Segurança robusta para proteção de dados.
- API RESTful para comunicação com o front-end.

## 📍Endpoints da API

Lista de todas as rotas presentes na API

### Clientes
| Método | Endpoint            | Descrição                         |
|--------|---------------------|-----------------------------------|
| POST   | <kbd>/clientes</kbd>         | Salva o cliente no sistema        |
| GET    | <kbd>/clientes/{cpf}</kbd>   | Busca cliente pelo CPF            |

### Eventos
| Método | Endpoint                          | Descrição                               |
|--------|-----------------------------------|-----------------------------------------|
| POST   | <kbd>/eventos</kbd>                        | Salva o evento no sistema               |
| POST   | <kbd>/eventos/{id}/upload-imagem</kbd>     | Faz upload de imagem do evento          |
| GET    | <kbd>/eventos/{id}</kbd>                   | Retorna detalhe do evento               |
| DELETE | <kbd>/eventos/{id}</kbd>                   | Deleta o evento do sistema              |
| GET    | <kbd>/eventos/proximos</kbd>               | Lista os eventos disponíveis recentes   |
| GET    | <kbd>/eventos/destaque</kbd>               | Lista os eventos quase lotados          |
| GET    | <kbd>/eventos/carrinho/{idDoEvento}</kbd>  | Retorna o carrinho de compra            |
| GET    | <kbd>/eventos/buscar</kbd>                 | Busca evento pelo nome                  |

### Ingressos
| Método | Endpoint                        | Descrição                           |
|--------|---------------------------------|-------------------------------------|
| POST   | <kbd>/ingressos</kbd>                    | Salva o ingresso no sistema         |
| GET    | <kbd>/ingressos/qrcode/{idDoIngresso}</kbd> | Retorna o QR Code do ingresso       |
| GET    | <kbd>/ingressos/busca/{cpf}</kbd>        | Busca o ingresso através do CPF do cliente |


## 💻 Como rodar o projeto

Para mais informações de como rodar o **TicketFlow** entre no [Repositório Full-Stack][FULLSTACK__URL]

## 🙎🏻‍ Autor do Back-End

- Raissa Quezia Lopes / [GitHub](https://www.github.com/raissaquezia) - [LinkedIn](https://www.linkedin.com/in/raissa-passos)
