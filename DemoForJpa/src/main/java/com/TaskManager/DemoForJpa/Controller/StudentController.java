package com.TaskManager.DemoForJpa.Controller;


import com.TaskManager.DemoForJpa.Entity.Student;
import com.TaskManager.DemoForJpa.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;


    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
      return new ResponseEntity<Student>(studentRepo.save(student), HttpStatus.CREATED) ;
    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getStudent(){
        return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
    }


    @GetMapping("/api/students/{id}")
    public ResponseEntity<List<Student>> getStudent(@PathVariable Long id){
        Optional<Student>  student=studentRepo.findById(id);
        if(student.isPresent()){
            return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,@RequestBody Student stud){
        Optional<Student> student=studentRepo.findById(id);
                if(student.isPresent()){
                    student.get().setName(stud.getName());
                    student.get().setEmail(stud.getEmail());
                    student.get().setAddress(stud.getAddress());
                    return new ResponseEntity<>(studentRepo.save(student.get()),HttpStatus.OK);
                }else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
    }

    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        Optional<Student> student=studentRepo.findById(id);
        if(student.isPresent()){
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
