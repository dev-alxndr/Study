# 페이징

> ### 페이징을 할 땐 정렬을 해줘야한다.

```java
@Test
public void paging1() throws Exception {
    List<Member> result = queryFactory
            .selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1)
            .limit(2)
            .fetch();

    assertThat(result.size()).isEqualTo(2);
}
```
- `offset` 으로 시작 지정 (0이 시작)
- `limit` 갯수 지정
- 실제 쿼리
```sql
select
    member0_.member_id as member_i1_1_,
    member0_.age as age2_1_,
    member0_.team_id as team_id4_1_,
    member0_.username as username3_1_ 
from
    member member0_ 
order by
    member0_.username desc limit ? offset ?
```

- QueryResult
Paging 관련 정보를 같이 받을 수 있음.
```java
@Test
public void paging2() throws Exception {
    QueryResults<Member> result = queryFactory.selectFrom(member)
            .orderBy(member.username.desc())
            .offset(1)
            .limit(2)
            .fetchResults();

    assertThat(result.getTotal()).isEqualTo(4);
    assertThat(result.getLimit()).isEqualTo(2);
    assertThat(result.getOffset()).isEqualTo(1);
    assertThat(result.getResults().size()).isEqualTo(2);
}
```
> `.fetchResults()` > `QueryResults` 객체로 반환
> `queryResults.getTotal()` : 전체 데이터 수
> `queryResults.getLimit()` : 제한 갯수
> `queryResults.getOffset()` : 시작 Row
> `queryResults.getResults()` : Content