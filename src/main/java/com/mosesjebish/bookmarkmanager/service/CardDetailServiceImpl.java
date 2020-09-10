package com.mosesjebish.bookmarkmanager.service;

import com.google.common.collect.Lists;
import com.mosesjebish.bookmarkmanager.dao.CardDetailDao;
import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.entity.CardDetailEntity;
import com.mosesjebish.bookmarkmanager.mapper.CardDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class CardDetailServiceImpl implements CardDetailService {

    private final CardDetailDao dao;

    private final CardDetailMapper mapper;

    private final GroupDetailService groupDetailService;

    public CardDetailServiceImpl(CardDetailDao dao, CardDetailMapper mapper, GroupDetailService groupDetailService) {
        this.dao = dao;
        this.mapper = mapper;
        this.groupDetailService = groupDetailService;
    }

    @Override
    public List<CardDetailDto> persist(List<CardDetailDto> cardDetailDtos) {
        List<CardDetailEntity> entities = mapper.mapDtosToEntities(cardDetailDtos);
        return mapper.mapEntityToDtos(Lists.newArrayList(dao.saveAll(entities)));
    }

    @Override
    public List<CardDetailDto> fetchAllCards() {
        return mapper.mapEntityToDtos(Lists.newArrayList(dao.findAll()));
    }

    @Override
    public List<CardDetailDto> fetchApprovedCards(Boolean approved) {
        return mapper.mapEntityToDtos(Lists.newArrayList(dao.findAllByApproved(approved)));
    }

    @Override
    public Map<String, List<CardDetailDto>> fetchCardsByGroup(String groupBy) {
        List<CardDetailDto> resultDtos = fetchAllCards();
        List<GroupDetailDto> groupDetailDtos = groupDetailService.fetchAllGroups();
        Map<String, List<CardDetailDto>> map = CardDetailHelper.processCardsByGroup(groupBy, resultDtos,groupDetailDtos);

        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }

        return map;
    }
}
