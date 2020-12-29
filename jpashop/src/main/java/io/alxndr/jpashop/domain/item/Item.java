package io.alxndr.jpashop.domain.item;

import io.alxndr.jpashop.domain.CategoryItem;
import io.alxndr.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // 하나의 테이블에 몰아서 넣는다.
@DiscriminatorColumn
public abstract class Item {    // 구현체 존재

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @OneToMany(mappedBy = "item", fetch = LAZY)
    private List<CategoryItem> categories = new ArrayList<>();

    /**
     * 재고 수량 증가
     * @param stockQuantity
     */
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    /**
     * 재고 수량 감소
     * @param stockQuantity
     */
    public void removeStock(int stockQuantity) {
        int result = this.stockQuantity - stockQuantity;
        if (result < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = result;
    }

}
