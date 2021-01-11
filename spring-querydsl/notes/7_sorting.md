# 정렬

> 회원 정렬순서  
> - 회원 나이 내림차순
> - 회원 이름 오름차순
> - 단 2에서 회원이 름이 없으면 마지막에 출력

- TestCode
```java
@Test
public void sort() throws Exception {
    // given
    em.persist(new Member(null, 100));
    em.persist(new Member("member5", 100));
    em.persist(new Member("member6", 100));
    // when
    List<Member> results = queryFactory.selectFrom(member)
            .where(member.age.eq(100))
            .orderBy(member.age.desc(), member.username.asc().nullsLast())
            .fetch();

    // then
    Member member5 = results.get(0);
    Member member6 = results.get(1);
    Member memberNull = results.get(2);

    assertThat(member5.getUsername()).isEqualTo("member5");
    assertThat(member6.getUsername()).isEqualTo("member6");
    assertThat(memberNull.getUsername()).isNull();
}
```
1. 새로운 회원 추가
2. `age.desc()`, `username.asc()` 정렬 후 검색
3. 검증
> `.nullsLast()` : Null 은 마지막  
> `.nullsFirst()` : Null이 처음

```sql
select
    member0_.member_id as member_i1_1_,
    member0_.age as age2_1_,
    member0_.team_id as team_id4_1_,
    member0_.username as username3_1_ 
from
    member member0_ 
where
    member0_.age=? 
order by
    member0_.age desc,
    member0_.username asc nulls last
```