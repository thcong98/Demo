package com.tmasolutions.service.Impl;

import com.tmasolutions.model.AppUser;
import com.tmasolutions.repo.AppUserRepository;
import com.tmasolutions.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppUserService implements IAppUserService {

    @Autowired
    AppUserRepository userRepository;
    @Override
    public AppUser createNewUser(AppUser usr) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usr.setPassword(passwordEncoder.encode(usr.getPassword()));
        // TODO Auto-generated method stub
        return userRepository.save(usr);
    }
    @Override
    public List<AppUser> loadUserByUsername(String Username) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(Username);
    }

}

