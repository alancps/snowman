
# snowman
 API Tourist Spot - Snowman
 https://touristspotsnowmanapi.azurewebsites.net/swagger-ui.html
 
Para testar você pode utilizar o seguinte comando  docker:

    docker-compose up --build --force-recreate
    Para acessar:
    localhost:8081/swagger-ui.html
Ou 

    mvn clean install 
    java -jar .\target\snowman-0.0.1-SNAPSHOT.jar

   Nesta opção você deverá ter o banco mysql instalado. A configuração de acesso encontra-se no arquivo application.properties. Altere somente o usuário e senha.

Já existe um usuário pré-cadastrado e que pode ser feito o login pelo endpoint /auth/login

    {
      "email": "alan.ecomp@gmail.com",
      "password": "123456"
    }
   Pegue o accessToken e tokenType  para fazer a autorização.

O Postman Collection encontra-se no repositório.