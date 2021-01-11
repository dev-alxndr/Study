# Fetch Join

- WithOut FetchJoin
```java
@PersistenceUnit
EntityManagerFactory emf;

@Test
public void fetchJoinNo() throws Exception {
    em.flush(); // fetch test시 해주는게 좋음
    em.clear();

    Member member1 = queryFactory
            .selectFrom(member)
            .where(member.username.eq("member1"))
            .fetchOne();

    // 1. 회원만 조회 (팀X)
    boolean loaded = emf.getPersistenceUnitUtil().isLoaded(member1.getTeam());
    assertThat(loaded).as("패치 조인 미적용").isFalse();
}
```

- With FetchJoin
```java
@Test
public void fetchJoinUse() throws Exception {
    em.flush();
    em.clear();

    Member member1 = queryFactory
            .selectFrom(member)
            .join(member.team, team).fetchJoin()
            .where(member.username.eq("member1"))
            .fetchOne();

    // 1. 회원 + 팀 fetch join
    boolean loaded = emf.getPersistenceUnitUtil().isLoaded(member1.getTeam());
    assertThat(loaded).as("패치 조인 적용").isTrue();
}
```

- native query
```sql
select
    member0_.member_id as member_i1_1_0_,
    team1_.team_id as team_id1_2_1_,
    member0_.age as age2_1_0_,
    member0_.team_id as team_id4_1_0_,
    member0_.username as username3_1_0_,
    team1_.name as name2_2_1_ 
from
    member member0_ 
inner join
    team team1_ 
        on member0_.team_id=team1_.team_id 
where
    member0_.username=?
```