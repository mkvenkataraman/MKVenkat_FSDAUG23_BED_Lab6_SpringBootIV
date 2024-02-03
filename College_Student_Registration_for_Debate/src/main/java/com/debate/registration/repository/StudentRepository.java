package com.debate.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debate.registration.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findByFirstNameContainingOrCourseContaining(String firstName, String course);
}
