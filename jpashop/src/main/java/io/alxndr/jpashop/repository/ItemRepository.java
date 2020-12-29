package io.alxndr.jpashop.repository;

import io.alxndr.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /*저장*/
    public void save(Item item) {
        if (item.getId() == null) { // ID값이 없다는건 새로운 객체라는 뜻
            em.persist(item);   // 신규 등록
        } else {
            em.merge(item); // 이미 DB에 등록되어있으므로 update라고 이해한다.
        }
    }

    /*단건 조회*/
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /*전체 조회*/
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }


}
