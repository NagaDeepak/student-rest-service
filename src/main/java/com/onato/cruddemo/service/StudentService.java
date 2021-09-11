package com.onato.cruddemo.service;

import java.util.List;
import java.util.Map;

import com.onato.cruddemo.entity.Student;

public interface StudentService {

	public List<Student> findAllStudents();

	public Student findStudentByRollNo(Integer theId);

	public int saveAll(List<Student> students);
	
	public Student saveOrUpdateStudent(Student student);

	public int deleteStudent(Integer rollNo);

	public Map<String, Double> getSubjectsAndMarksByStudentId(Integer id);

	public Map<String, String> getStudentsByAggregatePercentage(Double percentage);

	public List<String> getTopScorers();

	public void deleteAll();
	
	public long getCount();

}
