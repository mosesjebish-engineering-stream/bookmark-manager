package com.mosesjebish.bookmarkmanager.service;

import com.google.common.collect.Lists;
import com.mosesjebish.bookmarkmanager.dao.GroupDetailDao;
import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;
import com.mosesjebish.bookmarkmanager.entity.GroupDetailEntity;
import com.mosesjebish.bookmarkmanager.mapper.GroupDetailMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GroupDetailServiceImpl implements GroupDetailService {

    private final GroupDetailMapper mapper;

    private final GroupDetailDao dao;

    public GroupDetailServiceImpl(GroupDetailMapper mapper, GroupDetailDao dao) {
        this.mapper = mapper;
        this.dao = dao;
    }

    @Override
    public List<GroupDetailDto> fetchAllGroups() {
        return mapper.mapEntitiesToDtos(Lists.newArrayList(dao.findAll()));
    }

    @Override
    public List<GroupDetailDto> persist(List<GroupDetailDto> groupDetails) {
        Iterable<GroupDetailEntity> entities = mapper.mapDtosToEntities(groupDetails);
        return mapper.mapEntitiesToDtos(Lists.newArrayList(dao.saveAll(entities)));
    }

    @Override
    public GroupDetailDto findByGroupName(String groupName) {
        Optional<GroupDetailEntity> entityOptional = dao.findByGroupName(groupName);

        if (entityOptional.isEmpty()) {
            return null;
        }

        return mapper.mapEntityToDto(entityOptional.get());
    }
}
