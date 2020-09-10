package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.enums.TypesEnum;
import org.springframework.util.CollectionUtils;

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

            groupDetailDtos.forEach(aGroup -> {
                map.put(aGroup.getGroupName(), new ArrayList<>());
            });


            resultDtos.forEach(aDto -> {
                        if (!CollectionUtils.isEmpty(aDto.getGroupDetailInfo())) {
                            aDto.getGroupDetailInfo().forEach(aGroup -> {
                                if (map.containsKey(aGroup.getGroupName())) {
                                    List<CardDetailDto> listOfAppCards = map.get(aGroup.getGroupName());
                                    listOfAppCards.add(aDto);
                                    map.put(aGroup.getGroupName(), listOfAppCards);
                                } else {
                                    List<CardDetailDto> cards = new ArrayList<>(); //create a new list if this group has no existing mapping
                                    cards.add(aDto);
                                    map.put(aGroup.getGroupName(), cards);
                                }
                            });
                        }
                    }

            );

            return map;
        }
        if (groupBy.equalsIgnoreCase(TypesEnum.USER.name())) {
            return resultDtos.stream().collect(groupingBy(CardDetailDto::getUserId));
        }

        return Collections.emptyMap();
    }
}
