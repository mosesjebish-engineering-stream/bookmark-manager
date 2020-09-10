package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;

public class GroupDetailHelper {
    public static List<CardDetailDto> enrichCardsWithGroupDetails(List<CardDetailDto> cardDetailDtos, List<GroupDetailDto> groupDetailDtos) {
        Map<Long, List<GroupDetailDto>> groupMap = groupDetailDtos.stream().collect(groupingBy(GroupDetailDto::getId));
        cardDetailDtos.forEach(aCard -> {
            List<GroupDetailDto> groupDetailsList = new ArrayList<>();
            aCard.getGroupDetailInfo().forEach(aGroup -> {
                Optional<GroupDetailDto> groupDetailDto = groupMap.get(aGroup.getId()).stream().findFirst();
                groupDetailDto.ifPresent(groupDetailsList::add);
            });
            if (!CollectionUtils.isEmpty(groupDetailsList)) {
                aCard.setGroupDetailInfo(groupDetailsList);
            }
        });

        return cardDetailDtos;
    }
}
