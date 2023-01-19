# Meu Trampo API

- API onde é possível cadastrar serviços para que outra pessoa possa contrata-los
- Também é possível contratar serviços cadastrados por outros usuários

A API está hospedada em https://easy-service-api.fly.dev/

## Como rodar localmente

1. Tenha o Java 17 instalado
2. Tenha o maven 3.8.6 instalado
3. Clone o projeto
4. Na pasta raiz do projeto rode o comando `mvn clean install`
5. Com tudo instalado, basta rodar `mvn spring-boot:run`
6. Para acessa-lo, entrar no link http://localhost:8080/ e vc verá uma mensagem indicando que está tudo certo

### Endpoints disponíveis

- api/job
- api/jobSigned
- api/person