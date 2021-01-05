package io.alxndr.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @Embedded // 내장 타입
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = LAZY) // 나는 주인이 아니다. 읽기전용이다.
    private List<Order> orders = new ArrayList<>();
}
