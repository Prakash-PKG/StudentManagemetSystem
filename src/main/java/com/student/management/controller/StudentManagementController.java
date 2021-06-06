package com.student.management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.management.entity.Message;
import com.student.management.entity.Student;
import com.student.management.entity.UserLogin;
import com.student.management.service.StudentManagementService;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/student")
public class StudentManagementController {

	@Autowired  
	private StudentManagementService service; 

	
	@PostMapping("/userLogin")
	public ResponseEntity<Message> userLogin(@RequestBody UserLogin userLogin){
		ResponseEntity<Message> resultStatus=null;
		resultStatus=service.userLogin(userLogin);
		
		return resultStatus;
	}
	@PostMapping("/save") 
	public ResponseEntity<String> saveStudent(@RequestBody Student student) { 
		ResponseEntity<String> resp=null; 
		try {  
			Integer id=service.saveStudent(student);  
			System.out.println("Id :"+id);
			resp = new ResponseEntity<String>(
					"Student '"+id+"' created Successfully!",
					HttpStatus.OK );
		} catch (Exception e) { 
			resp = new ResponseEntity<String>(
					"Unable to Save Product",
					HttpStatus.INTERNAL_SERVER_ERROR
					);
			e.printStackTrace();  
		}   return resp; 

	}
	@GetMapping("/all")  public ResponseEntity<?> getAllStudents(){ 
		ResponseEntity<?> resp=null;   try { 
			List<Student> list=service.getAllStudents();   
			if(list!=null && !list.isEmpty())    
				resp=new ResponseEntity<List<Student>>(list,HttpStatus.OK);  
			else     resp=new ResponseEntity<String>("No Data Found",HttpStatus.OK);   
		} catch (Exception e) { 
			resp=new ResponseEntity<String>("Unable to fetch Data",HttpStatus.INTERNAL_SERVER_ERROR); 
			e.printStackTrace();   
		}    
		return resp; 
	}

	@GetMapping("/one/{id}")  public ResponseEntity<?> getOneStudent(@PathVariable Integer id)  { 
		ResponseEntity<?> resp=null;  
		try {  
			Optional<Student> opt=service.getOneStudent(id);  
			if(opt.isPresent())    
				resp=new ResponseEntity<Student>(opt.get(),HttpStatus.OK);  
			else    
				resp=new ResponseEntity<String>("No Data Found",HttpStatus.BAD_REQUEST);  
		} catch (Exception e) {    
			resp=new ResponseEntity<String>("Unable to Fetch Data",HttpStatus.INTERNAL_SERVER_ERROR);    
			e.printStackTrace();  
		}  
		return resp;  
	}
	@DeleteMapping("/remove/{id}") 
	public ResponseEntity<Message> deleteStudent( 
			@PathVariable Integer id)  { 
		System.out.println("welcome");   
		ResponseEntity<Message> resp=null;  
		try {   
			boolean exist=service.isExist(id);   
			if(exist) {   
				service.deleteStudent(id);   
				resp=new ResponseEntity<Message>(new Message("SUCCESSS",id+"-removed"),HttpStatus.OK); 
			}else { 
				resp=new ResponseEntity<Message>(new Message("FAIL",id+"-Not Exist"),HttpStatus.BAD_REQUEST);  
			}   
		} catch (Exception e) { 
			resp=new ResponseEntity<Message>(new Message("FAIL","Unable to Delete"),HttpStatus.INTERNAL_SERVER_ERROR);    
			e.printStackTrace();  
		}    
		return resp; 
	}   
	@PutMapping("/update")  
	public ResponseEntity<Message> updateStudent( 
			@RequestBody Student student) { 
		ResponseEntity<Message> resp=null;  
		try {   
			boolean exist=service.isExist(student.getStdId()); 
			if(exist) {     
				service.saveStudent(student);  
				resp=new ResponseEntity<Message>(new Message("OK",student.getStdId()+"-Updated"),HttpStatus.OK);
			}else { 
				resp=new ResponseEntity<Message>(new Message("OK",student.getStdId()+"-Not Exist"),HttpStatus.BAD_REQUEST);   
			}  
		} catch (Exception e) {  
			resp=new ResponseEntity<Message>(new Message("OK","Unable to Update"),HttpStatus.INTERNAL_SERVER_ERROR);  
			e.printStackTrace();   
		}   return resp; 
	}
}
