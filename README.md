<br />
<div align="center">
<h3 align="center">PRAGMA POWER-UP</h3>
  <p align="center">
    In this challenge you are going to design the backend of a system that centralizes the services and orders of a restaurant chain that has different branches in the city.
  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Recommended Tools
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Installation

1. Clone the repository
2. Change directory
   ```sh
   cd power-up-arquetipo-v3
   ```
3. Create a new database in MySQL called powerup
4. Update the database connection settings
   ```yml
   # src/main/resources/application-dev.yml
   spring:
      datasource:
          url: jdbc:mysql://localhost/powerup
          username: root
          password: <your-password>
   ```
5. After the tables are created execute src/main/resources/data.sql content to populate the database
6. Open Swagger UI and search the /auth/login endpoint and login with userDni: 123, password: 1234

<!-- USAGE -->
## Usage

1. Right-click the class PowerUpApplication and choose Run
2. Open [http://localhost:8091/swagger-ui/index.html](http://localhost:8091/swagger-ui/index.html) in your web browser

<!-- ROADMAP -->
## Tests

- Right-click the test folder and choose Run tests with coverage

## Proyect Structure

### Configuration Layer

It is the outermost layer and is responsible for security configuration using Spring Security and JWT, class assignment, dependency injection, exception usage and bean configurations.

### Adapters Layer

This layer contains two layers: the driven layer and the driven layer, which are responsible for all reception and adaptation by the client or server.

#### Drivin Layer

It is the user's entry point when connecting to the application, it allows the user to interact with the api.

### Domain Layer

It is the most internal module of the architecture, it belongs to the domain layer and encapsulates the business logic and rules.

## Entry Points

### Restaurant Controller

![image](https://github.com/Nirsch95/SmallSquareMicro/assets/37886668/d485f5f1-1a00-485f-8c38-740fb6d29212)

- Create Restaurant

![image](https://github.com/Nirsch95/SmallSquareMicro/assets/37886668/d1de4e1b-4992-4e24-8e2a-1f8b8a201f07)

### Dish Controller

![image](https://github.com/Nirsch95/SmallSquareMicro/assets/37886668/3df8603b-ceec-4e63-a65b-5dd08df687f0)

- Create Dish

![image](https://github.com/Nirsch95/SmallSquareMicro/assets/37886668/cc54da26-6ea3-4bc6-aef9-f940cffcad4c)

- Update Dish

![image](https://github.com/Nirsch95/SmallSquareMicro/assets/37886668/2b68b7a8-ae3a-4254-a3da-9251b013ed4d)

### Category Controller

![image](https://github.com/Nirsch95/SmallSquareMicro/assets/37886668/a6525668-20b7-4500-8270-ec63da3419f9)

- Create Category

![image](https://github.com/Nirsch95/SmallSquareMicro/assets/37886668/9f90ba26-0bea-458d-b00a-f374a8954a5f)

## Author

[@Nirsch95](https://github.com/Nirsch95) - Andrés Castro :wolf:
