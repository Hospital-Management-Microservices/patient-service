# Patient Service — Hospital Management System

## Overview

The **Patient Service** is a standalone RESTful microservice built as part of a Hospital Management System (HMS). It handles all patient-related operations including registration, retrieval, updating, and deletion of patient records.

This service is one of **5 microservices** in the HMS architecture, all accessible through a central **API Gateway**.

---

## System Architecture

```
                        ┌─────────────────┐
                        │   API Gateway   │
                        │   (Port 8080)   │
                        └────────┬────────┘
                                 │
        ┌────────────────────────┼────────────────────────┐
        │                        │                        │
┌───────▼──────┐      ┌──────────▼──────┐      ┌─────────▼───────┐
│   Patient    │      │    Doctor       │      │  Appointment    │
│   Service   │      │    Service      │      │   Service       │
│  (Port 8081)│      │  (Port 8082)    │      │  (Port 8083)    │
└──────────────┘      └─────────────────┘      └─────────────────┘
        │
┌───────▼──────┐
│  Neon.tech   │
│  PostgreSQL  │
│  (patient_db)│
└──────────────┘
```

---

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17+ | Programming Language |
| Spring Boot | 3.2.3 | Application Framework |
| Spring Data JPA | 3.2.3 | Database ORM |
| PostgreSQL (Neon.tech) | — | Cloud Database |
| SpringDoc OpenAPI | 2.3.0 | Swagger UI / API Docs |
| Lombok | 1.18.30 | Boilerplate Reduction |
| dotenv-java | 3.0.0 | Environment Variable Management |
| Maven | — | Build Tool |

---

## Project Structure

```
patient-service/
├── src/
│   └── main/
│       ├── java/com/hms/patientservice/
│       │   ├── PatientServiceApplication.java   ← Entry point
│       │   ├── SwaggerConfig.java               ← API docs config
│       │   ├── controller/
│       │   │   └── PatientController.java        ← REST endpoints
│       │   ├── service/
│       │   │   └── PatientService.java           ← Business logic
│       │   ├── repository/
│       │   │   └── PatientRepository.java        ← DB queries
│       │   ├── model/
│       │   │   └── Patient.java                  ← Entity / DB table
│       │   └── dto/
│       │       └── PatientDTO.java               ← Data Transfer Object
│       └── resources/
│           └── application.properties            ← App configuration
├── .env                                          ← Local only (gitignored)
├── .env.example                                  ← Safe template to share
├── .gitignore
└── pom.xml
```

---

## Database Schema

**Table: `patients`** (auto-created by Hibernate on first run)

| Column | Type | Description |
|--------|------|-------------|
| `id` | BIGINT (PK, AUTO) | Unique patient identifier |
| `first_name` | VARCHAR(100) | Patient first name |
| `last_name` | VARCHAR(100) | Patient last name |
| `date_of_birth` | DATE | Date of birth |
| `gender` | VARCHAR(10) | Gender |
| `email` | VARCHAR(150) | Email (unique) |
| `phone_number` | VARCHAR(15) | Phone number |
| `address` | TEXT | Home address |
| `blood_group` | VARCHAR(5) | Blood group (e.g. O+) |
| `emergency_contact` | VARCHAR(15) | Emergency contact number |
| `is_active` | BOOLEAN | Soft delete flag |
| `created_at` | TIMESTAMP | Auto-set on creation |
| `updated_at` | TIMESTAMP | Auto-updated on change |

---

## API Endpoints

Base URL (direct): `http://localhost:8081`  
Base URL (via gateway): `http://localhost:8080`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/patients` | Register a new patient |
| `GET` | `/patients` | Get all patients |
| `GET` | `/patients/{id}` | Get patient by ID |
| `PUT` | `/patients/{id}` | Update patient details |
| `DELETE` | `/patients/{id}` | Delete a patient |
| `GET` | `/patients/search?name=` | Search patients by last name |

### Example Request Body (POST /patients)

```json
{
  "firstName": "John",
  "lastName": "Silva",
  "dateOfBirth": "1990-05-15",
  "gender": "Male",
  "email": "john.silva@email.com",
  "phoneNumber": "0771234567",
  "address": "123 Colombo Road, Sri Lanka",
  "bloodGroup": "O+",
  "emergencyContact": "0779876543"
}
```

### Example Response (201 Created)

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Silva",
  "dateOfBirth": "1990-05-15",
  "gender": "Male",
  "email": "john.silva@email.com",
  "phoneNumber": "0771234567",
  "address": "123 Colombo Road, Sri Lanka",
  "bloodGroup": "O+",
  "emergencyContact": "0779876543",
  "isActive": true,
  "createdAt": "2026-03-29T08:30:00",
  "updatedAt": "2026-03-29T08:30:00"
}
```

---

## Running Locally

### Prerequisites
- Java 17 or higher
- Maven
- IntelliJ IDEA (recommended)

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/HMS-Microservices-SLIIT/patient-service.git
cd patient-service
```

**2. Set up environment variables**
```bash
# Copy the example file
cp .env.example .env

# Edit .env and fill in your Neon.tech credentials
```

**.env file format:**
```
DB_URL=jdbc:postgresql://your-neon-host/neondb?sslmode=require&channel_binding=require
DB_USERNAME=your_neon_username
DB_PASSWORD=your_neon_password
```

**3. Run the application**

In IntelliJ: Open project → Run `PatientServiceApplication.java`

Or via Maven:
```bash
mvn spring-boot:run
```

**4. Access Swagger UI**
```
http://localhost:8081/swagger-ui.html
```

---

## API Documentation

Once running, full interactive API docs are available at:

| URL | Description |
|-----|-------------|
| `http://localhost:8081/swagger-ui.html` | Swagger UI (direct) |
| `http://localhost:8081/api-docs` | OpenAPI JSON spec |
| `http://localhost:8080/swagger-ui.html` | Swagger UI (via API Gateway) |

---

## Environment Variables

This project uses a `.env` file to keep credentials secure.  
**Never commit your `.env` file to GitHub.**

| Variable | Description |
|----------|-------------|
| `DB_URL` | Full Neon.tech JDBC connection string |
| `DB_USERNAME` | Neon.tech database username |
| `DB_PASSWORD` | Neon.tech database password |

Use `.env.example` as a template — it is safe to commit.

---
