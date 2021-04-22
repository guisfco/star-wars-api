# star-wars-api

### Aplicação

- Requisitos:
  - Java 11
  - MongoDB
  
- Variáveis de ambiente utilizadas no `application.yml`:
  - `DATABASE_HOST` - Caso não esteja definida, por padrão será utilizado `localhost`
  - `DATABASE_PORT` - Caso não esteja definida, por padrão será utilizado `27017`
  
  
### MongoDB

- Necessário:
  - Criar um banco chamado `starwars`
  - Criar uma collection chamada `planet`
  
  
### Documentação

Para a documentação da API foi utilizado o [Swagger](https://swagger.io/tools/swagger-ui/), que poderá ser acessado por `http://localhost:8080/api/swagger-ui.html` (alterar a porta caso necessário)

![swagger](https://user-images.githubusercontent.com/40643343/115797005-45c0dd00-a3a9-11eb-9eca-15b1ca70ff2c.png)


### Cobertura de testes
![cobertura_testes](https://user-images.githubusercontent.com/40643343/115796878-fd092400-a3a8-11eb-89b6-1d8b0a41b61a.png)
