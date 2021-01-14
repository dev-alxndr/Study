# Querydsl 페이징

Querydsl Paging

- MemberRepositoryCustom.java
```java
Page<MemberTeamDto> searchWithPageSimple(MemberSearchCondition condition, Pageable pageable);

Page<MemberTeamDto> searchWithPageComplex(MemberSearchCondition condition, Pageable pageable);
```
인터페이스를 추가해준다.

- MemberRepositoryImple.java
```java
@Override
public Page<MemberTeamDto> searchWithPageSimple(MemberSearchCondition condition, Pageable pageable) {
    QueryResults<MemberTeamDto> result = queryFactory
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
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

    List<MemberTeamDto> content = result.getResults();
    long total = result.getTotal();

    return new PageImpl<>(content, pageable, total);
}
```
`QueryResult<>` : content, totalCount   
`getResults()` : 실제 데이터   
`getTotal()` : select 쿼리 그대로 count로 바꿔서 리턴

> 불필요한 Count쿼리를 최적화하고 싶다면 따로 분리해야됨.


```java
@Override
public Page<MemberTeamDto> searchWithPageComplex(MemberSearchCondition condition, Pageable pageable) {
    List<MemberTeamDto> result = queryFactory
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
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    List<MemberTeamDto> content = result;
    JPAQuery<MemberTeamDto> countQuery = queryFactory
            .select(new QMemberTeamDto(
                    member.id.as("memberId"),
                    member.username,
                    member.age,
                    team.id.as("teamId"),
                    team.name.as("teamName")))
            .from(member)
            .where(
                    usernameEq(condition.getUsername()),
                    teamNameEq(condition.getTeamName()),
                    ageGoe(condition.getAgeGoe()),
                    ageLoe(condition.getAgeLoe()))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize());


    return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
}
```
>## PageableExecutionUtils.getPage() 사용하면 카운트 쿼리를 최적화 할 수 있다.
### Count 쿼리가 생략가능한 경우 생략해서 처리
- 페이지 시작이면서 컨텐츠 사이즈가 페이지 사이즈보다 작을 때
- 마지막 페이지 일 때 (offset + 컨텐츠 사이즈를 더해서 전체 사이즈 구함)

