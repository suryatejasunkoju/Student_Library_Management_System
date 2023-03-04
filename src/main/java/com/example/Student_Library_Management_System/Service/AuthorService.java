package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public String addNewAuthor(Author author){
        //we are only initializing booksIssued List now,
        // since there would be no books that will be added now.

        authorRepository.save(author);
        return "Author added Successfully";
    }

    //My extra APIs' methods.

    //Tushar told that we can alter ID(Primary Keys).But, I am getting
    //org.hibernate.HibernateException: identifier of an instance of com.example.Student_Library_Management_System.Models.Author was altered from 3 to 1.

    public String updateAuthorId(Long oldId, Long newId) {
        Author author=authorRepository.findById(oldId).get();
        author.setId(newId);
        authorRepository.save(author);
        return "Author Id updated Successfully !!!";
    }

    //Tushar told that we can alter ID(Primary Keys).But, It's saving author(obj) with next continuous id but not with id that we have passed through postman.
    public String updateAuthorIdByAuthorObj(Long oldId, Author author) {
        Author originalAuthor=authorRepository.findById(oldId).get();
//        originalAuthor.setId(author.getId());

        System.out.println("oldId="+oldId+", newId="+author.getId());
        author.setBooksWritten(originalAuthor.getBooksWritten());
        author.setAge(originalAuthor.getAge());
        author.setName(originalAuthor.getName());
        author.setRating(originalAuthor.getRating());
        author.setCountry(originalAuthor.getCountry());
        authorRepository.save(author);
        return "Author Id updated Successfully !!!";

    }
}
