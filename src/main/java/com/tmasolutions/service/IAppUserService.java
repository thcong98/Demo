package com.tmasolutions.service;

import com.tmasolutions.model.AppUser;

import java.util.List;

public interface IAppUserService {
    AppUser createNewUser(AppUser usr);
    List<AppUser> loadUserByUsername(String Username);
}
