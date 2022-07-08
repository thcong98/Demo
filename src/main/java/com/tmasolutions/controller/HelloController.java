package com.tmasolutions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmasolutions.model.Book;
import com.tmasolutions.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    BookRepository bookRepository;
    @GetMapping("/greeting")
    public ResponseEntity<JsonNode> get() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = json = mapper.readTree("{\"Greeting\": \"Greetings from Spring Boot!\"}");
        return  ResponseEntity.ok(json);
    }
    @GetMapping("listbooks")
    List<Book> getListBooks(){
        return bookRepository.findAll();
    }
    @PostMapping("addBook")
    ResponseEntity<Book> addBook(@RequestBody Book book)throws JsonProcessingException{
        Book dt = bookRepository.save(book);
        return ResponseEntity.ok(dt);
    }
}
