# Email Writer Backend

🚀 **Live Frontend Demo:** https://email-writer-react-tbul.onrender.com

Backend service for the Email Writer application. This Spring Boot application integrates with Google's Gemini AI API to generate intelligent email replies in different tones such as **Formal**, **Informal**, and **Friendly**.

## Features

* Generate AI-powered email replies
* RESTful API endpoints
* Gemini AI integration
* Spring Boot architecture
* Chrome Extension support
* React Frontend integration
* Dockerized deployment support

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Web
* Maven

### AI Integration

* Google Gemini API

### Deployment

* Docker
* Render

## Project Structure

```text
email-writer/
│
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.email_writer/
│   │   │       ├── Configuration/
│   │   │       ├── Controller/
│   │   │       ├── Data/
│   │   │       ├── Service/
│   │   │       └── EmailWriterApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   │
│   └── test/
│
├── target/
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
├── README.md
└── .gitignore
```

## API Endpoint

### Generate Email Reply

**POST**

```http
POST /api/email/generate
```

### Request Body

```json
{
  "emailContent": "Thank you for your email.",
  "tone": "formal"
}
```

### Response

```json
{
  "reply": "Thank you for reaching out. I appreciate your email..."
}
```

## Running Locally

### Clone Repository

```bash
git clone <repository-url>
cd email-writer
```

### Configure Gemini API Key

Add the following in `application.properties`:

```properties
gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent
gemini.api.key=YOUR_GEMINI_API_KEY
```

### Start Application

Using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

The server will start on:

```text
http://localhost:8080
```

## Docker Deployment

### Build Image

```bash
docker build -t email-writer .
```

### Run Container

```bash
docker run -p 8080:8080 email-writer
```

## Frontend Repository

The backend serves the React application and Chrome Extension by providing AI-generated email responses through REST APIs.

## Future Improvements

* Email summarization
* Multi-language support
* Custom tone generation
* User authentication
* Reply history tracking
* Rate limiting and caching

## Author

**Yash Fating**

If you find this project useful, consider giving it a ⭐ on GitHub.
