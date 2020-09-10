package com.mosesjebish.bookmarkmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupDetailsResponseDto {
    private List<GroupDetailDto> groupDetailDtoList;
}
