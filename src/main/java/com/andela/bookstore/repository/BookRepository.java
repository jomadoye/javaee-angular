package com.andela.bookstore.repository;


import com.andela.bookstore.model.Book;
import com.andela.bookstore.util.NumberGenerator;
import com.andela.bookstore.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class BookRepository {

    @PersistenceContext(unitName="bookStorePU")
    private EntityManager em;

    @Inject
    private TextUtil textUtil;
    @Inject
    private NumberGenerator generator;

    @Transactional(Transactional.TxType.SUPPORTS)
    public Book find(Long id) {
        return em.find(Book.class, id);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b order by b.title", Book.class);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("SELECT count(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(Book book) {
        book.setTitle(textUtil.sanitize(book.getTitle()));
        book.setIsbn(generator.generateNumber());
        em.persist(book);
        return book;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        Book bookToDelete = em.getReference(Book.class, id);
        em.remove(bookToDelete);
    }


}
