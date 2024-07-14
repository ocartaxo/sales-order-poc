- `POST /clientes` - Cadastra um novo cliente <br>
```json
// request
{
  "nome": "string",
  "email": "string",
  "documento": {
    "digitos": "string",
    "tipo_documento": "string" // CPF ou CNPJ
  },
  "enderecos": [
    {
      "logradouro": "string",
      "tipo_endereco": "string" // ALL, ENTREGA, COBRANCA
    }
  ]
}
```
```json
// response - 201 CREATED
{
  "clientCode": "UUID",
  "nome": "string",
  "email": "string"
}
```
- `GET /clientes/{uuid}` - Exibe as informações detalhadas do cliente <br>
````json
// response - 200 OK
{
  "nome": "string",
  "email": "string",
  "enderecos": [
    {
      "logradouro": "string",
      "tipo_endereco": "string"
    }
  ]
}
````
- `GET /clientes` - Exibe informações resumidas de todos os clientes cadastrados <br>
````json
// response - 200 OK
[
  {
    "codigo_cliente": "uuid", 
    "nome": "string",
    "email": "string"
  }
]
````
- `PUT /clientes/{uuid}` - Atualiza as informacoes do cliente <br>
````json
// request
{
  "nome": "string",
  "email": "string"
}
````
````json
// response - 200 OK
{
  "codigo_cliente": "uuid",
  "nome": "string",
  "email": "string"
}

````
- `DELETE /clientes/{uuid}` - Deleta um cliente do sistema <br>
```json
// response - 204 NO_CONTENT
```
