package com.x.entity;

/**
 * 面试实体类
 */
public class Interview {

	private Integer id;
	private String name;
	private String sex;
	private String idcard; //身份证号
	private String tellphone; //联系方式
	private String recruitFrom; //招聘来源
	private String interviewer; //面试官
	private Integer isOk; //是否合格（0待定，1合格，-1不合格）
	private String missReason; //错失原因
	
	public Interview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTellphone() {
		return tellphone;
	}
	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}
	public String getRecruitFrom() {
		return recruitFrom;
	}
	public void setRecruitFrom(String recruitFrom) {
		this.recruitFrom = recruitFrom;
	}
	public String getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	public Integer getIsOk() {
		return isOk;
	}
	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}
	public String getMissReason() {
		return missReason;
	}
	public void setMissReason(String missReason) {
		this.missReason = missReason;
	}
	
}
