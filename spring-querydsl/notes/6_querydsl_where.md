# 검색 조건
`02ec24e`  

### 새로운 테스트 추가
```java
@Test
public void search() throws Exception {
    // given
    Member findMember = queryFactory.selectFrom(member)
            .where(member.username.eq("member1")
                    .and(member.age.eq(10)))
            .fetchOne();
    // when
    // then
    assertThat(findMember.getUsername()).isEqualTo("member1");
}
```
> where(안에서 .and() 대신 ,(comma)로 넣으면 같은 기능을 한다.)   
`.where(member.username.eq("member1"),member.age.eq(10))`


### 다양한 검색 조건
``` java
member.username.eq("member1") // username = 'member1'   
member.username.ne("member1") //username != 'member1'  
member.username.eq("member1").not() // username != 'member1'
member.username.isNotNull() //이름이 is not null   
member.age.in(10, 20) // age in (10,20)  
member.age.notIn(10, 20) // age not in (10, 20)   
member.age.between(10,30) //between 10, 30
member.age.goe(30) // age >= 30 
member.age.gt(30) // age > 30   
member.age.loe(30) // age <= 30 
member.age.lt(30) // age < 30
member.username.like("member%") //like 검색 
member.username.contains("member") // like ‘%member%’ 검색 
member.username.startsWith("member") //like ‘member%’ 검색
```
---
# 결과 조회

- 단순 리스트 조회
```java
List<Member> fetch = queryFactory
        .selectFrom(member)
        .fetch();

```
- 1개 조회
   - 결과가 2개이상이면 `NonUniqueResultException`
```java
Member member1 = queryFactory
        .selectFrom(QMember.member)
        .fetchOne();
```
- Limit 1
```java
Member member2 = queryFactory
        .selectFrom(QMember.member)
        .fetchFirst();
```

- Paging
```java
QueryResults<Member> results = queryFactory
        .selectFrom(member)
        .fetchResults();
long total = results.getTotal();
List<Member> content = results.getResults();
```
`results.getTotal()` : 리스트 카운트  
```sql
select
count(member0_.member_id) as col_0_0_ 
from
member member0_
```
`results.getResults()` : content 데이터
```sql
select
member0_.member_id as member_i1_1_,
member0_.age as age2_1_,
member0_.team_id as team_id4_1_,
member0_.username as username3_1_ 
from
member member0_
```
> 불필요한 카윤트쿼리를 성능을 위해서 따로 만들어서 날리는 경우도 있음

