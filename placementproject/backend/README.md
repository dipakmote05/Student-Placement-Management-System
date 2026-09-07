# Placement Portal Backend

Spring Boot 3 / Java 17 REST backend using MySQL. The application runs on port `8080` by default.

## Requirements

- Java JDK 17 (not only JRE)
- Maven 3.8+
- MySQL 8 running on port 3306

## 1. Prepare MySQL

You can let Hibernate create the tables. The configured MySQL user must have permission to create/use the database.

```sql
CREATE DATABASE IF NOT EXISTS placement_portal;
```

## 2. Set database credentials

Do not put your real password in Git or `application.properties`.

Windows Command Prompt:

```bat
set DB_USERNAME=root
set DB_PASSWORD=your_mysql_password
mvn spring-boot:run
```

Windows PowerShell:

```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
mvn spring-boot:run
```

macOS/Linux:

```bash
export DB_USERNAME=root
export DB_PASSWORD='your_mysql_password'
mvn spring-boot:run
```

For a custom connection, set `DB_URL`, for example:

```text
jdbc:mysql://localhost:3306/placement_portal?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
```

## 3. Check that it is running

Open:

- `http://localhost:8080/`
- `http://localhost:8080/actuator/health`

Expected health result: `{"status":"UP"}`.

## Main API endpoints

| Method | URL | Purpose |
| --- | --- | --- |
| POST | `/api/auth/register/student` | Register a student |
| POST | `/api/auth/register/company` | Register a recruiter/company |
| POST | `/api/auth/login` | Validate login credentials |
| POST | `/api/opportunities` | Create an opportunity |
| GET | `/api/opportunities/search` | Search/filter opportunities |
| GET | `/api/opportunities/{id}` | Get opportunity details |
| POST | `/api/applications/apply?studentId=1&opportunityId=1` | Apply |
| GET | `/api/applications/student/{studentId}` | Student application history |
| GET | `/api/applications/opportunity/{opportunityId}` | Recruiter applicant list |
| PATCH | `/api/applications/{studentId}/{opportunityId}/status?status=ACCEPTED` | Change status |

Allowed status values: `PENDING`, `REVIEWED`, `ACCEPTED`, `REJECTED`.

### Register company example

```json
{
  "email": "hr@example.com",
  "password": "password123",
  "companyName": "Example Technologies",
  "industry": "IT",
  "website": "https://example.com",
  "description": "Software company"
}
```

### Create opportunity example

Use the returned company user ID as `companyId`.

```json
{
  "companyId": 1,
  "title": "Java Developer Intern",
  "description": "Spring Boot internship",
  "industry": "IT",
  "location": "Pune",
  "requiredSkills": "Java, Spring Boot, MySQL",
  "stipend": 15000,
  "applicationDeadline": "2026-12-31",
  "maxApplicants": 100
}
```

## Frontend connection

The default allowed frontend origins are ports `3000` and `5173`. Override them when needed:

```powershell
$env:CORS_ALLOWED_ORIGINS="http://localhost:4200"
```

## Common startup errors

- `Port 8080 already in use`: stop the process using 8080 or run with `PORT=8081`.
- `Access denied for user 'root'`: `DB_PASSWORD` is missing or incorrect.
- `Communications link failure`: start MySQL and verify port 3306.
- `mvn` not recognized: install Maven 3.8+ and make sure it is available in `PATH`.

## Important security note

Passwords are BCrypt-hashed. The current login endpoint validates credentials, but it does not issue JWT tokens and the APIs do not yet enforce recruiter/student authorization. Add Spring Security with JWT before production deployment.
