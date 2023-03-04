package com.example.Student_Library_Management_System.Controller;

import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public String addNewBook(@RequestBody Book book){
        return bookService.addNewBook(book);
    }

    //My Extra API's
    @PutMapping("/{bookId}/update_authorId/{id}")
    public String updateAuthorId(@PathVariable("id")Long oldId, @RequestParam("newAuthorId")Long newId, @PathVariable("bookId")Long bookId){
        System.out.println("bookId="+bookId+", oldId="+oldId+", newId="+newId);
        return bookService.updateAuthorId(oldId, newId, bookId);
    }
}
