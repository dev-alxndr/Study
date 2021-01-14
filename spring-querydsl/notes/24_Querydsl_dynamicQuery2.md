# Querydsl Dynamic Query (Where)

이전에 본 `BooleanBuilder`와 다른 방식

- MemberJpaRepository.java
```java
public List<MemberTeamDto> searchByWhere(MemberSearchCondition condition) {
    return queryFactory
            .select(new QMemberTeamDto(
                    member.id.as("memberId"),
                    member.username,
                    member.age,
                    team.id.as("teamId"),
                    team.name.as("teamName")))
            .from(member)
            .leftJoin(member.team, team)
            .where(
                usernameEq(condition.getUsername()),
                teamNameEq(condition.getTeamName()),
                ageGoe(condition.getAgeGoe()),
                ageLoe(condition.getAgeLoe()))
            .fetch();
}

private BooleanExpression ageLoe(Integer ageLoe) {
    return ageLoe != null ? member.age.loe(ageLoe) : null;
}

private BooleanExpression ageGoe(Integer ageGoe) {
    return ageGoe != null ? member.age.goe(ageGoe) : null;
}

private BooleanExpression teamNameEq(String teamName) {
    return hasText(teamName) ? team.name.eq(teamName) : null;
}

private BooleanExpression usernameEq(String username) {
    return hasText(username) ? member.username.eq(username) : null;
}
```

#### 장점
- BooleanBuilder를 사용하는것보다 재사용성이 좋다.
- 각각의 조건을 조합할 수 있다.
```java
private BooleanExpression usernameAndTeamNameEq(String username, String teamName) {
    return usernameEq(username).and(teamNameEq(teamName));
}
```

### Test Code

- MemberJpaRepositoryTest.java
```java
@Test
public void searchWhereTest() throws Exception {
    // given
    Team teamA = new Team("TEAM A");
    Team teamB = new Team("TEAM B");
    em.persist(teamA);
    em.persist(teamB);

    Member member1 = new Member("member1", 10, teamA);
    Member member2 = new Member("member2", 20, teamA);

    Member member3 = new Member("member3", 30, teamB);
    Member member4 = new Member("member4", 40, teamB);

    em.persist(member1);
    em.persist(member2);
    em.persist(member3);
    em.persist(member4);
    // when
    MemberSearchCondition condition = new MemberSearchCondition();
    condition.setAgeGoe(35);
    condition.setAgeLoe(40);
    condition.setTeamName("TEAM B");

    List<MemberTeamDto> result = memberJpaRepository.searchByWhere(condition);
    // then
    assertThat(result).extracting("username").containsExactly("member4");
}
```

- native query
```sql
select
    member0_.member_id as col_0_0_,
    member0_.username as col_1_0_,
    member0_.age as col_2_0_,
    team1_.team_id as col_3_0_,
    team1_.name as col_4_0_ 
from
    member member0_ 
left outer join
    team team1_ 
        on member0_.team_id=team1_.team_id 
where
    team1_.name=? 
    and member0_.age>=? 
    and member0_.age<=?
```