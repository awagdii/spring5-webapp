package com.wagdi.spring5webapp.bootstrap;

import com.wagdi.spring5webapp.model.Author;
import com.wagdi.spring5webapp.model.Book;
import com.wagdi.spring5webapp.model.Publisher;
import com.wagdi.spring5webapp.repositories.AuthorRepository;
import com.wagdi.spring5webapp.repositories.BookRepository;
import com.wagdi.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//A bootstrap class that listen to the context event that will call onApplicationEvent when the event happens
//we annotate it as component to be a spring bean and get wired up by spring context
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    //we are doing constructor injection because the class is manged by spring
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        Publisher publisher= new Publisher("Rod Johnson");
        Author wagdi = new Author("Ahmed", "Wagdi");
        Book springBook = new Book("Spring", publisher, 1233L);
        wagdi.getBooks().add(springBook);

        Author john = new Author("john", "adams");
        Book javaBook = new Book("java fundamentals", publisher, 22311L);
        john.getBooks().add(javaBook);


        //save data
        publisherRepository.save(publisher);
        bookRepository.save(springBook);
        bookRepository.save(javaBook);
        authorRepository.save(wagdi);
        authorRepository.save(john);

    }
}
