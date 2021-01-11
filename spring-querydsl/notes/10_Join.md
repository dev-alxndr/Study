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

### 연관관계없이 Join하기
- 회원의 이름이 팀 이름과 같은 회원 조회
```java
@Test
public void thetaJoin() throws Exception {
    em.persist(new Member("TEAM A"));
    em.persist(new Member("TEAM B"));
    em.persist(new Member("TEAM C"));

    List<Member> result = queryFactory
            .select(member)
            .from(member, team)
            .where(member.username.eq(team.name))
            .fetch();

    assertThat(result)
            .extracting("username")
            .containsExactly("TEAM A", "TEAM B");
}
```
> from 절에 여러 엔티티를 선택해서 세타조인   
> 외부 조인 불가능

# Join On절
- On절을 활용한 조인(JPA 2.1 부터 지원)
    - 조인 대상 필터링
    - 연관관계 없는 엔티티 외부조인

- 회원과 팀을 조인하면서, 팀 이름이 TEAM A인 팀만 조회, 회원은 모두 조회
```java
@Test
public void joinOnFiltering() throws Exception {
    List<Tuple> result = queryFactory.select(member, team)
            .from(member)
            .leftJoin(member.team, team)
            .on(team.name.eq("TEAM A"))
            .fetch();

    for (Tuple tuple : result) {
        System.out.println("tuple = " + tuple);
        /*
        tuple = [Member(id=3, username=member1, age=10), Team(id=1, name=TEAM A)]   
        tuple = [Member(id=4, username=member2, age=20), Team(id=1, name=TEAM A)]   
        tuple = [Member(id=5, username=member3, age=30), null]   
        tuple = [Member(id=6, username=member4, age=40), null]
        */
    }
}
```
> left join이므로 회원은 모두 나오지만, 팀은 `TEAM A`인 팀만 join되어 나오게 된다.

> 

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
left outer join
    team team1_ 
    on member0_.team_id=team1_.team_id 
    and (
        team1_.name=?
    )
```

> on 절을 활용해 조인 대상을 필터링 할 때, 외부조인이 아니라 내부조인(inner join)을 사용하면, `where` 절에서 필터링 하는 것과 동일하다.
내부조인이면 익순한 `where` 사용하고 외부 조인이면 on 절을 사용하자.

# 연관관계 없는 엔티티 외부 조인

- 하이버네이트 5.1부터 `on`을 사용해서 서로 관계가 없는 필드로 외부 조인하는 기능이 추가되었다. 내부조인도 가능
- __주의!__
    - 일반조인: `leftJoin(member.team, team)`
    - on 조인: `from(member).leftJoin(team).on(~~)`
```java
/*
연관관계가 없는 엔티티 외부 조인
회원의 이름이 팀 이름과 같은 회원 조회
* */
@Test
public void joinOnNoRelation() throws Exception {
    em.persist(new Member("TEAM A"));
    em.persist(new Member("TEAM B"));
    em.persist(new Member("TEAM C"));

    List<Tuple> result = queryFactory
            .select(member, team)
            .from(member)
            .leftJoin(team)
            .on(member.username.eq(team.name))
            .where(member.username.eq(team.name))
            .fetch();

    for (Tuple tuple : result) {
        System.out.println("tuple = " + tuple);
    }
}
```

`leftJoin()` 기본적으로 `member.team` 을 적어서 `member.teamId = team.id` 이렇게 하겠지만, 막 조인..을 하기위해선 그냥 테이블을 작성후 ON절에서 걸러서 join을 해준다.
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
left outer join
    team team1_ 
        on (
            member0_.username=team1_.name
        ) 
where
    member0_.username=team1_.name
```