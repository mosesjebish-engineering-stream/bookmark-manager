package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;

import java.util.List;
import java.util.Map;

public interface CardDetailService {
    List<CardDetailDto> persist(List<CardDetailDto> cardDetailDtos);

    List<CardDetailDto> fetchAllCards();

    List<CardDetailDto> fetchApprovedCards(Boolean approved);

    Map<String, List<CardDetailDto>> fetchCardsByGroup(String groupBy);
}
