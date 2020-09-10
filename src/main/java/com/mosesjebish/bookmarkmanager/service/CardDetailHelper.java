package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.enums.TypesEnum;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class CardDetailHelper {
    public static Map<String, List<CardDetailDto>> processCardsByGroup(String groupBy, List<CardDetailDto> resultDtos, List<GroupDetailDto> groupDetailDtos) {
        Map<String, List<CardDetailDto>> map = new HashMap<>();

        if (groupBy.equalsIgnoreCase(TypesEnum.APPLICATION.name())) {
            return resultDtos.stream().collect(groupingBy(CardDetailDto::getApplication));
        }
        if (groupBy.equalsIgnoreCase(TypesEnum.TRIBE.name())) {
            return resultDtos.stream().collect(groupingBy(CardDetailDto::getTribe));
        }
        if (groupBy.equalsIgnoreCase(TypesEnum.GROUP_NAME.name())) {
            Map<String, List<CardDetailDto>> finalMap = map;

            groupDetailDtos.forEach(aGroup -> {
                finalMap.put(aGroup.getGroupName(), new ArrayList<CardDetailDto>());
            });


            resultDtos.forEach(aDto -> {
                        aDto.getGroupDetailInfo().forEach(aGroup -> {
                            if (finalMap.containsKey(aGroup.getGroupName())) {
                                List<CardDetailDto> listOfAppCards = finalMap.get(aGroup.getGroupName());
                                listOfAppCards.add(aDto);
                                finalMap.put(aGroup.getGroupName(), listOfAppCards);
                            } else {
                                List<CardDetailDto> cards = new ArrayList<>(); //create a new list if this group has no existing mapping
                                cards.add(aDto);
                                finalMap.put(aGroup.getGroupName(), cards);
                            }
                        });
                    }

            );

            return finalMap;
        }
        if (groupBy.equalsIgnoreCase(TypesEnum.USER.name())) {
            return resultDtos.stream().collect(groupingBy(CardDetailDto::getUserId));
        }

        return Collections.emptyMap();
    }
}
