package com.mosesjebish.bookmarkmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GroupCardDetailsResponseDto {
    private Map<String, List<CardDetailDto>> groupCardDetails;
}
