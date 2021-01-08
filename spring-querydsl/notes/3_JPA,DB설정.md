# JPA, DB 설정

```yml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/querydsl
    username: sa
    password:
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create
      properties:
        hibernate:
  #        show_sql: true   # System out
          format_sql: true
logging:
  level:
    org.hibernate.SQL: debug    # Logger
    org.hibernate.type: trace   # Show Query Parameter
```

- 좀 더 편하게 Query Parameter 확인하기

```gradle
implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.8'
```

