# SubQuery

### 나이가 가장 많은 회원 조회

```java
@Test
public void subQuery() throws Exception {

    QMember subMember = new QMember("subMember");

    List<Member> result = queryFactory
            .selectFrom(member)
            .where(member.age.eq(
                    JPAExpressions
                        .select(subMember.age.max())
                        .from(subMember)
            )).fetch();

    assertThat(result)
            .extracting("age")
            .containsExactly(40);
}
```

```sql
select
    member0_.member_id as member_i1_1_,
    member0_.age as age2_1_,
    member0_.team_id as team_id4_1_,
    member0_.username as username3_1_ 
from
    member member0_ 
where
    member0_.age=(
        select
            max(member1_.age) 
        from
            member member1_
    )
```

### 나이가 평균 이상인 회원
```java
@Test
public void subQuery2() throws Exception {

    QMember subMember = new QMember("subMember");

    List<Member> result = queryFactory
            .selectFrom(member)
            .where(member.age.goe(
                    JPAExpressions
                        .select(subMember.age.avg())
                        .from(subMember)
            )).fetch();

    assertThat(result)
            .extracting("age")
            .containsExactly(30, 40);
}
```

```sql
select
    member0_.member_id as member_i1_1_,
    member0_.age as age2_1_,
    member0_.team_id as team_id4_1_,
    member0_.username as username3_1_ 
from
    member member0_ 
where
    member0_.age>=(
        select
            avg(cast(member1_.age as double)) 
        from
            member member1_
    )
```

## Subquery In

```java
@Test
public void subQueryIN() throws Exception {

    QMember subMember = new QMember("subMember");

    List<Member> result = queryFactory
            .selectFrom(member)
            .where(member.age.in(
                    JPAExpressions
                            .select(subMember.age)
                            .from(subMember)
                    .where(subMember.age.gt(10))
            )).fetch();

    assertThat(result)
            .extracting("age")
            .containsExactly(20, 30, 40);
}
```

```sql
select
    member0_.member_id as member_i1_1_,
    member0_.age as age2_1_,
    member0_.team_id as team_id4_1_,
    member0_.username as username3_1_ 
from
    member member0_ 
where
    member0_.age in (
        select
            member1_.age 
        from
            member member1_ 
        where
            member1_.age>?
    )
```

### select 
```java
@Test
public void selectSubQuery() throws Exception {

    QMember subMember = new QMember("subMember");

    List<Tuple> result = queryFactory
            .select(member.username,
                    JPAExpressions.select(subMember.age.avg()).from(subMember))
            .from(member)
            .fetch();

    for (Tuple tuple : result) {
        System.out.println("tuple = " + tuple);
    }
    /*
    tuple = [member1, 25.0]
    tuple = [member2, 25.0]
    tuple = [member3, 25.0]
    tuple = [member4, 25.0]
    */
}
```

```sql
select
    member0_.username as col_0_0_,
    (select
        avg(cast(member1_.age as double)) 
    from
        member member1_) as col_1_0_ 
from
    member member0_
```


#### from 절의 서브쿼리 한계
JPA JPQL서브쿼리 한계점으로 from 절의 서브쿼리(인라이뷰)는 지원하지 않는다. 당연히 Querydsl도 지원하지 않는다.
하이버네이트 구현체를 사용하면 select절의 서브쿼리는 지원한다.
Querydsl도 하이버네이트 구현체를 사용하면 select절의 서브쿼리를 지원한다.

#### from 절의 서브쿼리 해결방안
1. 서브쿼리를 Join으로 변경한다. (가능한 상황)
2. 애플리케이션에서 쿼리를 2번 분리해서 실행한다.
3. NativeSQL을 사용한다.

> From절의 서브쿼리를 해결하려고 해야한다.  
> DB는 데이터를 퍼올리는 용도로만 사용하는게 낫다.  
> 애플리케이션에서 2번 3번 쿼리를 날리는것도 괜찮다.

SQL AntiPattern 