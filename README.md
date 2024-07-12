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
- Test greeting api, you should get "I'm running secure... ;)" message.

## Application Workflow

1. ### Application Submission (Status: Pending)
    -  The applicant submits the application along with necessary documents.
    -  Status is set to -> Pending.

2. ### Initial Review (Status: In Progress)
    - The supervisor or processing staff reviews the application.
    - If additional documents or information are needed, the status can be changed to -> Awaiting Documents.

3. ### Awaiting Documents (Status: Awaiting Documents)
    - The applicant is notified to provide the required documents.
    - Once the documents are received, the status reverts to -> In Progress.

4. ### Detailed Review (Status: Under Review)
    - The application undergoes a more thorough evaluation (e.g., background checks, validation of information).
    - If issues arise, the status can be changed to -> Rejected with comments.

5. ### Approval or Rejection (Status: Approved or Rejected)
    - If the application meets all criteria, it is marked as -> Approved.
    - If not, the application is marked as -> Rejected, and the applicant is informed with reasons.

6. ### Finalization (Status: Completed)
    - Once approved, the driving license is issued, and the application is marked as -> Completed.
    - Any necessary documentation is provided to the applicant.