# [Teste técnico Ekan] spring boot api

## Api de cadastro de beneficiarios de planos de saude

### Desenvolvido com: ###
1. Java 17
2. Spring Boot 3.*
3. Spring Security
4. Swagger/OpenApi
4. h2

### Utilização da Api: ###

Para executar a aplicação use o comando abaixo 

```
mvn spring-boot:run
```

Para começar a usar a api crie um usuário com o payload a seguir: `http://localhost:8080/api/auth/register`

Exemplo:
```
{
    "username": "<user>",
    "password": "<senha>"
}
```

Faça login na rota com o mesmo payload acima: `http://localhost:8080/api/auth/login`

Será devolvido um token para usar nos outros recursos

Exemplo:
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd1c2VyIiwiaWF0IjoxNzI3NDAwNjQ1LCJleHAiOjE3Mjc0MzY2NDV9.WDgICCL2OAIvztAs6f0S2447pnOHYB8ivrx7mt5RWJI"
}
```

Consulte os outros recursos da API via Swagger: `http://localhost:8080/swagger-ui/index.html`

