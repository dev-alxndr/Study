package io.alxndr.struct;

import lombok.*;

/**
 * @author : Alexander Choi
 * @date : 2021/03/10
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonDto {
    private Long id;

    private String name;

    private String email;

    private String birth;

    private String team;

}
