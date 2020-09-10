package com.mosesjebish.bookmarkmanager.mapper;

import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.entity.CardDetailEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardDetailMapper {
    CardDetailEntity mapDtoToEntity(CardDetailDto dto);

    CardDetailDto mapEntityToDto(CardDetailEntity entity);

    List<CardDetailEntity> mapDtosToEntities(List<CardDetailDto> dtos);

    List<CardDetailDto> mapEntityToDtos(List<CardDetailEntity> entities);
}
