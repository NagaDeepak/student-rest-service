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

	
	private static final int batchSize = 1000;
	
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

		//handled by global exceptional handler in com.onato.cruddemo.globalexceptions.GlobalExceptionHandler
		throw new RuntimeException("Student not found for the id : " + theId);
	}

	@Override
	public int saveAll(List<Student> students) {
		//Avoid overhead on db incase of more students and save the students based on the batch size.
		int size = students.size();
		int fromIndex = 0;
		int toIndex = fromIndex+batchSize;

		while(toIndex < size)
		{
			studentRepository.saveAll(students.subList(fromIndex, toIndex));
			fromIndex = toIndex;
			toIndex = fromIndex + batchSize;
		}
		if(fromIndex < size)
			studentRepository.saveAll(students.subList(fromIndex, size));
		return students.size();
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

		for (Object[] student : list) {
			topScorersList.add("Student id : " + student[0] + ", Student name : " 
		+ student[1] + ", Subject : " + student[2] + ", Marks : " + student[3]);
			
		}
		return topScorersList;
	}

	@Override
	public void deleteAll() {
		studentRepository.deleteAll();
	}

	@Override
	public Student saveOrUpdateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public long getCount() {
		return studentRepository.count();
	}

}






