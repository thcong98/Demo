package com.tmasolutions.utils;

import com.tmasolutions.model.AppUser;
import com.tmasolutions.model.SessionUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionHelper {
    public static AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return ((SessionUser)authentication.getPrincipal()).getUser();
        }
        return null;
    }
}


