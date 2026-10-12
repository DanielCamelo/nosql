package com.nosql.claseJava.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String synopsis;
    private String publishYear;
    private String category;
}