package com.tmasolutions.service.Impl;

import com.tmasolutions.model.Book;
import com.tmasolutions.repo.BookRepository;
import com.tmasolutions.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    private BookRepository bookRepository;

    @Override
    public List<Book> findByNameContaining(String search) {
        return bookRepository.findByNameContaining(search);
    }
}
