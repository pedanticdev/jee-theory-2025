# Running the Application

## Prerequisites
- Java 21
- Any Java IDE (IntelliJ IDEA, Eclipse, or VS Code)
- Maven (optional - project includes Maven wrapper)

## Quick Start
1. Clone the repository

2. Start the application:
   ```bash
   ./mvnw clean package payara-micro:dev
   ```

The application will start on port 8081. Access it at `http://localhost:8081`. The application includes an automated cleanup mechanism that ensures port 8081 is always available for Payara Micro deployment.

## Available Endpoints

The application exposes these REST endpoints:

- `GET /resources/hello` - Basic hello endpoint
- `GET /resources/hello/greet/{name}` - Personalized greeting
- `GET /resources/todo` - Fetch all todos
- `POST /resources/todo` - Create a new todo

For the POST request, use this JSON structure:
```json
{
  "todo": "Your todo item",
  "dueDate": "YYYY-MM-DD"
}
```

The project includes an IntelliJ HTTP request file (`.http`) with ready-to-use endpoint examples. You can also use any HTTP client of your choice (Postman, curl, etc.) to interact with these endpoints.

