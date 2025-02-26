## About
This is very simple, very small project I put together to POC some east/west communication within a modulith environment. 
Basically it's a test project to test different solutions to that problem.
Additionally, I've been using it to play around with MapStruct to map between entities and JSON request/response objects.

It is a REST API, using JSONAPI spec, for managing people and their pets. It is built using Spring Boot and Spring Data JPA.

As this was created as a POC it was not built using TDD, the approach I would take to write production ready code. I have added some basic test coverage.  


## Modules
- **audit**: This is an audit module which logs requests to resources to the database.
- **people**: This is a module containing 2 basic resources (Person and Pet) and all the necessary logic to manage them.
- **shared**: Anything that is required to be a shared resource between modules lives here.

In its current state it has event classes in the shared module. This keeps the coupling between modules to a minimum.
The idea being that `shared` could be extracted into a package and the `audit` and `people` modules could be extracted into microservices at a later point.

## Running
It uses an in memory DB so it has no dependencies other and a JDK, it was built using Java 21.

Other than that, run it in an IDE or use the command:
```shell
./gradlew bootRun
```

## Endpoints
`localhost:8080/api/people`
- **GET /people**: Get all people.
- **GET /people/{id}**: Get a person by ID.
- **POST /people**: Create a person.
Example payload (relationships is optional):
```json
{
  "type": "people",
  "attributes": {
    "name": "Jane Doe"
  },
  "relationships": {
    "pets": [
      {
        "id": 4
      }
    ]
  }
}
```
Example response:
```json
{
  "id": 2,
  "attributes": {
    "name": "Jane Doe"
  },
  "relationships": {
    "pets": null
  }
}
```
`localhost:8080/api/pets`
- **GET /pets**: Get all pets.
- **GET /pets/{id}**: Get a pet by ID.
- **POST /pets**: Create a pet.
Example payload:
```json
{
  "type": "pets",
  "attributes": {
    "name": "Buddy"
  },
  "relationships": {
    "owner": {
      "data": {
        "type": "person",
        "id": 1
      }
    }
  }
}
```
Example response:
```json
{
  "id": 4,
  "relationships": {
    "owner": {
      "data": {
        "id": 1,
        "attributes": {
          "name": "Jane Doe"
        },
        "relationships": {
          "pets": null
        }
      }
    }
  },
  "attributes": {
    "name": "Buddy"
  }
}
```
`localhost:8080/api/audit`
- **GET /audit**: Get all audit logs.
Example response:
```json
[
  {
    "id": 1,
    "attributes": {
      "action": "CREATE",
      "entityId": 1,
      "entityType": "Person"
    }
  },
  {
    "id": 2,
    "attributes": {
      "action": "CREATE",
      "entityId": 2,
      "entityType": "Person"
    }
  }
]
```