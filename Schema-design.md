# Smart Clinic Management System – MySQL Schema Design

This document outlines the schema design for the Smart Clinic Management System. The system supports Doctors, Patients, Appointments, Prescriptions, and Authentication using JWT.

## 📊 Tables Overview

We have defined the following tables:

- `Doctor`
- `Patient`
- `Appointment`
- `Prescription`
- `User` (for authentication)
- `Speciality`

---

## 1. `Doctor` Table

| Column         | Data Type     | Constraints                     |
|----------------|---------------|----------------------------------|
| id             | INT           | PRIMARY KEY, AUTO_INCREMENT     |
| name           | VARCHAR(100)  | NOT NULL                        |
| email          | VARCHAR(100)  | UNIQUE, NOT NULL                |
| speciality_id  | INT           | FOREIGN KEY REFERENCES Speciality(id) |
| availableTimes | TEXT          | JSON string of available slots  |
| password       | VARCHAR(255)  | NOT NULL (encrypted)            |

---

## 2. `Patient` Table

| Column     | Data Type     | Constraints                     |
|------------|---------------|----------------------------------|
| id         | INT           | PRIMARY KEY, AUTO_INCREMENT     |
| name       | VARCHAR(100)  | NOT NULL                        |
| email      | VARCHAR(100)  | UNIQUE, NOT NULL                |
| phone      | VARCHAR(15)   |                                 |
| password   | VARCHAR(255)  | NOT NULL (encrypted)            |

---

## 3. `Appointment` Table

| Column          | Data Type     | Constraints                                |
|------------------|---------------|---------------------------------------------|
| id               | INT           | PRIMARY KEY, AUTO_INCREMENT                |
| doctor_id        | INT           | FOREIGN KEY REFERENCES Doctor(id)          |
| patient_id       | INT           | FOREIGN KEY REFERENCES Patient(id)         |
| appointmentTime  | DATETIME      | NOT NULL                                   |
| status           | VARCHAR(20)   | DEFAULT 'BOOKED' (BOOKED, CANCELLED, DONE) |

---

## 4. `Prescription` Table

| Column         | Data Type     | Constraints                          |
|----------------|---------------|---------------------------------------|
| id             | INT           | PRIMARY KEY, AUTO_INCREMENT          |
| appointment_id | INT           | FOREIGN KEY REFERENCES Appointment(id) |
| doctor_id      | INT           | FOREIGN KEY REFERENCES Doctor(id)    |
| patient_id     | INT           | FOREIGN KEY REFERENCES Patient(id)   |
| details        | TEXT          | NOT NULL                             |
| created_at     | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP            |

---

## 5. `User` Table (for login)

| Column     | Data Type     | Constraints                     |
|------------|---------------|----------------------------------|
| id         | INT           | PRIMARY KEY, AUTO_INCREMENT     |
| email      | VARCHAR(100)  | UNIQUE, NOT NULL                |
| password   | VARCHAR(255)  | NOT NULL                        |
| role       | ENUM('DOCTOR', 'PATIENT', 'ADMIN') | NOT NULL  |

---

## 6. `Speciality` Table

| Column     | Data Type     | Constraints                      |
|------------|---------------|-----------------------------------|
| id         | INT           | PRIMARY KEY, AUTO_INCREMENT      |
| name       | VARCHAR(100)  | UNIQUE, NOT NULL                 |

---

## 🔁 Relationships

- One `Doctor` has many `Appointments`
- One `Patient` has many `Appointments`
- One `Appointment` can have one `Prescription`
- `Doctor` and `Patient` login credentials are stored in `User` for role-based login
- `Doctor.speciality_id` refers to `Speciality(id)`

---

Let me know if you'd like this pushed to GitHub or connected to actual Spring Boot entities.
