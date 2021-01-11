# Case

### 간단한 Case
```java
@Test
public void basicCase()  throws Exception {
    List<String> result = queryFactory
            .select(member.age.when(10).then("열살")
                    .when(20).then("스무살")
                    .otherwise("기타"))
            .from(member)
            .fetch();

    for (String s : result) {
        System.out.println("s = " + s);
    }
    /*
    s = 열살
    s = 스무살
    s = 기타
    s = 기타
    */
}
```

```sql
select
    case 
        when member0_.age=? then ? 
        when member0_.age=? then ? 
        else '기타' 
    end as col_0_0_ 
from
    member member0_
```

### 살짝 복잡한 Case

```java
@Test
public void complexCase() throws Exception {
    List<String> result = queryFactory
            .select(new CaseBuilder()
                    .when(member.age.between(0, 20)).then("0~20")
                    .when(member.age.between(21, 30)).then("21~30")
                    .otherwise("ETC"))
            .from(member)
            .fetch();

    for (String s : result) {
        System.out.println("s = " + s);
    }
}
```

```sql
select
    case 
        when member0_.age between ? and ? then ? 
        when member0_.age between ? and ? then ? 
        else 'ETC' 
    end as col_0_0_ 
from
    member member0_
```

> ### 이런 로직은 DB보다 애플리케이션단에서 처리하는게 좋다.   
> 물론 필요하면 써야한다.