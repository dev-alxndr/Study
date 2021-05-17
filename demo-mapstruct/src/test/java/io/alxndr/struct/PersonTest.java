package io.alxndr.struct;

import io.alxndr.struct.mapper.PersonMapper;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author : Alexander Choi
 * @date : 2021/05/17
 */
public class PersonTest {

    @Test
    public void dtoToEntity() {
        PersonDto personDto = PersonDto.builder()
                .id(1L)
                .name("Alexander Choi")
                .email("dev.alxndr@gmail.com")
                .birth("1995-01-01")
                .build();

        Person person = PersonMapper.INSTANCE.toEntity(personDto);

        assertEquals(personDto.getEmail(), person.getEmail());
        assertEquals(personDto.getTeam(), person.getGroup());
    }

    @Test
    public void entityToDto() {
        Person person = Person.builder()
                .id(1L)
                .name("Alexander Choi")
                .email("dev.alxndr@gmail.com")
                .birth("1995-01-01")
                .build();

        PersonDto personDto = PersonMapper.INSTANCE.toDto(person);

        assertEquals(personDto.getEmail(), person.getEmail());
        assertEquals(personDto.getTeam(), person.getGroup());
    }
}
