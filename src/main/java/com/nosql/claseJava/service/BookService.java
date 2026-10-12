package com.nosql.claseJava.service;

import com.nosql.claseJava.controllers.dto.BookRequest;
import com.nosql.claseJava.entity.Book;
import com.nosql.claseJava.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // GET (todos)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // GET (uno)
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    // POST
    public Book create(BookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .synopsis(request.getSynopsis())
                .publishYear(request.getPublishYear())
                .category(request.getCategory())
                .build();
        return bookRepository.save(book);
    }

    // PUT: reemplaza todos los campos
    public Optional<Book> replace(String id, BookRequest request) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setIsbn(request.getIsbn());
            book.setSynopsis(request.getSynopsis());
            book.setPublishYear(request.getPublishYear());
            book.setCategory(request.getCategory());
            return bookRepository.save(book);
        });
    }

    // PATCH: modifica solo los campos enviados (no nulos)
    public Optional<Book> patch(String id, BookRequest request) {
        return bookRepository.findById(id).map(book -> {
            if (request.getTitle() != null) book.setTitle(request.getTitle());
            if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
            if (request.getIsbn() != null) book.setIsbn(request.getIsbn());
            if (request.getSynopsis() != null) book.setSynopsis(request.getSynopsis());
            if (request.getPublishYear() != null) book.setPublishYear(request.getPublishYear());
            if (request.getCategory() != null) book.setCategory(request.getCategory());
            return bookRepository.save(book);
        });
    }

    // DELETE
    public boolean delete(String id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }
}