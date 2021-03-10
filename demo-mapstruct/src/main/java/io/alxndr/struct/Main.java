package io.alxndr.struct;

import io.alxndr.struct.mapper.PersonMapper;

/**
 * @author : Alexander Choi
 * @date : 2021/03/10
 */
public class Main {
    public static void main(String[] args) {
        // DTO -> Entity
        PersonDto personDto = PersonDto.builder()
                .id(1L)
                .name("Alexander Choi")
                .email("dev.alxndr@gmail.com")
                .birth("1995-10-14")
                .build();

        Person person = PersonMapper.INSTANCE.toEntity(personDto);
        System.out.println("car.toString() = " + person.toString());


        // Entity -> Dto
        PersonDto newPersonDto = PersonMapper.INSTANCE.toDto(person);
        System.out.println("newCarDto.toString() = " + newPersonDto.toString());

    }
}
