package com.jc.service;

import com.jc.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
}