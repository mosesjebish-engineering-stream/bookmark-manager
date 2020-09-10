package com.mosesjebish.bookmarkmanager.service;

import com.mosesjebish.bookmarkmanager.dto.GroupDetailDto;

import java.util.List;

public interface GroupDetailService {
    List<GroupDetailDto> fetchAllGroups();

    List<GroupDetailDto> persist(List<GroupDetailDto> groupDetails);

    GroupDetailDto findByGroupName(String groupName);
}
