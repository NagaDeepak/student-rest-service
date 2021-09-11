drop table if exists student_subject_map;
drop table if exists subject;
drop table if exists student;

create table student(

	roll_no int not null auto_increment,
	name varchar(255),
	class_name varchar(255),
	primary key (roll_no)
	
);


create table subject(

	subject_id int not null auto_increment,
	subject_name varchar(255),
	marks double,
	primary key (subject_id)

);


create table student_subject_map(

	roll_no int,
	subject_id int
	
	

);

alter table student_subject_map add  foreign key (roll_no) references student(roll_no);
alter table student_subject_map add  foreign key (subject_id) references subject(subject_id);


