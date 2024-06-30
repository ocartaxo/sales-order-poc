# Sales Order API

Uma API REST para compras de produtos em uma loja virtual. O sistema permite gerenciar pedidos, produtos e clientes
cadastrados, por consultas pelas rotas disponibilizadas.

| :placard: Vitrine.dev |                                               |
|-----------------------|-----------------------------------------------|
| :sparkles: Nome       | **Sales Order API**                           |
| :label: Tecnologias   | Java, Spring Boot, Spring Data JPA, Lombok H2 |

## Detalhes
A aplicação foi desenvolvida utilizando Java 17 e Spring Boot na versão 3.15 e Gradle para gerenciamento de dependências. 
As operações de banco de dados são feitas através das interfaces disponibilizadas pelo Spring Data JPA e, os dados são
salvos em um arquivo utilizando H2 SQL _engine_. Além disso, o projeto respeita a arquitetura Controller-Service-Repository
e a interação entre essas camadas são feitas por meio de interfaces do Java.

A aplicação foi desenvolvida respeitando os requisitos funcionais abaixo:
- **Cliente**: Para cadastro do cliente são obrigatórios os campos nome, email, documento, tipo de documento (CPF, CNPJ),
endereços, tipo de endereço (cobrança, entrega).
- **Produto**: Para cadastro de produto são obrigatórios os campos categoria, descrição, valor, quantidade em estoque.
- **Pedido de venda**: A venda deve conter os dados do cliente, endereço de entrega e cobrança, os dados de cada item discriminados
individualmente, quantidade, valor unitário e o valor total do pedido.

### Funcionalidades





 