package com.onato.cruddemo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onato.cruddemo.dao.StudentRepository;
import com.onato.cruddemo.entity.Student;
import com.onato.cruddemo.entity.Subject;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student findStudentByRollNo(Integer theId) {
		
		Optional<Student> optional = studentRepository.findById(theId);
		if(optional.isPresent())
			return optional.get();
		
		throw new RuntimeException("Student not found for the id : " + theId);
	}

	@Override
	public int save(Student student) {
		studentRepository.save(student);
		System.out.println(student);
		return student.getRollNo();
		
	}

	@Override
	public int deleteStudent(Integer rollNo) {
		Student student = findStudentByRollNo(rollNo);
		studentRepository.delete(student);
		return student.getRollNo();
	}

	@Override
	public Map<String, Double> getSubjectsAndMarksByStudentId(Integer id) {
		
		Student student = findStudentByRollNo(id);
		Map<String, Double> subjectToMarksMap = new HashMap<String, Double>();
		for (Subject sub : student.getSubjects()) {
			subjectToMarksMap.put(sub.getSubjectName(), sub.getMarks());
		}
		return subjectToMarksMap;
	}

	@Override
	public Map<String, String> getStudentsByAggregatePercentage(Double percentage) {
		
		Map<String, String> studentToAggregateMap = new HashMap<String, String>();
		List<Object[]> list = studentRepository.getStudentsByAggregate(percentage);
		for (Object[] objects : list) {
			studentToAggregateMap.put((String)objects[0],(Double)objects[1] + "%");
		}
		return studentToAggregateMap;
	}

	@Override
	public List<String> getTopScorers() {
		
		List<String> topScorersList = new ArrayList<>();
		List<Object[]> list = studentRepository.getTopScorers();
		
		for (Object[] students : list) {
			topScorersList.add("Student : " + students[0] + " Subject : " + students[1] + " marks : " + students[2]);
		}
		return topScorersList;
	}

	@Override
	public void deleteAll() {
		studentRepository.deleteAll();
	}

	
}






