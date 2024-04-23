package com.jc.controller;

import org.springframework.ui.Model;
import com.jc.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {

    // System under test: SUT
    BookController bookController;
    // Dependencies
    @Mock
    BookService bookService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookController = new BookController(bookService);
    }

    @Test
    void findAll() {
        when(bookService.findAll()).thenReturn(List.of());

        String view = bookController.findAll(model);

        assertEquals("book-list", view);
        verify(model, times(1)).addAttribute(any(), any());
        verify(bookService, times(1)).findAll();
    }

    @Test
    void findById() {
    }
}