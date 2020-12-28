package io.alxndr.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class Address implements Serializable {

    private String city;

    private String street;

    private String zipcode;

}
