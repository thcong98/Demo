package com.tmasolutions.service;

import com.tmasolutions.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findByNameContaining(String search);
}
