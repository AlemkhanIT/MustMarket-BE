# MustMarket

MustMarket is a Java-based web application built with Spring Boot, featuring real-time communication via WebSocket (STOMP) and user authentication with role-based access control.

## Technologies

- Java
- Spring Boot
- WebSocket (STOMP)
- Authentication
- Role-based access control (Admin, User)

## Features

- Real-time communication using WebSocket (STOMP)
- User authentication
- Role-based access control with two roles:
  - Admin
  - User

## Setup

1. Clone the repository
2. Configure your database settings in `application.properties`

## Usage

There are two ways to launch the application:

1. For development:
mvn spring-boot:run

2. For production (using Maven):
mvn clean package

java -jar target/mustmarket-0.0.1-SNAPSHOT.jar
Replace `mustmarket-0.0.1-SNAPSHOT.jar` with the actual name of your JAR file if different.
