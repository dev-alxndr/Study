# 검색 조건

새로운 테스트 추가
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