package org.lu.entity;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SortNatural;

@Entity
@Table(name = "RESUMES")
public class Resume extends AbstractEntity {

	@Column(unique = true, nullable = false)
	@NotNull
	@Size(min = 3,message="Minimum length is 3")
	private String resumeName;

	@OneToOne(mappedBy = "resume", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private BasicInfo basicInfo;

	@OneToMany(mappedBy = "resume", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@SortNatural
	private SortedSet<Education> educations = new TreeSet<>();

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public BasicInfo getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	// @JsonIgnore
	public SortedSet<Education> getEducations() {
		return educations;
	}

	public void setEducations(SortedSet<Education> educations) {
		this.educations = educations;
	}
}
