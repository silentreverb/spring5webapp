/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author grune
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;
    
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }
    
    private void initData() {
        // Eric
        Author eric = new Author("Eric", "Evans");
        Publisher hc = new Publisher("Harper Collins", "555 Ghetto Drive", "Suite 100", "Los Santos", "CA", "90001");
        Book ddd = new Book("Domain Driven Design", "1234", hc);
        eric.getBooks().add(ddd);
        
        authorRepository.save(eric);
        publisherRepository.save(hc);
        bookRepository.save(ddd);
        
        // Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx", "123 Test Street", "Suite 202", "New York", "NY", "10001");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);
        
        authorRepository.save(rod);
        publisherRepository.save(worx);
        bookRepository.save(noEJB);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        initData();
    }
    
}
