package com.mosesjebish.bookmarkmanager.mapper;

import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.entity.GroupDetailEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupDetailMapper {
    GroupDetailEntity mapDtoToEntity(GroupDetailDto dto);

    GroupDetailDto mapEntityToDto(GroupDetailEntity entity);

    List<GroupDetailEntity> mapDtosToEntities(List<GroupDetailDto> dtos);

    List<GroupDetailDto> mapEntitiesToDtos(List<GroupDetailEntity> entities);
}
