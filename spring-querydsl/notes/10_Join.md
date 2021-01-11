# Join

> Team A에 속한 회원을 검색한다.
```java
@Test
public void join() throws Exception {
    List<Member> result = queryFactory
            .selectFrom(member)
            .join(member.team, team)
            // leftJoin(), rightJoin()
            .where(team.name.eq("TEAM A"))
            .fetch();

    assertThat(result)
            .extracting("username")
            .containsExactly("member1", "member2");
}
```
- native query
```sql
select
    member0_.member_id as member_i1_1_,
    member0_.age as age2_1_,
    member0_.team_id as team_id4_1_,
    member0_.username as username3_1_ 
from
    member member0_ 
inner join
    team team1_ 
        on member0_.team_id=team1_.team_id 
where
    team1_.name=?
```