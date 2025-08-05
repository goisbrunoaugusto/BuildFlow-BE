# BuildFlow - Construction Management System

A Spring Boot application for managing construction projects, teams, and activities.

## Database Setup

### Option 1: Using Docker (Recommended)

1. **Start PostgreSQL with Docker Compose:**
   ```bash
   docker-compose up -d
   ```

2. **Verify the database is running:**
   ```bash
   docker-compose ps
   ```

3. **Check database logs:**
   ```bash
   docker-compose logs postgres
   ```

### Option 2: Local PostgreSQL Installation

1. **Install PostgreSQL** on your system
2. **Create the database:**
   ```sql
   CREATE DATABASE buildflow;
   CREATE USER postgres WITH PASSWORD 'postgres';
   GRANT ALL PRIVILEGES ON DATABASE buildflow TO postgres;
   ```

## Application Configuration

The application is configured to connect to PostgreSQL with the following settings:

- **Host:** localhost:5432
- **Database:** buildflow
- **Username:** postgres
- **Password:** postgres

## Running the Application

1. **Start the Spring Boot application:**
   ```bash
   ./gradlew bootRun
   ```

2. **Access the API:**
   - Base URL: http://localhost:8080
   - API Documentation: http://localhost:8080/api/v1

## API Endpoints

### Obras (Works/Projects)
- `GET /api/v1/obras` - List all works
- `POST /api/v1/obras` - Create new work
- `GET /api/v1/obras/{cei}` - Get work by CEI
- `PUT /api/v1/obras/{cei}` - Update work
- `DELETE /api/v1/obras/{cei}` - Delete work

### Equipes (Teams)
- `GET /api/v1/equipes` - List all teams
- `POST /api/v1/equipes` - Create new team
- `GET /api/v1/equipes/{id}` - Get team by ID
- `PUT /api/v1/equipes/{id}` - Update team
- `DELETE /api/v1/equipes/{id}` - Delete team

## Database Schema

The application uses JPA/Hibernate with automatic schema generation (`spring.jpa.hibernate.ddl-auto=update`). Tables will be created automatically when the application starts.

## Development

- **Java Version:** 21
- **Spring Boot:** 3.5.4
- **Database:** PostgreSQL 15
- **Build Tool:** Gradle

## Useful Commands

```bash
# Start database
docker-compose up -d

# Stop database
docker-compose down

# View database logs
docker-compose logs -f postgres

# Connect to database
docker exec -it buildflow-postgres psql -U postgres -d buildflow

# Run tests
./gradlew test

# Build application
./gradlew build
``` 