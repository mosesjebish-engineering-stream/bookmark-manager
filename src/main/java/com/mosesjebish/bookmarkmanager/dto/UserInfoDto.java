package com.mosesjebish.bookmarkmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoDto {

    private String userId;

    private String userName;

    private List<String> permissions;
}
