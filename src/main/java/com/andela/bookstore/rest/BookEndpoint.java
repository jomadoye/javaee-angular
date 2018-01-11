package com.andela.bookstore.rest;

import com.andela.bookstore.model.Book;
import com.andela.bookstore.repository.BookRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
public class BookEndpoint {

    public Book getBook(Long id) {
        return bookRepository.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.size() == 0) return Response.noContent().build();
        return Response.ok(books).build();
    }

    public Long countBook() {
        return bookRepository.countAll();
    }

    public Book createBook(Book book) {
        return bookRepository.create(book);
    }

    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    @Inject
    private BookRepository bookRepository;

}
