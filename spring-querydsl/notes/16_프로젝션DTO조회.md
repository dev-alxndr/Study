# 프로젝션DTO조회

Querydsl에서 프로젝션으로 DTO로 바로 조회하는 방법을 알아보고, JPQL과의 차이점을 확인해보겠습니다.

우선 MemberDto를 클래스를 만든다.
- MemberDto.java
```java
@Data
public class MemberDto {


    private String username;
    private int age;

    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
```

## 1. JPQL DTO반환
- QuerydslBasicTest.java
```java
@Test
    public void findDtoByJPQL() {
        List<MemberDto> result = em.createQuery("select new io.alxndr.springquerydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }
```
- native sql
```sql
select
    member0_.username as col_0_0_,
    member0_.age as col_1_0_ 
from
    member member0_
```
- 순수 JPA에서 DTO를 바로 조회할 때는 `new` 명령어를 사용
- DTO의 Package이름을 다 적어줘야해서 불편함
- 생성자 주입 방식만 지원


## 2. Querydsl DTO반환

3가지 방법
### 1. 프로퍼티 접근(기본생성자필요)   
`Projections.bean()`
```java
@Test
public void findDtoByQuerydslSetter() {
    List<MemberDto> result = queryFactory.select(
            Projections.bean(MemberDto.class,
                    member.username,
                    member.age))
            .from(member)
            .fetch();

    for (MemberDto memberDto : result) {
        System.out.println("memberDto = " + memberDto);
    }
}
```
### 2. 필드 직접 접근   
`Projections.fields()`
```java
@Test
public void findDtoByQuerydslFields() {
    List<MemberDto> result = queryFactory.select(
            Projections.fields(MemberDto.class,
                    member.username,
                    member.age))
            .from(member)
            .fetch();

    for (MemberDto memberDto : result) {
        System.out.println("memberDto = " + memberDto);
    }
}
```

### 3. 생성자 사용   
`Projections.constructor()`
```java
@Test
public void findDtoByQuerydslConstructor() {
    List<MemberDto> result = queryFactory.select(
            Projections.constructor(MemberDto.class,
                    member.username,
                    member.age))
            .from(member)
            .fetch();

    for (MemberDto memberDto : result) {
        System.out.println("memberDto = " + memberDto);
    }
}
```

### 4. 프로퍼티명이 다른경우
DB컬럼명과 DTO 변수명이 다른 경우
```java
@Test
public void findUserDto() {
    List<UserDto> result = queryFactory.select(
            Projections.fields(UserDto.class,
                    member.username.as("name"),
                    member.age))
            .from(member)
            .fetch();

    for (UserDto userDto : result) {
        System.out.println("userDto = " + userDto);
    }
}
```
`.as("프로퍼티명")`

### 5. SubQuery alias
```java
@Test
public void findUserDtoSubQuery() {
    QMember memberSub = new QMember("memberSub");

    List<UserDto> result = queryFactory.select(
            Projections.fields(UserDto.class,
                    member.username.as("name"),
                    Expressions.as(JPAExpressions
                        .select(memberSub.age.max())
                            .from(memberSub), "age")
                    ))
            .from(member)
            .fetch();

    for (UserDto userDto : result) {
        System.out.println("userDto = " + userDto);
    }
}
```
`Expressions.as()` 로 감싸야함