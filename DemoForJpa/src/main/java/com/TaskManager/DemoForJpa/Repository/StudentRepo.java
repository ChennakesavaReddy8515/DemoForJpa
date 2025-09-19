package com.TaskManager.DemoForJpa.Repository;

import com.TaskManager.DemoForJpa.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepo extends JpaRepository<Student,Long> {

}
