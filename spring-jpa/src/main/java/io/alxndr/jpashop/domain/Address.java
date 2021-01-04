package io.alxndr.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;
/*
* 값타입은 변경불가능하게 설계하는게 좋다.
* 변경할려면 새로운 객체를 만들어서 해줘야한다.
* */
@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;

    // WARNING
    // 임베디드 타입은 자바 기본생성자를 public or protected로 해야한다. protected 추천
    // jpa 구현 라이브러리가 객체를 생성할떄 리플렉션 같은 기술을 사용할 수 있도록 지원해야 하기 떄문이다.
    protected Address() {
    }

    // 생성자에서 값을 초기화해서 변경 불가능한 클래스로 만들자.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
