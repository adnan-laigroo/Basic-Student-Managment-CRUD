package com.magic.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

}
