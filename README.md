# 🏥 HealthTrack — Spring Boot JPA

A Spring Boot application demonstrating advanced Spring Data JPA concepts including entity relationships, cascading, fetch types, JPQL projections, transactional operations, and N+1 query optimization with PostgreSQL.

---

## ☁️ Core Concepts Overview

| Concept | Description |
|---------|-------------|
| Entity Relationships | `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany` |
| Cascading | `CascadeType.ALL`, `orphanRemoval` for lifecycle management |
| Fetch Types | `FetchType.LAZY` and `FetchType.EAGER` |
| JPQL Projections | Interface-based and class-based DTO projections |
| Modifying Queries | `@Modifying` + `@Transactional` for bulk updates |
| Transactions | `@Transactional` with dirty checking |
| N+1 Optimization | `LEFT JOIN FETCH` to avoid extra queries |
| Audit Timestamps | `@CreationTimestamp` for auto-managed fields |

---

## 🛠️ What I Built

### 1. Entity Relationships
- `Patient` → `Insurance` — `@OneToOne` with `CascadeType.ALL` and `orphanRemoval`
- `Patient` → `Appointment` — `@OneToMany` with `CascadeType.ALL`
- `Appointment` → `Patient`, `Doctor` — `@ManyToOne`
- `Department` → `Doctor` — `@OneToOne` (head doctor)
- `Department` → `Doctor` — `@ManyToMany`
- `Doctor` → `Appointment` — `@OneToMany`

### 2. JPQL Projections
- `IPatientInfo` — interface-based projection
- `CPatientInfo` — class-based projection using `new` keyword
- `BloodGroupStats` — aggregation query with `GROUP BY` and `ORDER BY`

### 3. Transactional Operations
- Dirty checking — modifying entity in `@Transactional` context auto-saves
- `@Modifying` — bulk update patient name by ID
- `removeInsuranceOfAPatient` — sets insurance to null, triggers cascade delete

### 4. N+1 Query Optimization
- `LEFT JOIN FETCH` in `getAllPatientsWithAppointments()` — fetches patients and appointments in single query

---

## 🔧 Core Concepts Demonstrated

### Entity Relationships
```java
// OneToOne with Cascade
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
@JoinColumn(name = "patient_insurance", unique = true)
private Insurance insurance;

// OneToMany
@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private Set<Appointment> appointments = new HashSet<>();

// ManyToMany
@ManyToMany
private Set<Doctor> doctors = new HashSet<>();
```

### JPQL Projections
```java
// Interface-based projection
@Query("select p.id as id, p.name as name, p.email as email from Patient p")
List<IPatientInfo> getAllPatientsInfo();

// Class-based projection
@Query("select new com.springboot.healthtrack.dto.CPatientInfo(p.id, p.name) from Patient p")
List<CPatientInfo> getAllPatientsInfoConcrete();

// Aggregation query
@Query("select new com.springboot.healthtrack.dto.BloodGroupStats(p.bloodGroup, COUNT(p)) from Patient p group by p.bloodGroup order by COUNT(p) DESC")
List<BloodGroupStats> getBloodGroupStats();
```

### Modifying Query
```java
@Transactional
@Modifying
@Query("UPDATE Patient p set p.name = :name where p.id = :id")
int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);
```

### N+1 Optimization
```java
@Query("select p from Patient p LEFT JOIN FETCH p.appointments")
List<Patient> getAllPatientsWithAppointments();
```

---

## 🧪 Tests

- `PatientServiceTest` — tests JPQL queries, projections and `LEFT JOIN FETCH`
- `InsuranceTests` — tests insurance assignment, removal and appointment creation

---

## 🚀 Quick Start

**Prerequisites:** Java 21+, Maven, PostgreSQL

```bash
git clone https://github.com/MansiArora-dev/health-track.git
cd health-track
```

**Setup PostgreSQL:**
1. Create database: `CREATE DATABASE <your_database_name>;`
2. Create `application-local.properties` in `src/main/resources/` with your credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<your_database_name>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
```
> ⚠️ Add `application-local.properties` to `.gitignore` to avoid committing credentials

**Using IntelliJ IDEA (Recommended):**
1. Open project in IntelliJ
2. Run → **Edit Configurations**
3. Environment variables: `SPRING_PROFILES_ACTIVE=local`
4. Click **Run ▶️**

**Using Maven:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

---

## 📂 Project Structure
```
src/main/java/com/springboot/healthtrack/
├── dto/
│   ├── BloodGroupStats.java        # Aggregation projection
│   ├── CPatientInfo.java           # Class-based projection
│   └── IPatientInfo.java           # Interface-based projection
├── entity/
│   ├── type/
│   │   └── BloodGroupType.java     # Blood group enum
│   ├── Appointment.java            # ManyToOne relationships
│   ├── Department.java             # OneToOne + ManyToMany
│   ├── Doctor.java                 # OneToMany relationship
│   ├── Insurance.java              # OneToOne inverse side
│   └── Patient.java                # OneToOne + OneToMany owning side
├── repository/
│   ├── AppointmentRepository.java
│   ├── DepartmentRepository.java
│   ├── DoctorRepository.java
│   ├── InsuranceRepository.java
│   └── PatientRepository.java      # JPQL queries and projections
└── service/
├── impl/
│   ├── AppointmentServiceImpl.java
│   ├── InsuranceServiceImpl.java
│   └── PatientServiceImpl.java
├── AppointmentService.java      # Service interface
├── InsuranceService.java        # Service interface
└── PatientService.java          # Service interface
src/main/resources/
├── application.properties          # App configuration
└── data.sql                        # Seed data
src/test/
├── InsuranceTests.java             # Insurance and appointment tests
└── PatientServiceTest.java         # Patient query tests
```
---

## 💻 Technologies

- **Java 21** | **Spring Boot** | **Maven**
- **Spring Data JPA** | **PostgreSQL** | **Hibernate**

---

## 🌟 Key Takeaways
- **Cascade Operations** — Parent entity manages child lifecycle automatically
- **Fetch Strategies** — LAZY loading avoids unnecessary DB queries
- **DTO Projections** — Fetch only required fields from DB
- **Dirty Checking** — Hibernate auto-detects and saves entity changes in transaction
- **N+1 Prevention** — `LEFT JOIN FETCH` fetches related data in single query

---

## 👩‍💻 Developer
**Mansi Arora** — Software Engineer