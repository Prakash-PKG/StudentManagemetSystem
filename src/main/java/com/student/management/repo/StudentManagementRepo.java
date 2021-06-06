package com.student.management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.student.management.entity.Message;
import com.student.management.entity.Student;
import com.student.management.entity.UserLogin;


public interface StudentManagementRepo  extends JpaRepository<Student,Integer>{

	

	

	@Query(value="from com.student.management.entity.UserLogin  v where v.userEmail=:username and v.userPassword=:userpassword ")
	public UserLogin checkUserIdExitOrNot(String username, String userpassword);




}
