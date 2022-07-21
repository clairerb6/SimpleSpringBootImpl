# restServiceApiTest

This repository was created to validate the necessary skills for the creation of rest services in the new **BCI bank** project.

## Deployment

For run application you must mount all code into eclipse IDE, and generate jar file through export menu or just run hitting `ctrl+F11`  

# Postman collection

All detailed information is inside the postman collection

[link](https://documenter.getpostman.com/view/20957797/UzR1JMTN)

For testing services java application must be run under http://localhost:8080

## Passwords security

The security of stored passwords is given only by the strength of the password. The requirements to save a password are:

* a digit must occur at least once
* a lower case letter must occur at least once
* an upper case letter must occur at least once
* a special character must occur at least once
* no whitespace allowed in the entire string
* anything, at least eight places though

## About duplicate emails
It is not allowed to register an email more than once. Not completing this field or sending an invalid email will not allow its registration

## Considerations when updating data

Since it is a basic project, if a field is omitted when updating, it will be left null in the database, so all fields must be sent if possible. The only one that can be omitted is the password
Phones are updated or inserted depending on whether the id exists within the request body, to avoid user insertion errors.

## Table structure
### Class Diagram
```mermaid
erDiagram
Users ||--o{ Phones : Contains
Users {
    long id PK "id identifier"
    String name
    String email
    String password
    List Phones FK "phones"
    Date created
    Date modified
    Date lastLogin
    String token
    boolean active
}
Phones {
    long id PK "id identifier"
    long number
    int citycode
    int countrycode
}

```

All the request are processed in the same route
## UML diagrams
```mermaid
sequenceDiagram
Postman ->> Java: body request for processing
Java ->> Database: after processing the request it is persisted in db
Database ->> Java: return id of object inserted
Java ->> Postman: show response
```
## State Diagram
```mermaid
stateDiagram-v2
s1: "Postman or client make a request"
s2: "Java process the response and send to databse"
s3: "Database store or respond to java, depend on call"
s4: "Java transform the response to json"
s5: "Postman show the response to client"
[*]-->s1
s1-->s2
s2-->s3
s3-->s4
s4-->s5
s5-->[*]
s5-->s1
```