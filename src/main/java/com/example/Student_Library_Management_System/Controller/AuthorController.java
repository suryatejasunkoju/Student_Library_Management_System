package com.example.Student_Library_Management_System.Controller;

import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import com.example.Student_Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/add")
    public String addNewAuthor(@RequestBody Author author){
        return authorService.addNewAuthor(author);
    }

    //My extra APIs' methods.
    @PutMapping("/update_id_by_PathParam/{id}")
    public String updateAuthorId(@PathVariable("id")Long oldId, @RequestParam("newId")Long newId){
        return authorService.updateAuthorId(oldId, newId);
    }

    @PutMapping("/update_id_by_AuthorObj")
    public String updateAuthorId(@RequestParam("oldId")Long oldId, @RequestBody Author author){
        return authorService.updateAuthorIdByAuthorObj(oldId,author);
    }
}
