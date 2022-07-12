package com.tmasolutions.service;

import com.tmasolutions.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAppUserService {
    AppUser createNewUser(AppUser usr);
    List<AppUser> loadUserByUsername(String Username);
    Page<AppUser> findAll(Pageable pageable);
    Page<AppUser> findByEmailContaining(String email, Pageable pageable);
    AppUser findById(Long id);
    AppUser updateUser(Long id, AppUser newAppUser);
    void deleteAppUser(Long id);

}
