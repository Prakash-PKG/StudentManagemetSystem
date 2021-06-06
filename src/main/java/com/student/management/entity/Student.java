package com.student.management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;
@Entity
@Table(name="student_manangemet")
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Integer stdId;
	@NotNull
	private String stdName;
	@NotNull
	private String stdEmailId;
	@NotNull
	private String stdFee;
	@NotNull
	private String stdCourse;
	
	
	public Integer getStdId() {
		return stdId;
	}
	public void setStdId(Integer stdId) {
		this.stdId = stdId;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	
	public String getStdEmailId() {
		return stdEmailId;
	}
	public void setStdEmailId(String stdEmailId) {
		this.stdEmailId = stdEmailId;
	}
	public String getStdFee() {
		return stdFee;
	}
	public void setStdFee(String stdFee) {
		this.stdFee = stdFee;
	}
	public String getStdCourse() {
		return stdCourse;
	}
	public void setStdCourse(String stdCourse) {
		this.stdCourse = stdCourse;
	}
	
	
}
