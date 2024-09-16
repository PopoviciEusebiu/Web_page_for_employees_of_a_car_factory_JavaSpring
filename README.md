# Machine Factory Simulation Web Application

This project is a web application designed to simulate the operations of a machine factory. It supports three distinct user roles, allowing users to log in, register, and perform specific operations based on their role. The project is built using **Java Spring** and follows **pre-CRUM concepts**, a **Client-Server System Architecture**, and uses **REST APIs** and **Spring Security** for secure authentication and authorization.

## Features

### 1. User Roles: Three Different Types of Users
- **Employee**:
  - Can perform **CRUD** (Create, Read, Update, Delete) operations on their own data.
  - Access and manage personal records within the system.

- **Regular User**:
  - Can interact with machine data.
  - Perform **CRUD** operations on machine-related records.

- **Admin**:
  - Full control over the application data.
  - Can perform **CRUD** on all users (Employee, Regular Users).
  - Perform **CRUD** on machine data and overall system configurations.

### 2. Authentication & Security
- **Login & Registration System**:
  - Three types of users can register and log in to the system.
  - Each user has access to different functionality based on their role.

- **Spring Security Integration**:
  - Role-based access control using **Spring Security**.
  - Only authenticated users can access protected resources and perform actions based on their assigned roles (Employee, Regular User, Admin).

### 3. Data Management
- **CRUD Operations**:
  - **Employee**:
    - Employees can create, update, view, and delete their own data.
  - **Regular User**:
    - Regular users can perform CRUD operations on machine data.
  - **Admin**:
    - Admins have full control over the system and can perform CRUD on all users (employees, regular users) and machine data.

### 4. Client-Server Architecture
- The application follows the **Client-Server System Architecture**, where:
  - **Frontend**: Handles user interaction and communicates with the backend via REST APIs.
  - **Backend**: Manages business logic, data storage, and REST API services.
