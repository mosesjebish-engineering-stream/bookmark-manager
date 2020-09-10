package com.mosesjebish.bookmarkmanager.mapper;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.entity.GroupDetailEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class GroupDetailMapper {
    public abstract GroupDetailEntity mapDtoToEntity(GroupDetailDto dto);

    @InheritInverseConfiguration(name = "mapDtoToEntity")
    public abstract GroupDetailDto mapEntityToDto(GroupDetailEntity entity);

    public abstract List<GroupDetailEntity> mapDtosToEntities(List<GroupDetailDto> dtos);

    @InheritInverseConfiguration(name = "mapDtosToEntities")
    public abstract List<GroupDetailDto> mapEntitiesToDtos(List<GroupDetailEntity> entities);

    @AfterMapping
    public void mapDtoExtraInformation(GroupDetailDto source, @MappingTarget GroupDetailEntity target) {
        if (!CollectionUtils.isEmpty(source.getAdminInfoList())) {
            target.setAdminList(new Gson().toJson(source.getAdminInfoList()));
        }
    }

    @AfterMapping
    public void mapEntityExtraInformation(GroupDetailEntity source, @MappingTarget GroupDetailDto target) {
        if (!Strings.isNullOrEmpty(source.getAdminList())) {
            Type typeOfAdminDetails = new TypeToken<List<String>>() {
            }.getType();
            target.setAdminInfoList(new Gson().fromJson(source.getAdminList(), typeOfAdminDetails));
        }
    }
}
