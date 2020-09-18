package com.mosesjebish.bookmarkmanager.service;

import com.google.gson.Gson;
import com.mosesjebish.bookmarkmanager.dao.GroupDetailDao;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.entity.GroupDetailEntity;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {

    @Mock
    private GroupDetailDao dao;

    @InjectMocks
    private GroupDetailServiceImpl service;

    private GroupDetailMapper mapper = null;

    PodamFactory factory = null;

    @Before
    public void setUp(){
        factory = new PodamFactoryImpl();
        mapper = Mappers.getMapper(GroupDetailMapper.class);
    }

    @Test
    public void fetchAll(){
        List<String> adminList = new ArrayList<>();
        adminList.add("TestUser");
        GroupDetailEntity groupDetailEntity = factory.manufacturePojo(GroupDetailEntity.class);
        groupDetailEntity.setAdminList(new Gson().toJson(adminList));
        List<GroupDetailEntity> groupDetailEntities = new ArrayList<>();
        groupDetailEntities.add(groupDetailEntity);

        doReturn(groupDetailEntities).when(dao).findAll();

        List<GroupDetailDto> response = service.fetchAllGroups();

        Assert.assertEquals(groupDetailEntity.getGroupName(),response.get(0).getGroupName());
    }

    @Test
    public void persist(){
        GroupDetailDto groupDetailDtoInput = factory.manufacturePojo(GroupDetailDto.class);
        List<GroupDetailDto> groupDetailDtoList = new ArrayList<>();
        groupDetailDtoList.add(groupDetailDtoInput);

        List<GroupDetailEntity> daoResponse = mapper.mapDtosToEntities(groupDetailDtoList);

        doReturn(daoResponse).when(dao).saveAll(anyIterable());

        List<GroupDetailDto> groupDetailExpectedOutput = service.persist(groupDetailDtoList);

        Assert.assertEquals(groupDetailDtoList.size(),groupDetailExpectedOutput.size());
    }

    @Test
    public void findByGroupName(){
        List<String> adminList = new ArrayList<>();
        adminList.add("TestUser");
        GroupDetailEntity groupDetailEntity = factory.manufacturePojo(GroupDetailEntity.class);
        groupDetailEntity.setAdminList(new Gson().toJson(adminList));
        Optional<GroupDetailEntity> groupDetailEntityOptional = Optional.of(groupDetailEntity);

        doReturn(groupDetailEntityOptional).when(dao).findByGroupName(anyString());

        GroupDetailDto response = service.findByGroupName("Test");

        Assert.assertEquals(groupDetailEntity.getGroupName(),response.getGroupName());
    }

    @Test
    public void findByGroupNotFound(){
        Optional<GroupDetailEntity> entity = Optional.empty();

        doReturn(entity).when(dao).findByGroupName(anyString());

        GroupDetailDto response = service.findByGroupName("Test");

        Assert.assertNull(response);
    }

}
