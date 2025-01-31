package com.mosesjebish.bookmarkmanager.dao;

import com.mosesjebish.bookmarkmanager.entity.GroupDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupDetailDao extends CrudRepository<GroupDetailEntity, Integer> {
    Optional<GroupDetailEntity> findByGroupName(String groupName);
}
