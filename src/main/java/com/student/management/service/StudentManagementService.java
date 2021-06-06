package com.student.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.student.management.entity.Message;
import com.student.management.entity.Student;
import com.student.management.entity.UserLogin;

public interface StudentManagementService {

	Integer saveStudent(Student student);
	public List<Student> getAllStudents(); 
	public Optional<Student> getOneStudent(Integer id); 
	public boolean isExist(Integer id); 
	public void deleteStudent(Integer id);
	ResponseEntity<Message> userLogin(UserLogin userLogin);

}
