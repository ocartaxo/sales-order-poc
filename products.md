- `POST /produtos` - Cadastra um novo produto
````json
// request
{
  "descricao": "string", 
  "categoria": "string",
  "valor": "long",
  "quantidade_estoque": "int"
}
````
````json
// response - 201 CREATED
{}
````

<br>

- `GET /produtos` - Lista todos os produtos disponíveis
````json
// response - 200 OK
[
  {
    "descricacao": "string",
    "categoria_produto": "string",
    "valor": "long"
  }
]
````

<br>

- `GET /produtos/{id}` - Exibe as informacoes de um produto
````json
// response - 200 OK
{
  "id": "long",
  "descricacao": "string",
  "categoria_produto": "string",
  "valor": "long",
  "quantidade_estoque": "int"
}
````


<br>

- `PUT(ou PATCH) /produtos/{id}` - Atualiza informações do produto de id correspondente
````json
// request 
{
  "valor": "long",
  "quantidade_estoque": "int"
}
````
````json
// response - 200 OK
{
  "id": "long",
  "descricacao": "string",
  "categoria_produto": "string",
  "valor": "long",
  "quantidade_estoque": "int"
}
````

<br>

- `DELETE /produtos/{id}` - Deleta o produto de id correspondente
````json
// response - 204 NO_CONTENT
{}
````