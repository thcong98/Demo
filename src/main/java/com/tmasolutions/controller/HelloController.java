package com.tmasolutions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmasolutions.model.Book;
import com.tmasolutions.repo.BookRepository;
import com.tmasolutions.service.Impl.BookServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    BookRepository userRepository;

    @Autowired
    BookServiceImpl bookService;
    @GetMapping("/greeting")
    public ResponseEntity<JsonNode> greeting() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //int i = 1/0;
        JsonNode json = mapper.readTree("{\"Greeting\": \"Greetings from Spring Boot!\"}");
        return ResponseEntity.ok(json);
    }

    @PostMapping("/books")
    @ApiOperation(value = "Create new user", notes = "Create new user for system")
    public ResponseEntity<Book> createUser(@RequestBody Book book) throws JsonProcessingException {
        Book dt = userRepository.save(book);
        return ResponseEntity.ok(dt);
    }
    @GetMapping("/books")
    public List<Book> all() {
        return userRepository.findAll();
    }

    @GetMapping("/books/filer")
    public List<Book> all(String search) {
        return bookService.findByNameContaining(search);
    }

    @GetMapping("/books/{id}")
    public Book one(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> null);
    }

}
