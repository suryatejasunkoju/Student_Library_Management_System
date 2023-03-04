package com.example.Student_Library_Management_System.Controller;

import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("/get_name_by_email")
    public String getNameByEmail(@RequestParam("email") String email){
        return "Hi,Your name is"+studentService.getNameByEmail(email);
    }

    @PutMapping("/update_mobNo")
    public String updateMobileNo(@RequestBody Student student){
        return studentService.updateMobileNo(student);
    }


    //My extra API's
    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam("id") Integer id){
        return studentService.deleteStudent(id);
    }

    @PutMapping("/update_name")
    public String updateNameById(@RequestBody Student student){
        return studentService.updateName(student);
    }

    @PutMapping("/update_id/{id}")
    public String updateId(@PathVariable("id")Integer oldId, @RequestParam("newId")Integer newId){
        System.out.println("oldId="+oldId+", newId="+newId);//They are getting printed correctly.
        return studentService.updateId(oldId, newId);
    }
}
