package com.student.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.student.management.entity.Message;
import com.student.management.entity.Student;
import com.student.management.entity.UserLogin;
import com.student.management.repo.StudentManagementRepo;
import com.student.management.service.StudentManagementService;

@Service
public class StudentManagementServiceImpl implements StudentManagementService{

	
	@Autowired
	private StudentManagementRepo repo;
	
	public Integer saveStudent(Student s) {
		return repo.save(s).getStdId();
	}
	public List<Student> getAllStudents() {
		return repo.findAll(); 
	}

	public Optional<Student> getOneStudent(Integer id) {
		return repo.findById(id); 
	}

	public boolean isExist(Integer id) {
		return repo.existsById(id); 
	}

	public void deleteStudent(Integer id) {
		repo.deleteById(id);  
		
	}
	public ResponseEntity<Message> userLogin(UserLogin userLogin) {
		ResponseEntity<Message> resp=null;
		Message msg=new Message("", "");
		String userName=userLogin.getUserEmail();
		String userPassword=userLogin.getUserPassword();
		UserLogin userDetails=repo.checkUserIdExitOrNot(userName,userPassword);
		if(userDetails!=null) {
			msg.setMessage("Valid User");
			msg.setType("200");
		}else {
			msg.setMessage("Invalid Valid User");
			msg.setType("500");
		}
		resp = new ResponseEntity<Message>(msg,HttpStatus.OK );
		return resp;
	}
}
