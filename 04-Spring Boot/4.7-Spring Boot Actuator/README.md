# 4.7 - Spring Boot Actuator

## Introduction

Welcome to **4.7 - Spring Boot Actuator**

If you’re new to coding, this is your guide to keeping tabs on your Spring Boot app! Based on the "Spring Boot Actuator" lecture from the Udemy course "Mastering Java + Spring Boot: REST APIs and Microservices," we’ll dive into **Actuator**—a free tool that lets you monitor and manage your app with special web endpoints. Imagine it as your app’s personal doctor—checking its pulse, stats, and more! We’ll add it to your `restapp` from [4.4-Rest Controller Application](#4.4---rest-controller-application), explore its features, secure it, and tweak it with custom settings. Let’s jump in and make your app talk! 🚀

---

## Table of Contents

1. [What Is Spring Boot Actuator?](#1-what-is-spring-boot-actuator)
   - [1.1 Definition and Purpose](#11-definition-and-purpose)
   - [1.2 Why It’s Awesome](#12-why-its-awesome)
   - [1.3 Key Terms for Beginners](#13-key-terms-for-beginners)
2. [Learning Roadmap](#2-learning-roadmap)
   - [2.1 Why Use Actuator?](#21-why-use-actuator)
   - [2.2 Setting Up Actuator](#22-setting-up-actuator)
   - [2.3 Exploring Actuator Endpoints](#23-exploring-actuator-endpoints)
   - [2.4 Securing Actuator](#24-securing-actuator)
   - [2.5 Customizing with Application Properties](#25-customizing-with-application-properties)
3. [Practical Demonstration](#3-practical-demonstration)
   - [3.1 Adding Actuator to Restapp](#31-adding-actuator-to-restapp)
   - [3.2 Testing the Health Endpoint](#32-testing-the-health-endpoint)
   - [3.3 Exposing More Endpoints (Info, Mappings, Beans)](#33-exposing-more-endpoints-info-mappings-beans)
   - [3.4 Securing with Spring Security](#34-securing-with-spring-security)
   - [3.5 Custom Application Properties](#35-custom-application-properties)
4. [Practical Application](#4-practical-application)
   - [4.1 Best Practices for Actuator](#41-best-practices-for-actuator)
   - [4.2 Common Mistakes to Avoid](#42-common-mistakes-to-avoid)
   - [4.3 Hands-On Exercises](#43-hands-on-exercises)
5. [Wrapping Up](#5-wrapping-up)
   - [5.1 Resources for Further Learning](#51-resources-for-further-learning)
   - [5.2 Summary of Key Takeaways](#52-summary-of-key-takeaways)
   - [5.3 What’s Next in the Course](#53-whats-next-in-the-course)

---

## 1. What Is Spring Boot Actuator?

### 1.1 Definition and Purpose

Let’s keep it simple: Actuator is your app’s check-up tool!

- **Definition**: A Spring Boot feature that adds special web endpoints (URLs) to monitor and manage your app.
- **Purpose**: Answers questions like:
  - Is my app running? (Health)
  - What’s inside it? (Metrics like beans, mappings)
  - How’s it doing? (Info and status)

#### Real-World Analogy

Think of Actuator as a fitness tracker for your app—shows if it’s active, how it’s built, and what it’s up to!

### 1.2 Why It’s Awesome

- **Free Endpoints**: Add one dependency, get tons of info—no coding needed!
- **DevOps Power**: Monitor like a pro (health, metrics)—out of the box.
- **Flexible**: Secure it, tweak it, customize it—your rules.

#### Example Benefit

Hit `localhost:8080/actuator/health`—see `{"status":"UP"}`—your app’s alive, instantly!

### 1.3 Key Terms for Beginners

Your newbie glossary—made easy:

| Term         | Meaning                            | Example                        |
| ------------ | ---------------------------------- | ------------------------------ |
| **Actuator** | Tool for app monitoring/management | `spring-boot-starter-actuator` |
| **Endpoint** | A URL showing app details          | `/actuator/health`             |
| **Health**   | Is the app running?                | `"status": "UP"`               |
| **Beans**    | Hidden helpers in your app         | List at `/actuator/beans`      |
| **Mappings** | Your app’s routes                  | `/hi`, `/bye` at `/mappings`   |
| **DevOps**   | Managing apps like pros do         | Health checks                  |

---

## 2. Learning Roadmap

Your step-by-step guide to Actuator mastery!

### 2.1 Why Use Actuator?

- **What You’ll Learn**: How it solves monitoring problems.
- **Goal**: Know why it’s a game-changer.

### 2.2 Setting Up Actuator

- **What You’ll Do**: Add the Actuator starter.
- **Goal**: Get endpoints running fast.

### 2.3 Exploring Actuator Endpoints

- **What You’ll Do**: Check `/health`, `/info`, `/mappings`, `/beans`.
- **Goal**: See inside your app.

### 2.4 Securing Actuator

- **What You’ll Do**: Add security to lock it down.
- **Goal**: Keep your app’s info private.

### 2.5 Customizing with Application Properties

- **What You’ll Do**: Tweak settings in `application.properties`.
- **Goal**: Make Actuator your own.

---

## 3. Practical Demonstration

Let’s bring Actuator to your `restapp` from [4.4-Rest Controller Application](#4.4---rest-controller-application)!

### 3.1 Adding Actuator to Restapp

- **Pre-Check**: Open `restapp` in Eclipse (from [4.4-Rest Controller Application](#4.4---rest-controller-application)), with `starter-web` and `devtools` from [4.6-Spring Boot Starters and Spring Dev Tools](#4.6---spring-boot-starters-and-spring-dev-tools).
- **Steps**:
  1. **Edit POM**: Open `pom.xml`, inside `<dependencies>`, add:
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-actuator</artifactId>
     </dependency>
     ```
  2. **Update Maven**: Right-click `restapp` > `Maven > Update Project` > "OK"—wait for downloads.
  3. **Run It**: Right-click `RestappApplication.java` > `Run As > Spring Boot App`.
  4. **Check Console**: See "Tomcat started on port(s): 8080" and "LiveReload server"—it’s alive!

> [!NOTE]
> One line in `pom.xml` = instant monitoring—Actuator’s magic!

### 3.2 Testing the Health Endpoint

- **Pre-Check**: Ensure `application.properties` is empty or unchanged.
- **Steps**:

  1. **Visit Health**: Open `localhost:8080/actuator/health` in a browser.
  2. **See Result**: Get `{"status":"UP"}`—your app’s running fine!
  3. **Use Postman**:
     - Open Postman (from [4.4-Rest Controller Application](#4.4---rest-controller-application)), set URL to `localhost:8080/actuator/health`, method "GET," click "Send."
     - See `{"status":"UP"}`—works everywhere!

- **What’s Happening**:
  - `/health` is free by default—no setup needed.

> [!TIP] >`/health` is your app’s pulse—check it in browsers or Postman!

### 3.3 Exposing More Endpoints (Info, Mappings, Beans)

- **Steps**:

  1. **Edit Properties**: Open `src/main/resources/application.properties`, add:
     ```
     management.endpoints.web.exposure.include=*
     management.info.env.enabled=true
     info.app.name=RestApp
     info.app.description=A crazy fun app
     info.app.version=1.0.0
     ```
  2. **Save & Reload**: Save (`Ctrl+S`)—DevTools restarts (from [4.6-Spring Boot Starters and Spring Dev Tools](#4.6---spring-boot-starters-and-spring-dev-tools)).
  3. **Test Info**: Visit `localhost:8080/actuator/info`—see:
     ```json
     {
       "app": {
         "name": "RestApp",
         "description": "A crazy fun app",
         "version": "1.0.0"
       }
     }
     ```
  4. **Check Mappings**: Visit `localhost:8080/actuator/mappings`—see `/hi`, `/bye`, `/hello`, plus Actuator routes.
  5. **List Beans**: Visit `localhost:8080/actuator/beans`—huge list of Spring’s background helpers!

- **What’s Happening**:
  - `*` exposes all 10+ endpoints (e.g., `/auditEvents`, `/caches`); `info` settings add your app’s story.

> [!NOTE] >`*` is your key—unlocks every Actuator secret!

### 3.4 Securing with Spring Security

- **Steps**:

  1. **Add Security**: In `pom.xml`, inside `<dependencies>`, add:
     ```xml
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-security</artifactId>
     </dependency>
     ```
  2. **Update Maven**: Right-click `restapp` > `Maven > Update Project` > "OK."
  3. **Run Again**: Stop (red square in Console), rerun `RestappApplication.java`.
  4. **See Password**: Console shows "Using generated security password: [random-id]" (e.g., `abcd-1234`).
  5. **Test Login**: Visit `localhost:8080/actuator/health`—login pops up:
     - Username: `user`
     - Password: Copy from Console.
     - Sign in—see `{"status":"UP"}`.
  6. **Custom Login**: In `application.properties`, add:
     ```
     spring.security.user.name=scott
     spring.security.user.password=tiger
     ```
     - Stop, rerun, use `scott`/`tiger` at `localhost:8080/actuator/health`.
  7. **Postman Test**: In Postman, set URL to `localhost:8080/actuator/info`, go to "Authorization" tab, pick "Basic Auth," enter `scott`/`tiger`, click "Send"—see info JSON.
  8. **Exclude Option**: In `application.properties`, try:
     ```
     management.endpoints.web.exposure.exclude=beans
     ```
     - Save, test `/actuator/beans`—it’s blocked!

- **What’s Happening**:
  - `starter-security` adds a login wall; custom creds override defaults; `exclude` hides endpoints.

> [!TIP]
> Security is Spring magic—locks your app with one line!

### 3.5 Custom Application Properties

- **Steps**:

  1. **Explore Options**: In `application.properties`, you can add:
     ```
     server.port=9090                # Change Tomcat port
     server.servlet.session.timeout=30m  # Session timeout (30 minutes)
     spring.datasource.url=jdbc:mysql://localhost:3306/mydb  # Database URL
     spring.datasource.username=dbuser  # DB username
     spring.datasource.password=dbpass  # DB password
     my.custom.property=HelloWorld    # Your own value
     ```
  2. **Use in Code**: In `MyController.java`, add:

     ```java
     @Value("${my.custom.property}")
     private String customProp;

     @GetMapping("/custom")
     public String getCustom() {
         return customProp;
     }
     ```

  3. **Test It**: Save, visit `localhost:8080/custom` (or `9090` if port changed)—see "HelloWorld."

- **What’s Happening**:
  - Spring Boot has 1000+ properties (core, web, security, data, etc.)—tweak anything!

> [!NOTE] >`application.properties` is your control panel—customize away!

---

## 4. Practical Application

Let’s lock in Actuator with tips and practice!

### 4.1 Best Practices for Actuator

- **Start Simple**: Use `/health` first—add more endpoints later.
- **Secure It**: Always add `starter-security` in real apps—protect your data.
- **Add Info**: Set `info.app.*`—makes your app professional.
- **Monitor Often**: Check `/health` in Postman—stay in control.

### 4.2 Common Mistakes to Avoid

- **Missing Actuator**: No `starter-actuator`? No endpoints—check `pom.xml`!
- **Unsecured Endpoints**: Skipped security? `/beans` is public—add it!
- **Property Typos**: `spring.secure.user.name` vs. `spring.security.user.name`? Fix it!
- **Cookie Confusion**: Login skips? Use a private browser—clear cookies.

### 4.3 Hands-On Exercises

Try these to own Actuator:

1. **Health Check**:
   - Test `localhost:8080/actuator/health` in Postman—save the JSON.
2. **Custom Info**:
   - Set `info.app.name=[Your Name]`, save, check `/actuator/info`.
3. **Count Routes**:
   - Visit `/actuator/mappings`, count your app’s routes (e.g., `/hi`).
4. **Secure Login**:
   - Set `spring.security.user.name=me` and `password=secret`, test `/actuator/health` login.
5. **Exclude & Test**:
   - Add `management.endpoints.web.exposure.exclude=beans,mappings`, save, try both—blocked?

> [!TIP]
> These tasks make Actuator your tool—small wins, big skills!

---

## 5. Wrapping Up

### 5.1 Resources for Further Learning

Level up with these:

- **Spring Docs**: [spring.io/guides/gs/actuator-service](https://spring.io/guides/gs/actuator-service/) - Actuator basics.
- **Endpoint List**: [docs.spring.io/spring-boot/docs/current/reference/html/actuator.html](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html) - All 10+ endpoints.
- **Security Basics**: [spring.io/guides/topicals/spring-security-architecture](https://spring.io/guides/topicals/spring-security-architecture) - Deep dive.
- **Postman**: [learning.postman.com](https://learning.postman.com/) - Master testing.
- **Udemy Course**: [Course Link](#) - Full lecture (placeholder).

### 5.2 Summary of Key Takeaways

- **Actuator**: Adds endpoints (e.g., `/health`, `/info`) to monitor/manage your app.
- **Setup**: One dependency (`starter-actuator`)—free goodies!
- **Endpoints**: `/health` (default), `/info`, `/mappings`, `/beans`—peek inside.
- **Security**: `starter-security` locks it; custom `scott/tiger` works.
- **Properties**: 1000+ options in `application.properties`—tweak ports, DBs, more.

> [!TIP]
> You’ve got Actuator down—your app’s talking to you now!

### 5.3 What’s Next in the Course

The transcript teases what’s coming:

- **Next Videos**: Build REST APIs with:
  - Security (more on `starter-security`).
  - Database connections (e.g., `spring.datasource.*`).
  - Mapping POJOs to JSON (entities to output).
- **Later**: MVC and Microservices—big stuff ahead!

Assumed next: **05-RESTful Services(REST API)**—stay tuned!

---
