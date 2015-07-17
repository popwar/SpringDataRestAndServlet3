package org.lu.repository;

import org.lu.entity.Education;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RestEducationRepository extends
		PagingAndSortingRepository<Education, Long> {

}
