package com.mosesjebish.bookmarkmanager.dao;

import com.mosesjebish.bookmarkmanager.entity.CardDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDetailDao extends CrudRepository<CardDetailEntity, Integer> {

    Iterable<CardDetailEntity> findAllByApproved(Boolean approved);
}
