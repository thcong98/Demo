package com.tmasolutions.repo;

import com.tmasolutions.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    List<AppUser> findByEmail(String Email);
}
