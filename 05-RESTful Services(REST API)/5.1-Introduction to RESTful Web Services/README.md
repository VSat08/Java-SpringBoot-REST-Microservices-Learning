# 05-5.1-Introduction to RESTful Web Services

## Introduction

Welcome to **5.1 - Introduction to RESTful Web Services** 🌟! If you’re new to coding, this is your first step into the world of REST APIs! Based on the "Introduction to RESTful Web Services" lecture from the Udemy course "Mastering Java + Spring Boot: REST APIs and Microservices," we’ll explore what RESTful web services (or REST APIs) are and why they’re awesome. Think of them as a way for apps to chat with each other—like a weather app asking a server, "What’s the forecast?" We’ll use a weather app example to understand REST, then build a simple "Hello Spring" service in Spring Boot. Let’s get started and make apps talk! 🚀

---

## Table of Contents

1. [What Are RESTful Web Services?](#1-what-are-restful-web-services)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why They’re Great](#12-why-theyre-great)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Understanding the Problem](#21-understanding-the-problem)
   - [2.2 How REST Works](#22-how-rest-works)
   - [2.3 Building a Simple REST Service](#23-building-a-simple-rest-service)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 The Weather App Example](#31-the-weather-app-example)
   - [3.2 Creating a "Hello Spring" REST Service](#32-creating-a-hello-spring-rest-service)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for REST](#41-best-practices-for-rest)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 What’s Next](#53-whats-next)

---

## 1. What Are RESTful Web Services?

### 1.1 Definition and Purpose

Let’s break it down: RESTful web services are a way for apps to talk over the internet!

- **Definition**: REST (Representational State Transfer) is a lightweight method for apps (clients) to request and receive data from other apps (servers) using HTTP.
- **Purpose**: Solves problems like:
  - How do I get weather data for my app?
  - How do apps share info easily?

#### Real-World Analogy

Think of REST as a waiter: your app orders "weather for London" (request), and the server delivers the forecast (response)—simple and fast!

### 1.2 Why They’re Great

- **Easy Connection**: Uses HTTP (the web’s language)—no fancy setup.
- **Any Language**: Clients and servers can use whatever coding language they want.
- **Flexible Data**: Get data in JSON (modern and popular) or XML—your choice.

#### Example Benefit

Your weather app asks `openweathermap.org` for London’s forecast via REST—it gets JSON data like temperature and humidity, instantly!

### 1.3 Key Terms for Beginners

Your newbie glossary—explained simply:

| Term         | Meaning                                          | Example              |
| ------------ | ------------------------------------------------ | -------------------- |
| **REST**     | Way apps talk using HTTP                         | `GET /weather`       |
| **API**      | Interface for apps to share services             | Weather API          |
| **HTTP**     | Web protocol for requests/responses              | `http://example.com` |
| **Client**   | App asking for data                              | Your weather app     |
| **Server**   | App giving data                                  | Weather service      |
| **Endpoint** | Specific URL for a service                       | `/weather/london`    |
| **JSON**     | Popular data format (JavaScript Object Notation) | `{"temp": 20}`       |

---

## 2. Learning Roadmap

Your path to REST mastery!

### 2.1 Understanding the Problem

- **What You’ll Learn**: Why apps need REST (e.g., weather data).
- **Goal**: Grasp the client-server idea.

### 2.2 How REST Works

- **What You’ll Learn**: How REST uses HTTP, languages, and data formats.
- **Goal**: Know the REST basics.

### 2.3 Building a Simple REST Service

- **What You’ll Do**: Create a "Hello Spring" service in Spring Boot.
- **Goal**: Make your first REST API.

---

## 3. Practical Demonstration

Let’s see REST in action with examples!

### 3.1 The Weather App Example

- **Problem**: Build a client app to show a city’s weather report.
- **Architecture**:
  - **Client**: Your weather app (asks for data).
  - **Server**: External weather service (e.g., `openweathermap.org`) gives data.
- **How It Works**:
  1. **Connection**: Client uses REST API calls over HTTP (e.g., `https://api.openweathermap.org/data/weather?city=London`).
  2. **Languages**: Client can be JavaScript, server can be Java—REST doesn’t care!
  3. **Data Format**: Server responds with JSON (e.g., `{"temp": 20, "humidity": 80}`) or XML.
- **Real Example**:

  - Visit `openweathermap.org`, see their API docs.
  - Call `https://api.openweathermap.org/data/weather?lat=51.51&lon=-0.13&appid=YOUR_KEY`.
  - Get JSON: temperature, humidity, etc.

- **What’s Happening**:
  - Client requests via an endpoint; server responds with weather data.

> [!NOTE]
> REST connects your app to services like a weather forecast—easy peasy!

- **More Examples**:
  - **Currency Converter**: Client asks `GET /convert?from=USD&to=INR&amount=100`, gets INR value.
  - **Movie Tickets**: Client asks `GET /movies?city=NY`, gets showtimes.

### 3.2 Creating a "Hello Spring" REST Service

- **Steps**:

  1. **Create Project**:
     - In Eclipse, go `File > New > Spring Starter Project`.
     - Name: `restdemo`.
     - Type: Maven, Java, defaults.
     - Dependencies: `Spring Web` (for REST), `Spring Boot DevTools` (from [4.6](#4.6---spring-boot-starters-and-spring-dev-tools)).
     - Click "Next" > "Finish"—wait for build.
  2. **Add Controller**:

     - In `src/main/java/com.example.restdemo`, right-click > `New > Class`.
     - Name: `MyRestController`.
     - Add `@RestController` annotation:

       ```java
       package com.example.restdemo;

       import org.springframework.web.bind.annotation.RestController;
       import org.springframework.web.bind.annotation.GetMapping;

       @RestController
       public class MyRestController {
       }
       ```

  3. **Create Service**:
     - Add a method with an endpoint:
       ```java
       @GetMapping("/hello")
       public String sayHello() {
           return "Hello Spring";
       }
       ```
     - **Optional**: Add a base path with `@RequestMapping`:
       ```java
       @RestController
       @RequestMapping("/api")
       public class MyRestController {
           @GetMapping("/hello")
           public String sayHello() {
               return "Hello Spring";
           }
       }
       ```
  4. **Run It**:
     - Right-click `RestdemoApplication.java` > `Run As > Spring Boot App`.
     - Console: "Tomcat started on port(s): 8080," "LiveReload server running."
  5. **Test It**:
     - Open browser (e.g., Firefox), visit:
       - `localhost:8080/hello` (without `/api`).
       - Or `localhost:8080/api/hello` (with `/api`).
     - See "Hello Spring" on the page!
     - Root (`localhost:8080`) shows a "Whitelabel Error Page" (no mapping).

- **What’s Happening**:
  - `@RestController` makes it a REST service; `@GetMapping` sets the endpoint; returns plain text.

> [!TIP]
> Your first REST API is live—say "Hello Spring" at `localhost:8080/hello`!

---

## 4. Practical Application

Let’s make REST stick with tips and practice!

### 4.1 Best Practices for REST

- **Clear Endpoints**: Use simple names (e.g., `/hello`, `/weather`).
- **Use HTTP**: Stick to `GET` for fetching data—matches REST style.
- **Pick JSON**: It’s modern and easy—use it over XML when possible.
- **Test Early**: Check endpoints in a browser—see results fast.

### 4.2 Common Mistakes to Avoid

- **Wrong Dependency**: No `Spring Web`? No REST—check `pom.xml`!
- **Bad Endpoint**: Typo in `@GetMapping("/helo")`? Fix to `/hello`!
- **No Reload**: Forgot DevTools? Restart manually—add it!
- **Root Confusion**: `localhost:8080` fails? Use your endpoint (e.g., `/hello`).

### 4.3 Hands-On Exercises

Try these to master REST:

1. **Test Hello**:
   - Visit `localhost:8080/hello`—screenshot "Hello Spring."
2. **Change Message**:
   - Edit `sayHello()` to return "Hi REST!"—save, retest.
3. **Add Endpoint**:
   - Add `@GetMapping("/bye")` with "Goodbye REST"—test `localhost:8080/bye`.
4. **Use /api**:
   - Add `@RequestMapping("/api")` to the class, test `localhost:8080/api/hello`.
5. **Break It**:
   - Remove `@RestController`, rerun—does `/hello` still work?

> [!TIP]
> These tasks make REST yours—small steps, big wins!

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

Level up with these:

- **REST Basics**: [restfulapi.net](https://restfulapi.net/) - REST explained.
- **Spring Docs**: [spring.io/guides/gs/rest-service](https://spring.io/guides/gs/rest-service/) - Build REST in Spring.
- **OpenWeather API**: [openweathermap.org/api](https://openweathermap.org/api) - Try a real API.
- **HTTP Guide**: [developer.mozilla.org/en-US/docs/Web/HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP) - Learn HTTP.

### 5.2 Summary of Key Takeaways

- **REST**: Apps talk via HTTP with endpoints (e.g., `/hello`).
- **How It Works**: Client requests, server responds—language-independent, JSON preferred.
- **Your Service**: Built a "Hello Spring" REST API with Spring Boot.
- **Names**: REST API = RESTful Web Service = REST Service—all the same!

> [!TIP]
> You’re a REST rookie now—your app’s chatting already!

### 5.3 What’s Next


- **5.2 - Introduction to JSON, HTTP, and Postman Client**: Learn JSON basics, HTTP details, and test with Postman.
- **Later**: JSON binding, path variables, full REST apps, MVC, and microservices—exciting stuff ahead!

---
