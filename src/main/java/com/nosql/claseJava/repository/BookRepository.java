package com.nosql.claseJava.repository;

import com.nosql.claseJava.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}