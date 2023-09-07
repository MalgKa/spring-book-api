package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.pojo.Book;
import pl.coderslab.services.BookService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }


//    @RequestMapping("/helloBook")
//    public Book helloBook() {
//        return new Book(1L, "9788324631766", "Thinking in Java",
//                "Bruce Eckel", "Helion", "programming");
//    }

    @GetMapping
    public List<Book> listOfBooks() {
        return bookService.getBooks();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id){
        bookService.delete(id);
    }

    @GetMapping("/{id}")
    public Book getOneBook(@PathVariable("id") Long id){
       return bookService.get(id).orElse(null);
    }

    @PutMapping
    public void updateBook(@RequestBody Book book){
        bookService.update(book);
    }

    @DeleteMapping
    public void deleteAllBooks(){
        bookService.deleteAll();
    }

}
