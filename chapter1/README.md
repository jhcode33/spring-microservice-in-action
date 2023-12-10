## Spring Microservices in Action - Second Edition. Chapter 1

## Introduction

Spring Microservice In Action - Chapter1에서 사용한 코드입니다.
기본적인 Spring boot 응용 프로그램이 포함되어 있습니다.
> Code used on the 1st Chapter of the Spring Microservices in Action - Second Edition Manning publication book. This code contains a basic Spring Boot application. 

## Upgrade Stack
- Java 11 -> Java 17
- Spring boot 2.2.3.RELEASE -> Spring boot 3.1.5

## Initial Configuration

1.	Apache Maven (http://maven.apache.org) 
2.	Git Client (http://git-scm.com)

ps: 로컬에 Maven 설치를 원하지 않으시는 분은 maven wrapper을 사용하십시오.
> If you don't want to install Maven locally, use the Maven wrapper.

## How To Use

전체 프로젝트를 'git fork' 후 'git clone' 하였다고 가정하고 설명합니다.
(자세한 방법은 root README.md 파일을 참고하거나 [Github](https://github.com/jhcode33/spring-microservice-in-action)를 방문해주세요)
Chapter1을 빌드하고 실행하는 방법은 아래와 같습니다.

### Check Java Version
> ```bash
> $ java --version
> ```

### Spring boot Project Build & Run

1. Installed Maven in local : mvn
2. Don't installed Maven yet : ./mvnw

```bash
# Move Chapter1 Directory & Move Simple Application
$ cd chapter1
$ cd simple-application

# Install dependencies
$ mvn install

# Run the app
$ mvn spring-boot:run
or 
$ java -jar target/simple-application-0.0.1-SNAPSHOT.jar
```

## How To Test

테스트에서 Postman을 사용합니다. 
자신의 운영체제에 맞는 제품을 다운로드하여 테스트하십시오.
- [for download Postman](https://www.postman.com/downloads/)

### Test URL Endpoint

> GET
> - http://localhost:8080/hello/illary?lastName=huaylupo
> - Return: { "message" : "Hello illary huaylupo" }
> ![chapter1](https://github.com/jhcode33/spring-microservice-in-action/assets/125725072/87018ff4-fcc3-4fc6-bd1d-adbbc94824b1)

> POST
> - http://localhost:8080/hello
> - Body : JSON <br> 
>   { "firstName" : "illary", "lastName" : "huaylupo" }
> - Return : { "message" : "Hello illary huaylupo" }
> ![chapter1](https://github.com/jhcode33/spring-microservice-in-action/assets/125725072/fcc3ae86-cca3-4918-a56e-a1fee8d8f2d4)