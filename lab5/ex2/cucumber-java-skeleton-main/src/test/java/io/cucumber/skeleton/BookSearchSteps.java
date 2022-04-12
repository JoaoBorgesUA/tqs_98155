package io.cucumber.skeleton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @Given("a(nother) book with the title {string}, written by {string}, published in {int} {word} {int}")
    public void newBook(final String title, final String author, Integer int1, String month, Integer int3) {
        Book book = new Book(title, author,
                LocalDateTime.of(int3, Month.valueOf(month.toUpperCase()).getValue(), int1, 0, 0));
        library.addBook(book);
    }

    @When("the customer searches for books published between {int} and {int}")
    public void setSearchParameters(final int from, final int to) {
        result = library.findBooks(LocalDateTime.of(from, 1, 1, 0, 0), LocalDateTime.of(to, 12, 31, 0, 0));
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertEquals(result.size(), booksFound);
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        assertEquals(result.get(position - 1).getTitle(), title);
    }
}