# Student Scholarship Platform - Backend

This is the Spring Boot backend for the Student Scholarship Platform. It provides RESTful APIs for user authentication, scholarship discovery, and application management.

## 🚀 Built With
- **Java 21**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Maven**

## 📂 Features
- **User Authentication**: Secure Login and Registration (Student and Admin roles).
- **Scholarship Management**: Admin portal for full CRUD operations on scholarship listings.
- **Application Tracking**: Real-time tracking of student scholarship applications.
- **CORS Config**: Ready to connect with modern React/Vite frontends.

## 🏃 How to Run
1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/Jayasree000/scholarship-backend.git
    ```
2.  **Open in your IDE** (Eclipse or IntelliJ).
3.  **Run the Application**: 
    Locate `ScholarshipPlatformApplication.java` and select **Run As -> Java Application**.
4.  **Database Console**: 
    Access the H2 Console at `http://localhost:8081/h2-console`.
    *   **JDBC URL**: `jdbc:h2:mem:scholarshipdb`
    *   **User**: `sa`
    *   **Password**: *(leave blank)*

## 🛠 API Endpoints
- **GET** `/api/scholarships` - List all scholarships
- **POST** `/api/users/login` - Authenticate users
- **POST** `/api/scholarships/apply` - Submit a scholarship application
- **PATCH** `/api/scholarships/applications/{id}/status` - Approve/Reject applications (Admin only)
