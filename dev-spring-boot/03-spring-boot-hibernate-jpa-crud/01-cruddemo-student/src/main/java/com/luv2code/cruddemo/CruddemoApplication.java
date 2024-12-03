package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			createMultipleStudents(studentDAO);
//			getStudentById(studentDAO);
//			studentQuery(studentDAO);
//			queryForStudentBylastName(studentDAO);
//			updateStudent(studentDAO);
//			delete(studentDAO);
//			deleteAll(studentDAO);
		};
	}

	private void deleteAll(StudentDAO studentDAO) {
		System.out.println(studentDAO.deleteAll());
	}

	private void delete(StudentDAO studentDAO) {
		int studentId =2;
		studentDAO.delete(studentId);
		System.out.println("Deleting a Student with id "+studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int id =1;
		System.out.println("Getting Student with ID: "+id);
		Student student =studentDAO.findById(1);

		System.out.println("Change First name with 'XYZ'");
		student.setLastName("XYZ");

		// update the student
		studentDAO.update(student);

		System.out.println("Updated Student "+student);

	}

	private void queryForStudentBylastName(StudentDAO studentDAO) {
		// Get a list of students
		List<Student> theStudents = studentDAO.findByLastName("raja");

		// display
		for(Student student: theStudents){
			System.out.println(student);
		}
	}

	private void createStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new Student Object...");
		Student tempStudent1 = new Student("paul","doel","poel.deo@luv2code.com");

		// save the student object
		studentDAO.save(tempStudent1);

		//display the saved student
		System.out.println("Saved Student ID: "+tempStudent1.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new Student Object...");
		Student tempStudent1 = new Student("paul","doel","poel.deo@luv2code.com");
		Student tempStudent2 = new Student("paul","doel","poel.deo@luv2code.com");
		Student tempStudent3 = new Student("paul","doel","poel.deo@luv2code.com");


		// save the student object
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		//display the saved student
		System.out.println("Saved Student ID: "+tempStudent1.getId());
		System.out.println("Saved Student ID: "+tempStudent2.getId());
		System.out.println("Saved Student ID: "+tempStudent3.getId());
	}

	private void getStudentById(StudentDAO studentDAO){

		//create  student
		System.out.println("Creating a new Student");
		Student tempStudent = new Student("AB","D","äbd@luv2com");

		//save the student
		System.out.println("Saving the Student");
		studentDAO.save(tempStudent);

		// display id of student
		System.out.println(tempStudent.getId());

		//retrieve the student based on id : primary key
		Student student = studentDAO.findById(tempStudent.getId());

		//display retrieved student
		System.out.println("Found the Student "+student);
	}

	private void studentQuery(StudentDAO studentDao){
		List<Student> student = studentDao.queryStudent();
		System.out.println("List of Students ..Total in"+student.size());
		for(Student e : student){
			System.out.println(e);
		}
	}
}
