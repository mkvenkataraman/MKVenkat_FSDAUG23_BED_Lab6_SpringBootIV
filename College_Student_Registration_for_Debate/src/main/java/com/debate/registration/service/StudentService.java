package com.debate.registration.service;

import java.util.List;

import com.debate.registration.entity.Student;

public interface StudentService {
	public List<Student> findAll();

	public Student findById(int id);

	public void save(Student theStudent);

	public void deleteByid(int id);

	public List<Student> searchStudents(String query);
}
