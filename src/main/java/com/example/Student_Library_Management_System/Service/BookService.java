package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    public String addNewBook(Book book) {
        //Book has 2 relations, With Card and Author
        //We don't need Card object to save Book object, because this book can be later issued by Card.

        //We need Author object to save Book object, because without author there will be no book.
        //We pass author(within Book object) from postman, then we can get Author object using authorId
        //save this book with current Author and save author
        //No, need to save book as it will be saved by cascading effect.

        Long authorId=book.getAuthor().getId();//getting authorId
        Author author=authorRepository.findById(authorId).get();//getting author object
        book.setAuthor(author);
        List<Book> prevBookList=author.getBooksWritten();//getting list of books written by author.
        prevBookList.add(book);//updating list of books written by author.
        author.setBooksWritten(prevBookList);

        //saving author and book(cascading effect).
        authorRepository.save(author);
        //save(Entity) creates row if Entity's primary key doesn't exists, else it updates the same row.


        return "Book added successfully";
    }

    //My Extra APIs' Service Methods.
    public String updateAuthorId(Long oldId, Long newId, Long bookId) {
        Book book= bookRepository.findById(bookId).get();

        Author author=authorRepository.findById(oldId).get();
        author.setId(newId);

        List<Book> booksWritten=author.getBooksWritten();
        booksWritten.remove(book);

        book.setAuthor(author);
        booksWritten.add(book);
        author.setBooksWritten(booksWritten);

        authorRepository.save(author);
        bookRepository.save(book);
        return "author_id updated Successfully !!!";
    }
}
