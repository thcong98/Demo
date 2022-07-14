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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookServiceImpl bookService;
    @GetMapping("/greeting")
    public ResponseEntity<JsonNode> greeting() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //int i = 1/0;
        JsonNode json = mapper.readTree("{\"Greeting\": \"Greetings from Spring Boot!\"}");
        return ResponseEntity.ok(json);
    }

    @Transactional
    @PostMapping("/books")
    @ApiOperation(value = "Create new book", notes = "Create new book for system")
    public ResponseEntity<Book> createUser(@RequestBody Book book) throws JsonProcessingException {
        Book dt = bookRepository.save(book);
        System.out.println(dt.getId());
        return ResponseEntity.ok(dt);
    }
    @GetMapping("/books")
    public List<Book> all() {
        return bookRepository.findAll();
    }
    @GetMapping("/books/findbyname/{name}")
    public List<Book> findbyname(@PathVariable String name) {
        return bookRepository.findByName(name);
    }
    @GetMapping("/books/filer")
    public List<Book> all(String search) {
        return bookService.findByNameContaining(search);
    }

    @GetMapping("/books/{id}")
    public Book one(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> null);
    }

    @Transactional
    @PutMapping("/books/{id}/required-no-store-data")
    public Book updateOne(@PathVariable Long id, String name, String description, String author, String language){
        bookService.updateNameRequired(id, name);//is not stored to DB
        bookService.updateAuthorNameRequired(id, description);//is not stored to DB
        bookService.updateDescriptionRequired(id, author);//is not stored to DB
        return bookService.updateLanguageRequired(id, language);//is not stored to DB
    }

    @Transactional()
    @PutMapping("/books/{id}/required-new-storedata-except-language")
    public Book updateOneNew(@PathVariable Long id, String NewName, String Description, String NewAuthor, String NewLanguage){
        bookService.updateNameRequiresNew(id, NewName);//is stored to DB
        bookService.updateAuthorNameRequiresNew(id, NewAuthor);//is stored to DB
        bookService.updateDescriptionRequiresNew(id, Description);//is stored to DB
        return bookService.updateLanguageRequiresNew(id, NewLanguage);//is not stored to DB
    }
    @Transactional
    @PutMapping("/books/{id}/a-test-case")
    public Book updateOneNew1(@PathVariable Long id, String NewName, String Description, String NewAuthor, String NewLanguage){
        bookService.updateNameRequiresNew(id, NewName);//is stored to DB
        bookService.updateAuthorNameRequiresNew(id, NewAuthor);//is stored to DB
        bookService.updateDescriptionRequiresNew(id, Description);//is stored to DB
        return bookService.updateLanguageRequiresNew(id, NewLanguage);//is not stored to DB
    }
    @PutMapping("/books/{id}/mandatory-passed-store-data")
    @Transactional
    public Book updateOneMandatory(@PathVariable Long id, String NewName){
        return  bookService.updateNameMandatory(id, NewName); // Passed at normal
    }
    @PutMapping("/books/{id}/mandatory-failed-no-store-data")
    public Book updateOneMandatoryFailed(@PathVariable Long id, String NewName){
        return bookService.updateNameMandatory(id, NewName);//Throw exception
    }
    @PutMapping("/books/{id}/never-passed-store-data")
    public Book updateOneNeverPassed(@PathVariable Long id, String NewName){
        return  bookService.updateNameNever(id, NewName); // Passed at normal
    }
    @Transactional
    @PutMapping("/books/{id}/never-failed-no-store-data")
    public Book updateOneNeverFailed(@PathVariable Long id, String NewName){
        return bookService.updateNameNever(id, NewName);//Throw exception
    }
    @Transactional
    @PutMapping("/books/{id}/supports-hastransaction-will-be-rolledback")
    public Book supportsHasTransactionWillBeRollback(@PathVariable Long id, String NewLanguage){
        return bookService.updateLanguageSupports(id, NewLanguage);//Throw exception
    }
    @PutMapping("/books/{id}/supports-notransaction-will-storeData")
    public Book supportsNotTransactionWillBeStoreData(@PathVariable Long id, String NewLanguage){
        return bookService.updateLanguageSupports(id, NewLanguage);//Throw exception
    }
    @PutMapping("/books/{id}/not-supports-hastransaction-will-storeData")
    public Book notSupportsHasTransactionWillBeStoreData(@PathVariable Long id, String NewLanguage){
        return bookService.updateLanguageNotSupports(id, NewLanguage);//Throw exception
    }
    @PutMapping("/books/{id}/not-supports-notransaction-will-storeData")
    public Book notSupportsNotTransactionWillBeStoreData(@PathVariable Long id, String NewLanguage){
        return bookService.updateLanguageNotSupports(id, NewLanguage);//Throw exception
    }
    @Transactional
    @PutMapping("/books/{id}/nested-storedata-except-language")
    public Book updateOneNested(@PathVariable Long id, String NewName, String description, String NewAuthor, String NewLanguage){
        bookService.updateNameNested(id, NewName);//is stored to DB
        bookService.updateAuthorNameNested(id, NewAuthor);//is stored to DB
        bookService.updateDescriptionNested(id, description);//is stored to DB
        return bookService.updateLanguageNested(id, NewLanguage);//is not stored to DB
    }
    @Transactional
    @PutMapping("/books/{id}/name-transaction-readonly")
    public Book updateOne(@PathVariable Long id, String NewName){
        return bookService.updateNameReadOnly(id, NewName);
    }
    @PutMapping("/books/{id}/rollbackname")
    public Book rollbackName(@PathVariable Long id, String NewName) throws SQLException{
        return bookService.updateNameRollBack(id, NewName);
    }

    @PutMapping("/books/findByName")
    public List<Book> rollbackname(String Name) throws SQLException {
        return bookRepository.findByName(Name);
    }
}
