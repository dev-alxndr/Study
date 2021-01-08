package io.alxndr.springquerydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static io.alxndr.springquerydsl.QMember.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em); // 동시성 문제 해결됩니다.
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
    }


    @Test
    public void startJPQL() throws Exception {
        // given
        String query = "select m from Member m where m.username = :username";
        Member findMember = em.createQuery(query, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        // when

        // then
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }


    @Test
    public void startQuerydsl() throws Exception {
        // given

        QMember m = new QMember("m");

        // when
        Member findMember = queryFactory.selectFrom(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        // then
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() throws Exception {
        // given
        Member findMember = queryFactory.selectFrom(member)
                .where(member.username.eq("member1")
                        ,member.age.eq(10))
                .fetchOne();
        // when
        // then
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetchTest() throws Exception {
        // given

        // List
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();

        // First
        Member member1 = queryFactory
                .selectFrom(QMember.member)
                .fetchOne();

        // limit 1
        Member member2 = queryFactory
                .selectFrom(QMember.member)
                .fetchFirst();

        // Paging
        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();
        long total = results.getTotal();
        List<Member> content = results.getResults();


        // when

        // then
    }

}
