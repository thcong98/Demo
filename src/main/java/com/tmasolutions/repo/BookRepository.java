package com.tmasolutions.repo;

import com.tmasolutions.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    List<Book> findByName(String name);
    List<Book> findByNameContaining(String search);
}
