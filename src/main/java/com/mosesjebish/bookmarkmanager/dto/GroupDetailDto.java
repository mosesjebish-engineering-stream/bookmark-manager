package com.mosesjebish.bookmarkmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupDetailDto {

    private Long id;

    private String groupName;

    private String createdBy;

    private List<String> adminInfoList;

    private List<CardDetailDto> cardDetailInfo;

}
