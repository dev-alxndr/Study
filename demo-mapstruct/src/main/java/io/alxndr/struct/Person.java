package io.alxndr.struct;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author : Alexander Choi
 * @date : 2021/03/10
 */
//@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private Long id;

    private String name;

    private String email;

    private String birth;

    private String group;

    private LocalDateTime creationDate;
}
