package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student){
        //We are letting Card to be saved when student is saved.
        //Order of setting attributes:
        //1st set all attributes of Child class then only set all attributes of Parent Class.
        //ALl attributes of Student except Card attribute have been passed from postman itself.
        //But all attributes of Card are not set.So, set it now.
        Card card=new Card();
        card.setCardStatus(CardStatus.ACTIVATED);//setting cardStatus from Enum
        card.setStudent(student);//passing student object, so that foreign key is derived from this passed student in Card Table.Linking card with student.
        //remaining values of attributes of Card are auto-generated.

        //Now set all attributes that are not set of Student class
        student.setCard(card);//Linking student with card.

        //if we would have used uni-directional mapping, we need to save both entities.
        //Since, we are using bi-directional mapping, saving Parent entity automatically saves Child Entity bcoz of Cascading effect.
        studentRepository.save(student);
        return "Student and Card added successfully";
    }

    public String deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

    public String getNameByEmail(String email) {
        return studentRepository.findByEmail(email).getName();
    }

    public String updateMobileNo(Student newStudent) {
        Student student=studentRepository.findById(newStudent.getId()).get();
        student.setMobileNo(newStudent.getMobileNo());
        studentRepository.save(student);
        return "MobileNo of Student Updated Successfully";
    }

    public String updateName(Student newStudent) {
        Student student=studentRepository.findById(newStudent.getId()).get();
        student.setName(newStudent.getName());
        studentRepository.save(student);
        return "Student Name updated successfully !!!";
    }

    public String updateId(Integer oldId, Integer newId) {
        Student student=studentRepository.findById(oldId).get();
        student.setId(newId);
        studentRepository.save(student);
        return "Id updated Successfully !!!";
    }
}
