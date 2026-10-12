package com.nosql.claseJava.controllers;

import com.nosql.claseJava.controllers.dto.BookRequest;
import com.nosql.claseJava.entity.Book;
import com.nosql.claseJava.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // GET /books
    @GetMapping
    public List<Book> getAll() {
        return bookService.findAll();
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable String id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /books
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(request));
    }

    // PUT /books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Book> replace(@PathVariable String id, @RequestBody BookRequest request) {
        return bookService.replace(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /books/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Book> patch(@PathVariable String id, @RequestBody BookRequest request) {
        return bookService.patch(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return bookService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // OPTIONS /books
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options() {
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT,
                        HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.OPTIONS)
                .build();
    }
}