package com.tmasolutions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmasolutions.model.AppUser;
import com.tmasolutions.request.ResponseModel;
import com.tmasolutions.service.Impl.AppUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Cacheable(value = "AppUser" , key = "{#email,#page,#size, #sortby}")
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllTutorials(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "email") String sortby
    ) {

        try {
            List<AppUser> tutorials = new ArrayList<AppUser>();
            Pageable paging = PageRequest.of(page, size, Sort.by(sortby).descending());

            Page<AppUser> pageTuts;
            pageTuts = userServiceImpl.findAll(paging);
            if (email == null)
                pageTuts = userServiceImpl.findAll(paging);
            else
                pageTuts = userServiceImpl.findByEmailContaining(email, paging);

            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("/{id}")
    public ResponseModel one(@PathVariable Long id) {
        try {
            AppUser usr = userServiceImpl.findById(id);
            return new ResponseModel(true, usr);
        }catch(Exception e) {
            return new ResponseModel(false, null, e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseModel updateOne(@PathVariable Long id, @RequestBody AppUser newUser) {
        try {
            AppUser usr = userServiceImpl.updateUser(id, newUser);
            return new ResponseModel(true, usr);
        }catch(Exception e) {
            return new ResponseModel(false, null, e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseModel deleteUser(@PathVariable Long id) {
        try {
            userServiceImpl.deleteAppUser(id);
            return new ResponseModel(true, null);
        }catch(Exception e) {
            return new ResponseModel(false, null, e.getMessage());
        }

    }

}
