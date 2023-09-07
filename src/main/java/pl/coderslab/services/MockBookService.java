package pl.coderslab.services;

import org.springframework.stereotype.Component;

import pl.coderslab.pojo.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MockBookService implements BookService {
    private static Long nextId = 1L;
    private List<Book> list = new ArrayList<>();


    //przykładowa lista do testowania
//    public MockBookService() {
//        list = new ArrayList<>();
//        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
//        list.add(new Book(2L, "9788324627738", "Rusz glowa Java.", "Sierra Kathy, Bates	Bert", "Helion",
//                "programming"));
//        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary Cornell", "Helion",
//                "programming"));
//    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public Optional<Book> get(Long id) {
        Optional<Book> book = list.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
        return book;
    }

    @Override
    public void add(Book book) {
        book.setId(nextId);
        nextId++;
        list.add(book);
    }

    @Override
    public void delete(Long id) {
        List<Book> newList = list.stream()
                .filter(s -> !s.getId().equals(id))
                .collect(Collectors.toList());
        list = newList;
    }

    @Override
    public void update(Book book) {
        for(Book bookToUpdate: list){
            if(bookToUpdate.getId().equals(book.getId())){
                bookToUpdate.setIsbn(book.getIsbn());
                bookToUpdate.setTitle(book.getTitle());
                bookToUpdate.setAuthor(book.getAuthor());
                bookToUpdate.setPublisher(book.getPublisher());
                bookToUpdate.setType(book.getType());
            }
        }
    }

    @Override
    public void deleteAll() {
       list.clear();
    }
}
