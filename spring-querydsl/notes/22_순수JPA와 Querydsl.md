# 순수 JPA와 Querydsl

그동안 Test코드에서 작성하던 
코드를 Repository에 작성해보도록하겠습니다.

- SpringQuerydslApplication.java
```java
@SpringBootApplication
public class SpringQuerydslApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringQuerydslApplication.class, args);
    }

    @Bean
    JPAQueryFactory queryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

}
```
간단하게 `JPAQueryFactory`를 Bean으로 등록해줍니다.

그리고 `repository` 패키지를 하나 만들고 아래에
클래스를 만들어줍니다.
- MemberJpaRepository.java
```java
@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByUsername(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findAll_Querydsl() {
        return queryFactory.selectFrom(member)
                .fetch();
    }

    public List<Member> findByUsername_Querydsl(String username) {
        return queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }
}
```

`EntityManager`를 이용한 것과 `Querydsl`을 이용한 같은 기능을 하는 
코드를 작성했습니다.

해당 기능들에 대한 테스트 코드를 작성합니다.   
> MAC 기준 `cmd+shift+t`를 누르면 해당 클래스에 대한 테스트클래스를 바로 만들 수 있습니다.

- MemberJpaRepositoryTest.java
```java
@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> result1 = memberJpaRepository.findAll();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberJpaRepository.findByUsername("member1");
        assertThat(result2).containsExactly(member);

    }

    @Test
    public void querydslTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> result1 = memberJpaRepository.findAll_Querydsl();
        assertThat(result1).containsExactly(member);

        List<Member> result2 = memberJpaRepository.findByUsername_Querydsl("member1");
        assertThat(result2).containsExactly(member);
    }
}
```

모든 테스트가 성공했다면 완료입니다.