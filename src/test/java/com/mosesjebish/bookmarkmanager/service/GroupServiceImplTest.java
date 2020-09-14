package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dao.GroupDetailDao;
import com.mosesjebish.bookmarkmanager.mapper.GroupDetailMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {

    @MockBean
    GroupDetailDao dao;

    @InjectMocks
    GroupDetailServiceImpl service;

    private GroupDetailMapper mapper = Mappers.getMapper(GroupDetailMapper.class);

    @Test
    public void persist(){

    }

}
