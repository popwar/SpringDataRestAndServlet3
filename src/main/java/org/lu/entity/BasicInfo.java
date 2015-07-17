package org.lu.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.lu.utils.Gender;

@Entity
@Table(name = "BASIC_INFO")
public class BasicInfo extends AbstractEntity {

	private String name;

	private Gender gender;

	private int age;
	
	@OneToOne
	@JoinColumn(name = "RESUME_ID")
	private Resume resume;

	@JsonIgnore
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
