package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dao.CardDetailDao;
import com.mosesjebish.bookmarkmanager.dto.CardDetailDto;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.entity.CardDetailEntity;
import com.mosesjebish.bookmarkmanager.mapper.CardDetailMapper;
import com.mosesjebish.bookmarkmanager.mapper.GroupDetailMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CardDetailServiceImplTest {

    @Mock
    private CardDetailDao cardDetailDao;

    @Mock
    private GroupDetailService groupDetailService;

    @InjectMocks
    private CardDetailServiceImpl cardDetailService;

    private CardDetailMapper mapper = null;

    PodamFactory factory = null;

    @Before
    public void setUp(){
        factory = new PodamFactoryImpl();
        mapper = Mappers.getMapper(CardDetailMapper.class);
    }

    @Test
    public void persist(){
        GroupDetailDto groupDetailDto = factory.manufacturePojo(GroupDetailDto.class);
        List<GroupDetailDto> groupDetailDtos = new ArrayList<>();
        groupDetailDtos.add(groupDetailDto);

        CardDetailDto cardDetailDto = factory.manufacturePojo(CardDetailDto.class);
        cardDetailDto.setLongUrl("http://test.coom");
        cardDetailDto.setGroupDetailInfo(groupDetailDtos);
        List<CardDetailDto> cardDetailDtos = new ArrayList<>();
        cardDetailDtos.add(cardDetailDto);
        doReturn(groupDetailDtos).when(groupDetailService).fetchAllGroups();

        List<CardDetailEntity> entities = mapper.mapDtosToEntities(cardDetailDtos);

        doReturn(entities).when(cardDetailDao).saveAll(anyIterable());

        List<CardDetailDto> cardDetailDtoList = cardDetailService.persist(cardDetailDtos);

        Assert.assertEquals(cardDetailDtos.size(),cardDetailDtoList.size());
    }

    @Test
    public void fetchAllCards(){

        GroupDetailDto groupDetailDto = factory.manufacturePojo(GroupDetailDto.class);
        List<GroupDetailDto> groupDetailDtos = new ArrayList<>();
        groupDetailDtos.add(groupDetailDto);

        CardDetailDto cardDetailDto = factory.manufacturePojo(CardDetailDto.class);
        cardDetailDto.setLongUrl("http://test.coom");
        cardDetailDto.setGroupDetailInfo(groupDetailDtos);
        List<CardDetailDto> cardDetailDtos = new ArrayList<>();
        cardDetailDtos.add(cardDetailDto);


        doReturn(mapper.mapDtosToEntities(cardDetailDtos)).when(cardDetailDao).findAll();
        doReturn(groupDetailDtos).when(groupDetailService).fetchAllGroups();

        List<CardDetailDto> response = cardDetailService.fetchAllCards();

        Assert.assertEquals(cardDetailDtos.size(),response.size());
    }

    @Test
    public void fetchApprovedCards(){
        GroupDetailDto groupDetailDto = factory.manufacturePojo(GroupDetailDto.class);
        List<GroupDetailDto> groupDetailDtos = new ArrayList<>();
        groupDetailDtos.add(groupDetailDto);

        CardDetailDto cardDetailDto = factory.manufacturePojo(CardDetailDto.class);
        cardDetailDto.setLongUrl("http://test.coom");
        cardDetailDto.setGroupDetailInfo(groupDetailDtos);
        List<CardDetailDto> cardDetailDtos = new ArrayList<>();
        cardDetailDtos.add(cardDetailDto);

        doReturn(mapper.mapDtosToEntities(cardDetailDtos)).when(cardDetailDao).findAllByApproved(true);
        doReturn(groupDetailDtos).when(groupDetailService).fetchAllGroups();

        List<CardDetailDto> response = cardDetailService.fetchApprovedCards(true);

        Assert.assertEquals(cardDetailDtos.size(),response.size());
    }

    @Test
    public void fetchCardByGroup(){
        GroupDetailDto groupDetailDto = factory.manufacturePojo(GroupDetailDto.class);
        List<GroupDetailDto> groupDetailDtos = new ArrayList<>();
        groupDetailDtos.add(groupDetailDto);

        CardDetailDto cardDetailDto = factory.manufacturePojo(CardDetailDto.class);
        cardDetailDto.setLongUrl("http://test.coom");
        cardDetailDto.setGroupDetailInfo(groupDetailDtos);
        List<CardDetailDto> cardDetailDtos = new ArrayList<>();
        cardDetailDtos.add(cardDetailDto);

        doReturn(mapper.mapDtosToEntities(cardDetailDtos)).when(cardDetailDao).findAllByApproved(true);
        doReturn(groupDetailDtos).when(groupDetailService).fetchAllGroups();

        Map<String,List<CardDetailDto>> response = cardDetailService.fetchCardsByGroup("GROUP_NAME");

        Assert.assertNotNull(response);

        ;
    }

}
