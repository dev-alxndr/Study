package me.alxndr.restapi.events;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author : Alexander Choi
 * @date : 2021/08/02
 */

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class EventDto {

    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location;
    private int basePrice;
    private int maxPrice;
    private int limitOfEnrollment;

}
