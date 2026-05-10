# 🚖 Ride Sharing System (OOP Java Project)

## 📌 Project Title
**Ride Sharing System using Object-Oriented Programming in Java**

---

## 🧑‍🎓 Group Members

- Name: **Sidra**  
- CMS-ID: **023-25-0107**  
- Section: **E**  

---

## 🎯 Project Purpose

The purpose of this project is to simulate a basic ride-sharing system (similar to Uber) using Object-Oriented Programming (OOP) concepts in Java.

This system allows passengers to register, book rides, and view ride history while drivers are automatically assigned and can complete rides. The project demonstrates real-world software design using OOP principles.

---

## 💡 Project Overview

This system is designed to demonstrate how a real-world application can be structured using object-oriented programming. It focuses on modular design, reusability, and interaction between multiple classes.

---

## 🧱 Main Modules / Classes

### 1. User (Abstract Class)
- Base class for Passenger and Driver
- Contains common attributes: id, name, phone
- Demonstrates abstraction and inheritance

### 2. Passenger Class
- Extends User
- Stores ride history
- Allows passengers to book rides and view history

### 3. Driver Class
- Extends User
- Handles driver availability
- Assigns and completes rides

### 4. Ride Class
- Stores ride details:
  - Pickup location
  - Drop location
  - Distance
  - Fare
  - Status

### 5. RideService Class
- Core system logic
- Handles:
  - Ride creation
  - Driver assignment
  - Fare calculation
  - Ride completion

### 6. Main (RideSharingApp)
- Console-based menu system
- Handles user interaction and system flow

---

## 🧠 OOP Concepts Used

- Encapsulation (private fields with controlled access)
- Inheritance (User → Passenger/Driver)
- Polymorphism (method overriding: displayInfo)
- Abstraction (abstract User class)
- Association (Passenger–Ride–Driver relationship)
- Collections (ArrayList for managing data)

---

## ⚙️ How to Run the Project

### Requirements:
- Java JDK 8 or above
- Any IDE (IntelliJ / Eclipse / NetBeans) or terminal

### Run using terminal:
```bash
javac RideSharingApp.java
java RideSharingApp
```

### Run using IDE:
- Open project in IDE
- Run file: RideSharingApp.java

---

## 📁 GitHub Repository

**Repository URL:** https://github.com/sidra56/Ridehandling_System.git

- Upload all `.java` files
- Include this README.md in root directory
- Ensure repository is public or accessible to instructor/TAs

---

## 🎥 Video Demonstration

**Drive Link:** https://drive.google.com/file/d/1lJsSEsggkNwnCUBSjCjMnsUE6Ksm0Ji8/view?usp=drive_link

### Video Requirements Covered:
- Project introduction and purpose
- System explanation (modules/classes)
- Demonstration of working program
- Each group member explanation (if applicable)


---

## 🚀 Conclusion

This project demonstrates a complete object-oriented system design in Java. It shows how real-world systems can be implemented using OOP principles such as inheritance, abstraction, polymorphism, and encapsulation.

