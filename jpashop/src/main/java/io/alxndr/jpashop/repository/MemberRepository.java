package io.alxndr.jpashop.repository;

import io.alxndr.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/*
@Repository -> @Component
component scan을 통해 Bean으로 등록*
 */
@Repository
public class MemberRepository {

    @PersistenceContext // 해당 어노테이션은 Spring을 주입받을 수 있음
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        // JPQL entity를 대상으로 조회. sql로 번역되어 실행됨.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
