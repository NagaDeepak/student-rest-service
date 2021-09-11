package com.onato.cruddemo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onato.cruddemo.entity.Student;
import com.onato.cruddemo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/count")
	public long getCountOfStudents() {
		return studentService.getCount();
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Integer id)
	{
		return studentService.findStudentByRollNo(id);
	}

	@GetMapping("")
	public List<Student> getAllStudends()
	{
		return studentService.findAllStudents();
	}

	@GetMapping("{id}/subjects")
	public Map<String, Double> getSubjectsForStudent(@PathVariable Integer id)
	{
		return studentService.getSubjectsAndMarksByStudentId(id);
	}
	
	@GetMapping("/aggregate/{percentage}")
	public Map<String, String> getStudentsByAggreagatePercentage(@PathVariable Double percentage)
	{
		return studentService.getStudentsByAggregatePercentage(percentage);
	}

	@GetMapping("/topScorers")
	public List<String> getTopScorerOfEachSubject()
	{
		return studentService.getTopScorers();
	}
	
	@PostMapping("")
	public String saveStudents(@RequestBody List<Student> students)
	{
		int count = studentService.saveAll(students);
		return "Successfully saved " + count + " students";
	}

	@PutMapping("")
	public Student updateStudent(@RequestBody Student student)
	{
		return studentService.saveOrUpdateStudent(student);
	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Integer id)
	{
		int sid = studentService.deleteStudent(id);
		return "Successfully deleted student for id : " + sid;
	}

	@DeleteMapping("")
	public String deleteAllStudents()
	{
		studentService.deleteAll();
		return "Successfully deleted all students";
	}

}










