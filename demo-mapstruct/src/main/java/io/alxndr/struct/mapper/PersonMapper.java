package io.alxndr.struct.mapper;

import io.alxndr.struct.Person;
import io.alxndr.struct.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author : Alexander Choi
 * @date : 2021/03/10
 */
@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    /**
     * source: 인자로 받은 carDto target : 리턴대상의 entity 객체
     *
     * {@code @Mapping} source = 2번쨰 인자값 / target = Car.ageGroup
     *
     * @return {@link Person}
     */
    @Mapping(target = "id", ignore = true)  // ID는 제외
    @Mapping(source = "team", target = "group") // PersonDto.team => Person.group 에 매칭
    @Mapping(target = "creationDate", expression = "java(java.time.LocalDateTime.now())")   // Person.CreationDate LocalDateTime 으로 초기화
    Person toEntity(PersonDto personDto);

    @Mapping(source = "group", target = "team")
    PersonDto toDto(Person person);

}
