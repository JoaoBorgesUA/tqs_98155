package io.cucumber.skeleton;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final LocalDateTime localDateTime, final LocalDateTime localDateTime2) {

        return store.stream().filter(book -> {
            return localDateTime.isBefore(book.getPublished()) && localDateTime2.isAfter(book.getPublished());
        }).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }
}