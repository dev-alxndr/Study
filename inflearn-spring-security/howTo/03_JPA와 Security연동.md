# JPA와 Security 연동

### 이전의 문제점
1. 매번 유저를 추가하는 일이 생길 경우 코드를 수정해야 한다.
2. 수정, 삭제도 마찬가지로 코드를 수정해야 한다.

-> 위와 같은 문제를 DB를 연동하여 유저 정보를 관리할 수 있도록 수정해보겠습니다.
(JPA를 사용하겠습니다.)

## 개선

#### JPA 설정
##### dependency 추가 
- build.gradle
```graldle
dependencies {
...
    // JPA
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    // H2
    runtimeOnly 'com.h2database:h2'
...
}
```

##### class 생성

![](./images/Screen%20Shot%202021-03-23%20at%204.12.58%20PM.png)
