package com.mosesjebish.bookmarkmanager.service;

import com.google.common.collect.Lists;
import com.mosesjebish.bookmarkmanager.dao.CardDetailDao;
import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.entity.CardDetailEntity;
import com.mosesjebish.bookmarkmanager.mapper.CardDetailMapper;
import com.mosesjebish.bookmarkmanager.mapper.GroupDetailMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class CardDetailServiceImpl implements CardDetailService {

    private final CardDetailDao dao;

    private CardDetailMapper mapper = Mappers.getMapper(CardDetailMapper.class);

    private final GroupDetailService groupDetailService;

    public CardDetailServiceImpl(CardDetailDao dao, GroupDetailService groupDetailService) {
        this.dao = dao;
        this.groupDetailService = groupDetailService;
    }

    @Override
    public List<CardDetailDto> persist(List<CardDetailDto> cardDetailDtos) {

        cardDetailDtos.forEach(aCardDto -> {
            URLShortener u = new URLShortener(5, "www.mosesjebish.com/card");
            aCardDto.setShortUrl(u.shortenURL(aCardDto.getLongUrl()));
        });

        List<GroupDetailDto> groupDetailDtos = groupDetailService.fetchAllGroups();

        cardDetailDtos = GroupDetailHelper.enrichCardsWithGroupDetails(cardDetailDtos, groupDetailDtos);


        List<CardDetailEntity> entities = mapper.mapDtosToEntities(cardDetailDtos);
        return mapper.mapEntityToDtos(Lists.newArrayList(dao.saveAll(entities)));
    }

    @Override
    public List<CardDetailDto> fetchAllCards() {
        List<GroupDetailDto> groupDetailDtos = groupDetailService.fetchAllGroups();

        List<CardDetailDto> cardDetailDtos = mapper.mapEntityToDtos(Lists.newArrayList(dao.findAll()));

        cardDetailDtos = GroupDetailHelper.enrichCardsWithGroupDetails(cardDetailDtos, groupDetailDtos);

        return cardDetailDtos;
    }

    @Override
    public List<CardDetailDto> fetchApprovedCards(Boolean approved) {
        List<GroupDetailDto> groupDetailDtos = groupDetailService.fetchAllGroups();

        List<CardDetailDto> cardDetailDtos = mapper.mapEntityToDtos(Lists.newArrayList(dao.findAllByApproved(approved)));

        cardDetailDtos = GroupDetailHelper.enrichCardsWithGroupDetails(cardDetailDtos, groupDetailDtos);

        return cardDetailDtos;
    }

    @Override
    public Map<String, List<CardDetailDto>> fetchCardsByGroup(String groupBy) {
        List<CardDetailDto> resultDtos = fetchApprovedCards(true);
        List<GroupDetailDto> groupDetailDtos = groupDetailService.fetchAllGroups();
        Map<String, List<CardDetailDto>> map = CardDetailHelper.processCardsByGroup(groupBy, resultDtos, groupDetailDtos);

        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }

        return map;
    }
}
