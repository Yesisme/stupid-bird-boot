package com.epsoft.demo.bean.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "sex")
	private String sex;

	@Column(name = "brith")
	private Date brith;

	@Column(name = "relationid")
	private Integer relationId;

	@Column(name = "createtime")
	private Date createTime;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getBrith() {
		return brith;
	}

	public void setBrith(Date brith) {
		this.brith = brith;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	//配置显示格式和时间
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", age=" + age + ", sex=" + sex + ", brith=" + brith
				+ ", relationId=" + relationId + ", createTime=" + createTime + ", email=" + email + ", phone=" + phone
				+ "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userName, age, sex, brith, relationId, createTime, email, phone);
	}

	public static void main(String[] args) {
		User user1 = new User();
		user1.setId(1L);
		System.out.println("user1 hashcode"+user1.hashCode());
		User user2 = new User();
		user1.setId(2L);
		System.out.println("user2 hashcode"+user2.hashCode());
		System.out.println(user1==user2);
		System.out.println("user1.equals(user2)"+user1.equals(user2));
	}
}
