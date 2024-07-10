# traffic-system
## Getting started

### Prerequisites:

- Java 11
- Spring Boot 2.7.13
- Maven as our project management and comprehension tool
- Docker and Docker Compose

### Running the project:

```bash
    mvn clean install -DskipTests
    docker-compose up --build
```
### Clone the repository

```bash
    git clone https://github.com/SaeedSatari/traffic-system.git
```
### Swagger:
http://localhost:8080/swagger-ui/index.html#/

### How to test the api using swagger:

- Signup with your email, firstname and lastname
- Login using your email and password
- Use the accessToken from the Login response and Authorize using the swagger Authorize button
- Test greeting api, you should get "I'm running secure... ;)" message