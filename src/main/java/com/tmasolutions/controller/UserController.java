package com.tmasolutions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmasolutions.model.AppUser;
import com.tmasolutions.service.Impl.AppUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    AppUserService userServiceImpl;

    @PostMapping("/")
    @ApiOperation(value = "Create new user", notes = "Create new user for system")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser user) throws JsonProcessingException {
        AppUser dt = userServiceImpl.createNewUser(user);
        return ResponseEntity.ok(dt);

    }

    @GetMapping("/require_role_USER")
    public ResponseEntity<?> require_role_USER() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"Greeting\": \"You are USER\"}");
        return ResponseEntity.ok(json);
    }

    @GetMapping("/require_role_ADMIN")
    public ResponseEntity<?> require_role_ADMIN() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree("{\"Greeting\": \"You are ADMIN\"}");
        return ResponseEntity.ok(json);
    }

}
