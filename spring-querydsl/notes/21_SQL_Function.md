# SQL Function

`f99b23f`

### SQL Function사용하여 회원이름을 M으로 바꾸는 방법
```java
@Test
public void sqlFunction() {
    List<String> usernames = queryFactory
            .select(Expressions.stringTemplate("function('replace', {0}, {1}, {2})", member.username, "member", "M"))
            .from(member)
            .fetch();
    for (String usearname : usernames) {
        System.out.println("usearname = " + usearname);
    }
}
```

```sql
select
    replace(member0_.username,
    ?,
    ?) as col_0_0_ 
from
    member member0_
```

### Lower 사용
```java
@Test
public void sqlFunction2() {
    List<String> result = queryFactory
            .select(member.username)
            .from(member)
            .where(member.username.eq(
                    Expressions.stringTemplate("function('lower', {0})", member.username)
            )).fetch();

    for (String username : result) {
        System.out.println("username = " + username);
    }
}
```
```sql
select
    member0_.username as col_0_0_ 
from
    member member0_ 
where
    member0_.username=lower(member0_.username)
```
> 이렇게 ANSI표준으로 등록된 Function은 아래와 같이 간단하게 사용가능하다.

```java
@Test
public void sqlFunction2() {
    List<String> result = queryFactory
            .select(member.username)
            .from(member)
            .where(member.username.eq(member.username.lower())).fetch();

    for (String username : result) {
        System.out.println("username = " + username);
    }
}
```

> 간단한 Function은 뒤에 .을 찍고 한번 찾아보자.