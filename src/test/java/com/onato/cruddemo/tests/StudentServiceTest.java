package com.onato.cruddemo.tests;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.onato.cruddemo.dao.StudentRepository;
import com.onato.cruddemo.entity.Student;
import com.onato.cruddemo.entity.Subject;
import com.onato.cruddemo.service.StudentService;
import com.onato.cruddemo.service.StudentServiceImpl;

import junit.framework.Assert;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class StudentServiceTest {

	@InjectMocks
	private StudentService studentService = new StudentServiceImpl();
	
	@Mock
	private StudentRepository studentRepository;
	
	private Student student = null;
	
	@Before
	public void initliase()
	{
		List<Subject> subjects = new ArrayList<>();
		subjects.add(new Subject(1, "maths", 90.1, null));
		subjects.add(new Subject(2, "science", 80.1, null));
		
		student = new Student(1, "deepak", "cse", subjects);
	}
	
	@Test
	public void test()
	{
		Student student = new  Student(1, "deepak", "cse", null);
		when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		Assert.assertEquals(studentService.findStudentByRollNo(1), student);
	}
	@Test
	public void findAllStudents()
	{
		List<Student> list = new ArrayList<>();
		list.add(new Student(1,"deepak", "cse", null));
		list.add(new Student(1,"bhargav", "mec", null));
		
		when(studentRepository.findAll()).thenReturn(list);
		Assert.assertEquals(list, studentService.findAllStudents());
	}

	@Test
	public void getSubjectsAndMarksByStudentId()
	{
		Map<String, Double> marksMap = new HashMap<>();
		marksMap.put("maths", 90.1);
		marksMap.put("science", 80.1);
		when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		Assert.assertEquals(marksMap, studentService.getSubjectsAndMarksByStudentId(1));
	}
	
	
	
}
