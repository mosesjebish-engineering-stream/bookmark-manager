package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dao.CardDetailDao;
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
    public void when_fetchAllCards(){
        Assert.assertEquals(1,1);
    }
}
