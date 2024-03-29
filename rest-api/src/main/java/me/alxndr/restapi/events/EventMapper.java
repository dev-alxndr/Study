package me.alxndr.restapi.events;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author : Alexander Choi
 * @date : 2021/08/02
 */
@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "offline", ignore = true)
    @Mapping(target = "free", ignore = true)
    @Mapping(target = "eventStatus", ignore = true)
    Event toEntity(EventDto eventDto);

    EventDto toDto(Event person);

}
