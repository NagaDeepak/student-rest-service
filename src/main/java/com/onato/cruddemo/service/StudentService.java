package com.onato.cruddemo.service;

import java.util.List;
import java.util.Map;

import javax.validation.spi.ValidationProvider;

import com.onato.cruddemo.entity.Student;

public interface StudentService {

	public List<Student> findAllStudents();
	
	public Student findStudentByRollNo(Integer theId);
		
	public int save(Student student);
	
	public int deleteStudent(Integer rollNo);
	
	public Map<String, Double> getSubjectsAndMarksByStudentId(Integer id);
	
	public Map<String, String> getStudentsByAggregatePercentage(Double percentage);
	
	public List<String> getTopScorers();
	
	public void deleteAll();

}
