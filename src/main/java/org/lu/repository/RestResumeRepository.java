package org.lu.repository;

import org.lu.entity.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RestResumeRepository extends
		PagingAndSortingRepository<Resume, Long> {

	@Query("select r from Resume r where r.resumeName like %:resumeName%")
	public Page<Resume> findByResumeName(
			@Param("resumeName") String resumeName, Pageable pageable);
}
