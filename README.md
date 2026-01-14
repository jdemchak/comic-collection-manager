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
    - Search comics by title using case-insensitive partial matching
- Tested with Postman

# Frontend
- Simple HTML + JavaScript interface to interact with the backend
- Features:
    - View all comics
    - Add new comics
    - Update existing comics
    - Delete comics
    - Display number of comics per publisher
- Uses `fetch` API to call the backend endpoints

# Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- HTML, JavaScript
- Postman
- Git & GitHub

# API Endpoints
- GET /api/comics
- GET /api/comics/{isbn}
- POST /api/comics
- PUT /api/comics/{isbn}
- DELETE /api/comics/{isbn}
- GET /api/comics/publisher/{publisher}
- GET /api/comics/publisher/{publisher}/count
- GET /api/comics/publisher/count
- GET /api/comics/search?title={title}