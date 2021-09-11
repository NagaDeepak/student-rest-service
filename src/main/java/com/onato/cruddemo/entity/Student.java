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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name="Student")
public class Student {

	public Student() {
	}

	@Id
	@Column(name="roll_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rollNo;

	@Column(name = "name")
	private String name;
	
	@Column(name = "class_name")
	private String className;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "student_subject_map",
				joinColumns = @JoinColumn(name="roll_no"),
				inverseJoinColumns = @JoinColumn(name = "subject_id"))
	
	private List<Subject> subjects;

	public Student(int rollNo, String name, String className, List<Subject> subjects) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.className = className;
		this.subjects = subjects;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", className=" + className + ", subjects=" + subjects
				+ "]";
	}
	
	
	

}











