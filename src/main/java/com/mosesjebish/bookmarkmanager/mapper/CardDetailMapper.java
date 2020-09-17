package com.mosesjebish.bookmarkmanager.mapper;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.dto.UserInfoDto;
import com.mosesjebish.bookmarkmanager.entity.CardDetailEntity;
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
public abstract class CardDetailMapper {
    public abstract CardDetailEntity mapDtoToEntity(CardDetailDto dto);

    @InheritInverseConfiguration(name = "mapDtoToEntity")
    public abstract CardDetailDto mapEntityToDto(CardDetailEntity entity);


    public abstract List<CardDetailEntity> mapDtosToEntities(List<CardDetailDto> dtos);

    @InheritInverseConfiguration(name = "mapDtosToEntities")
    public abstract List<CardDetailDto> mapEntityToDtos(List<CardDetailEntity> entities);

    @AfterMapping
    public void mapDtoExtraInformation(CardDetailDto source, @MappingTarget CardDetailEntity target) {
        if (!CollectionUtils.isEmpty(source.getGroupDetailInfo())) {
            target.setGroupDetails(new Gson().toJson(source.getGroupDetailInfo()));
        }

        if(!CollectionUtils.isEmpty(source.getUserInfoDetails())){
            target.setUserDetails(new Gson().toJson(source.getUserInfoDetails()));
        }
    }

    @AfterMapping
    public void mapEntityExtraInformation(CardDetailEntity source, @MappingTarget CardDetailDto target){
        if(!Strings.isNullOrEmpty(source.getGroupDetails())){
            Type typeOfGroupDetails = new TypeToken<List<GroupDetailDto>>(){}.getType();
            target.setGroupDetailInfo(new Gson().fromJson(source.getGroupDetails(), typeOfGroupDetails));
        }

        if(!Strings.isNullOrEmpty(source.getUserDetails())){
            Type typeOfUserDetails = new TypeToken<List<UserInfoDto>>(){}.getType();
            target.setUserInfoDetails(new Gson().fromJson(source.getUserDetails(), typeOfUserDetails));
        }
    }
}
