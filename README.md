# comic-collection-manager
Backend Spring Boot application for managing and tracking a personal comic book collection using REST APIs.

# Features
- CRUD operations for comics (Create, Read, Update, Delete)
- Persistent storage using MySQL and JPA
- RESTful API design
- Pagination support for large datasets
- Bean Validation for POST and PUT endpoints to ensure valid input
- Custom exception handling for error scenarios (ComicNotFound, DuplicateComic, validation errors)
- Custom queries:
    - Find comics by publisher
    - Count comics by a single publisher
    - Count comics for all publishers
- Tested with Postman

# Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman
- Git & GitHub

# API Endpoints
- GET /comics
- GET /comics/{isbn}
- POST /comics
- PUT /comics/{isbn}
- DELETE /comics/{isbn}
- GET /comics/publisher/{publisher}
- GET /comics/publisher/{publisher}/count
- GET /comics/publisher/count