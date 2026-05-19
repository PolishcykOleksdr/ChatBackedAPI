# Chat System

HTTP API for users, chats, and messages.

## Requirements

- Java 21
- Maven
- PostgreSQL running on `localhost:5432`

Default database settings are in `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: rootroot
server:
  port: 9000
```

## Run

```bash
mvn spring-boot:run
```

The server starts on:

```text
http://localhost:9000
```

## Run With Docker

```bash
docker compose up --build
```

The API will be available at:

```text
http://localhost:9000
```

To stop containers:

```bash
docker compose down
```

## API Examples

Create user:

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"username": "user_1"}' \
  http://localhost:9000/users/add
```

Create chat:

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name": "chat_1", "users": [1, 2]}' \
  http://localhost:9000/chats/add
```

Send message:

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"chat": 1, "author": 1, "text": "hi"}' \
  http://localhost:9000/messages/add
```

Get user chats:

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"user": 1}' \
  http://localhost:9000/chats/get
```

Get chat messages:

```bash
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"chat": 1}' \
  http://localhost:9000/messages/get
```
