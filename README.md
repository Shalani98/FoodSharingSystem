# 🍽️ Food Sharing System

A full-stack web application built using **Spring Boot** that enables users to share, manage, and request food items efficiently. The system helps reduce food waste by connecting donors and receivers.

---

## 🎥 Demo Video

[▶ Watch Demo Video](foodsharingsystem.mp4)

> If the video does not play directly, download and view it locally.

---

## 🚀 Features

* 👤 User Registration & Login system
* 📦 Add food donations with image upload
* 📍 Location-based food details
* 🤝 Claim food functionality
* 👨‍💼 Admin-level food management
* 🖼️ Image upload support for food items

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Web (REST APIs)
* Spring Data JPA
* Hibernate

### Frontend

* HTML
* CSS
* JavaScript

### Database

* MySQL

### Tools

* Gradle
* Postman
* Git & GitHub

---

## 📁 Project Structure

```id="struct01"
foodsharing/
│
├── controller
├── service
├── repository
├── entity
├── dto
├── resources
└── uploads (for images)
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone repository

```id="clone01"
git clone https://github.com/your-username/foodsharing.git
cd foodsharing
```

---

### 2️⃣ Configure database

```id="db01"
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/food_sharing
    username: root
    password: yourpassword

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

### 3️⃣ Run application

```id="run01"
mvn spring-boot:run
```

---

## 🔗 API Documentation

### 👤 User APIs

| Method | Endpoint          | Description         |
| ------ | ----------------- | ------------------- |
| POST   | `/users/register` | Register a new user |
| POST   | `/users/login`    | Login user          |
| GET    | `/users`          | Get all users       |

---

### 🍲 Food APIs

| Method | Endpoint                 | Description                |
| ------ | ------------------------ | -------------------------- |
| POST   | `/foods/addFood`         | Add food with image upload |
| GET    | `/foods/getFood`         | Get all food items         |
| GET    | `/foods/getFood/{id}`    | Get food by ID             |
| PUT    | `/foods/claim/{id}`      | Claim a food item          |
| DELETE | `/foods/delete/{id}`     | Delete a food item         |
| GET    | `/foods/donor/{donorId}` | Get foods by donor         |

---

## 🖼️ Image Upload Feature

* Food images are stored in local `/uploads` folder
* Image URL stored in database
* Supports multipart file upload via API

---

## 🧪 Testing

* API testing done using Postman
* Manual frontend testing
* Image upload verification

---

## ⚡ Key Learnings

* Building REST APIs with Spring Boot
* File upload handling in Java
* DTO to Entity mapping
* MVC architecture
* MySQL integration
* API design best practices

---




