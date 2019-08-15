package com.x.entity;

/**
 * 招聘实体类
 */
public class Recruit {

	private Integer id;
	private String name;
	private String sex;
	private String idcard;
	private String tellphone;
	private String recruitFrom;
	private Integer healthyState; //健康状态 0不合格 1合格
	private Integer idcardState; // 身份证核验 0不合格 1合格
	
	public Recruit() {
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
	public Integer getHealthyState() {
		return healthyState;
	}
	public void setHealthyState(Integer healthyState) {
		this.healthyState = healthyState;
	}
	public Integer getIdcardState() {
		return idcardState;
	}
	public void setIdcardState(Integer idcardState) {
		this.idcardState = idcardState;
	}
	
}
