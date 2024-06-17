# Projeto: Adesão de linguagens de programação

Fiz este projeto com o máximo de cuidado possível, algumas coisas não estao funcionando
como devieram, devido a minha falta de tempo por conta do meu atual emprego e assuntos particulares. Espero
que possam compreender. Obrigado pela oportunidade!

### Funções disponíveis na aplicação
> * Log In (Não consegui terminar a implementação e testar a tempo).

> * Sign Up (Tambem não consegui testar).

> * Cadastro de um novo usuário.
 
> * Cadastro de uma nova linguagem de programação.

> * Listar um usuário ou ver se seu userName está disponivel.

> * Listar as linnguagens de programção e os usuários vinculadas a mesma.

> * Retornar a quantidade de usuarios vinculados a uma determinada linguagem de programação.

### Tecnologias utilizadas
> * Java JDK versão 17.

> * Spring Boot framework.

> * Maven como gerenciador de dependências.

> * Spring Security.

## Como executar a aplicação

> ### Requests para os usuários:
> * `curl -i localhost:8080/api/users`

> * `GET curl -i localhost:8080/api/users/{language}`

> * `GET curl -i localhost:8080/api/users/{username}/language`

> * `GET curl -i localhost:8080/api/users/checkUsernameAvailability`

> * `POST curl -i "Content-Type: application/json" -d localhost:8080/api/users/addUser "{\"name\": \"John Doe\", \"userName\": \"John\", \"password\": \"teste@123\",
    \"birthDay\": \"1990-01-01\", \"documento\": \"000.000.000-00\", \"email\": \"jhon@gmail.com\", \"phoneNumber\": \"0000000-0000\", \"languages\": \"["1", "2"]"}"`

> ### Requests para as linguagens de programação: 
> * `curl -i localhost:8080/api/programmingLanguages`

> * `GET curl -i localhost:8080/api/programmingLanguages/{id}`

> * `POST curl -i "Content-Type: application/json" -d localhost:8080/api/programmingLanguages/addLanguages "{"title\": \"Java\"}`

## Observação
> * As configurações do banco H2 estão no arquivo application.properties. Username= admin, Password= admin

> * As inserções contidas no arquivo import.sql preenchem o database assim que o projeto é iniciado.

> * Link para o banco H2: localhost:8080/h2-console.


