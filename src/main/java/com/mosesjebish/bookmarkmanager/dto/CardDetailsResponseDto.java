package com.mosesjebish.bookmarkmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CardDetailsResponseDto {
    private List<CardDetailDto> cardDetailDtoList;
}
