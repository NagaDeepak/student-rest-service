package com.onato.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onato.cruddemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("Select st.name, avg(sub.marks) from Student st join st.subjects sub group by st.name having avg(sub.marks) >= :avg")
	List<Object[]> getStudentsByAggregate(@Param("avg") Double percentage);
	
	@Query(value = "select s.roll_no, s.name, a.subject_name, b.marks from ( select subject_name, max(marks) as high_marks from subject group by subject_name) a join subject b on\n"
			+ "a.subject_name = b.subject_name  and a.high_marks = b.marks join student_subject_map sbmap on sbmap.subject_id = b.subject_id join student s on s.roll_no = sbmap.roll_no"
			, nativeQuery = true)
	List<Object[]> getTopScorers();
}
