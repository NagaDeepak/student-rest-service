package com.onato.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onato.cruddemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("Select st.name, avg(sub.marks) from Student st join st.subjects sub group by st.name having avg(sub.marks) > :avg")
	List<Object[]> getStudentsByAggregate(@Param("avg") Double percentage);
	@Query("Select st.name, sub.subjectName, max(sub.marks) from Student st join st.subjects sub group by sub.subjectName, st.name")
	List<Object[]> getTopScorers();
}
