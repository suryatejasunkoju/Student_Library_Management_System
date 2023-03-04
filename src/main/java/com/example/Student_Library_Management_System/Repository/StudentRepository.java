package com.example.Student_Library_Management_System.Repository;

import com.example.Student_Library_Management_System.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findByEmail(String email);
    public List<Student> findByCountry(String country);
}
