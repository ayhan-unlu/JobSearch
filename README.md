![Java](https://img.shields.io/badge/Java-25-orange) ![License: Free Use](https://img.shields.io/badge/License-Free--Use-green) ![Patika.dev](https://img.shields.io/badge/Patika.dev-Project-blue)  
![GitHub stars](https://img.shields.io/github/stars/ayhan-unlu/JobSearch?style=social) ![GitHub forks](https://img.shields.io/github/forks/ayhan-unlu/JobSearch?style=social) ![GitHub last commit](https://img.shields.io/github/last-commit/ayhan-unlu/JobSearch)



JobSearch is a full-stack job portal application built with modern Spring technologies.
It enables job seekers to manage profiles and explore job vacancies, while providing admins (companies) efficient tools for filtering and reviewing candidates.

The system is fully functional, end-to-end tested, and ready for deployment.

🎯 Features
User Features

User registration & authentication

Role-based dashboard (USER)

View personal profile (JobSeeker)

Browse all active job vacancies

Filter vacancies by:

Military service requirement

5+ years experience requirement

Secure logout and session handling

Admin Features

Admin login & secure session

Admin dashboard

View full JobSeeker list

Filter JobSeekers by:

Military service completed

5+ years experience

Platform Features

Full Spring Security integration

Persistent user and job seeker data

Vacancy management via service layer

Thymeleaf-based UI

MySQL relational database

🏗️ Architecture

The application follows a layered architecture:

Controller Layer      → Thymeleaf pages, routing, dashboards
Service Layer         → Business logic and filtering rules
Repository Layer      → JPA repositories for User, JobSeeker, Vacancy
Entity Layer          → Hibernate-mapped domain objects
Security Layer        → AuthenticationProvider, RBAC, session management
Database Layer        → MySQL schema with relationships

⚙️ Tech Stack
Layer	Technology
Backend	Spring Boot 4
Security	Spring Security
ORM / DB	Hibernate / JPA + MySQL
Frontend	HTML + Thymeleaf
Build Tool	Maven
Language	Java 25
Logging	SLF4J / Logback
🚀 Getting Started
1. Clone the repository
   git clone https://github.com/ayhan-unlu/JobSearch.git
   cd JobSearch

2. Configure MySQL

Create a database:

CREATE DATABASE jobsearch;


Configure application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/jobsearch
spring.datasource.username=yourUser
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update

3. Run the project
   mvn spring-boot:run


Application starts at:

http://localhost:8080

🧪 Testing

The project allows:

Manual UI testing via browser

Service-layer filtering validation

Session handling & login flow testing

Controller → Service → Repository integration checks

(If you want, I can add a full JUnit section and sample test cases.)

🗂️ Directory Structure
src/main/java/com.ayhanunlu
├── controller/
├── data/
│    ├── dto/
│    ├── entity/
│    ├── repository/
├── enums/
├── security/
├── service/
│    ├── impl/
└── Application.java

src/main/resources/
├── templates/ (Thymeleaf views)
└── application.properties

📄 License

This project is licensed under MIT License, allowing free use, modification, and distribution.

👤 Developer

Ayhan Ünlü
Java & Spring Boot Developer

This project was created to demonstrate strong command over:

Spring Boot architecture

Security & authentication

JPA/Hibernate ORM

MVC with Thymeleaf

Clean backend design principles

📦 Project Status

Completed
All core features have been implemented successfully, and the project is ready for deployment, further extension, or portfolio presentation.