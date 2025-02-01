- `POST /api/produto` - Cadastra um novo produto <br>
```json
// request
{
  "descricao": "string",
  "categoria": "AUTOMOTIVO|ARTESANATO|CELULAR|CAMA|VIDEOGAME|INFORMATICA|ELETRODOMESTICO|LIVRO|PAPELARIA|TV",
  "valor": "double",
  "quantidade_estoque": "integer"
}
```
```json
// response - 201 CREATED
{
  "id": "long",
  "descricao": "string",
  "categoria": "string",
  "valor": "double",
  "quantidade_estoque": "integer"
}
```
- `GET /api/produto/{id}` - Exibe as informações detalhadas do produto <br>
````json
// response - 200 OK
{
  "id": "long",
  "descricao": "string",
  "categoria": "string",
  "valor": "double",
  "quantidade_estoque": "integer"
}
````
- `GET /api/produto` - Exibe informações paginadas de todos produtos cadastrados <br>
````json
// response - 200 OK
{
  "content": [
    {
      "id": "long",
      "descricao": "string",
      "categoria": "string",
      "valor": "double",
      "quantidade_estoque": "integer"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 0,
  "totalElements": 0,
  "size": 5,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 0,
  "first": true,
  "empty": true
}
````
- `PUT /api/produto/{id}` - Atualiza as informacoes do produto <br>
````json
// request
{
  "valor": "double",
  "quantidade_estoque": "integer"
}
````
````json
// response - 200 OK
{
  "id": "long",
  "descricao": "string",
  "categoria": "string",
  "valor": "double",
  "quantidade_estoque": "integer"
}

````
- `DELETE /api/produto/{id}` - Deleta um produto do sistema <br>
```json
// response - 204 NO_CONTENT
```
