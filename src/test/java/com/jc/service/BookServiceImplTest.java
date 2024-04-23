package com.jc.service;

import com.jc.model.Book;
import com.jc.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    // System under test: SUT
    BookService bookService;
    // Dependencies
    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void findAll() {
        // Configuración de Mock
        when(bookRepository.findAll()).thenReturn(List.of());

        // Ejecución del método a testear
        List<Book> books = bookService.findAll();

        // Comprobaciones de JUnit
        assertNotNull(books);
        assertEquals(0, books.size());

        // Verificaciones Mockito
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void findByIdFound() {
        // Configuración de Mock
        when(bookRepository.findById(any())).thenReturn(Optional.of(new Book(1L, "book1", "description")));

        // Ejecución del método a testear
        Optional<Book> bookOpt = bookService.findById(1L);

        // Comprobaciones de JUnit
        assertNotNull(bookOpt);
        assertTrue(bookOpt.isPresent());
        assertEquals("BOOK1", bookOpt.get().getTitle());
        // Verificaciones Mockito
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        // Configuración de Mock
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Ejecución del método a testear
        var book = bookService.findById(1L);

        // Comprobaciones de JUnit
        assertTrue(book.isEmpty());

        // Verificaciones Mockito
        verify(bookRepository, times(1)).findById(1L);
    }
}