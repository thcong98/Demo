package com.tmasolutions.service.Impl;

import com.tmasolutions.model.AppUser;
import com.tmasolutions.model.SessionUser;
import com.tmasolutions.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    AppUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<AppUser> usr = userRepository.findByEmail(username);
        if(usr.size()>0) {
            return new SessionUser(usr.get(0));// new User(usr.get(0).getEmail(), usr.get(0).getPassword(),new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

