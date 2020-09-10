package com.mosesjebish.bookmarkmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CardDetailDto {
    private Long id;

    private String shortUrl;

    private String longUrl;

    private String userId;

    private String tribe;

    private String application;

    private String platform;

    private String description;

    private Boolean approved;

    private List<GroupDetailDto> groupDetailInfo;
}
