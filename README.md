# 🧑‍💼 Employee Management System (Java OOP + GUI)

## 📌 Description
This project is a simple Employee Management System developed using Java Object-Oriented Programming (OOP) concepts and Swing GUI.

It allows users to:
- Add employees
- View all employees
- Search employees by ID
- Handle different employee types
---

## 🏗️ Technologies Used
- Java (OOP Concepts)
- Java Swing (GUI)
- ArrayList (Data Storage)
---

## 📂 Project Structure
OOP Project/
│
├── src/
│ ├── app/
│ │ └── Main.java
│ ├── employee/
│ │ ├── Employee.java
│ │ ├── FullTimeEmployee.java
│ │ ├── PartTimeEmployee.java
│ │ ├── Intern.java
│ │ ├── Address.java
│ │ └── Department.java
│ ├── interfaces/
│ │ └── Payable.java
│ ├── services/
│ │ └── EmployeeService.java
│ └── gui/
│ └── EmployeeManagementGUI.java
│
└── README.md

---

## ⚙️ Features

### 👤 Employee Types
- Full-Time Employee
- Part-Time Employee
- Intern

### 💡 Functionalities
- Add Employee
- Show All Employees
- Search by ID (String ID supported like `Ugr/37298/17`)

---

## ▶️ How to Run

### 1. Go to source folder
```bash
cd src
javac app/*.java employee/*.java interfaces/*.java services/*.java gui/*.java

java app.Main

🖥️ GUI Preview
Form to input employee data
Dropdown for employee type
Dynamic salary fields
Output display area
Search functionality
🧠 OOP Concepts Used
Inheritance
Polymorphism
Abstraction
Encapsulation
Interface implementation

🚀 Future Improvements
Add Update & Delete functionality
Store data in file/database
Use JTable for better UI
Add login system
