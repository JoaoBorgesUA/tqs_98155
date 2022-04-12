package io.cucumber.skeleton;

import java.time.LocalDateTime;

public class Book {
    private final String title;
    private final String author;
    private final LocalDateTime published;

    public Book(String title, String author, LocalDateTime ldt) {
        this.title = title;
        this.author = author;
        this.published = ldt;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public LocalDateTime getPublished() {
        return this.published;
    }

}