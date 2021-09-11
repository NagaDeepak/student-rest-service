package com.onato.cruddemo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "subject")
public class Subject {

	public Subject() {
	}
	
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", marks=" + marks + ", students="
				+ students + "]";
	}

	@Id
	@Column(name = "subject_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subjectId;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "marks")
	private double marks;
	
	@ManyToMany(fetch = FetchType.LAZY
	)
	@JoinTable(name = "student_subject_map",
	joinColumns = @JoinColumn(name = "subject_id"),
	inverseJoinColumns =  @JoinColumn(name = "roll_no"))
	@JsonIgnore
	private List<Student> students;
	

	public Subject(int subjectId, String subjectName, double marks, List<Student> students) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.marks = marks;
		this.students = students;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	
	
	
}
