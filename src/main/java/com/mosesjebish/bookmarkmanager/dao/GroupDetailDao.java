package com.mosesjebish.bookmarkmanager.dao;

import com.mosesjebish.bookmarkmanager.entity.GroupDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDetailDao extends CrudRepository<GroupDetailEntity, Integer> {
}
