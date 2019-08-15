package com.x.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
	
	private String empId;
	private String empNo;
	private String empName;
	private String empSex;
	private String empPicture;
	private String empIdcard;
	private String empNation;
	private String empZzmm;
	private String empRecord;
	private String school;
	private String major;
	private Integer deptId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date hiredate;
	private String empSource;
	private String empForm;
	private String empDesc;
	private Integer isLeave;
	
	private String deptName; //所属部门名称（多表查询）
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpPicture() {
		return empPicture;
	}
	public void setEmpPicture(String empPicture) {
		this.empPicture = empPicture;
	}
	public String getEmpIdcard() {
		return empIdcard;
	}
	public void setEmpIdcard(String empIdcard) {
		this.empIdcard = empIdcard;
	}
	public String getEmpNation() {
		return empNation;
	}
	public void setEmpNation(String empNation) {
		this.empNation = empNation;
	}
	public String getEmpZzmm() {
		return empZzmm;
	}
	public void setEmpZzmm(String empZzmm) {
		this.empZzmm = empZzmm;
	}
	public String getEmpRecord() {
		return empRecord;
	}
	public void setEmpRecord(String empRecord) {
		this.empRecord = empRecord;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getEmpSource() {
		return empSource;
	}
	public void setEmpSource(String empSource) {
		this.empSource = empSource;
	}
	public String getEmpForm() {
		return empForm;
	}
	public void setEmpForm(String empForm) {
		this.empForm = empForm;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public Integer getIsLeave() {
		return isLeave;
	}
	public void setIsLeave(Integer isLeave) {
		this.isLeave = isLeave;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
