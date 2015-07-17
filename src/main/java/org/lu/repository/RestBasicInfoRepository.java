package org.lu.repository;

import org.lu.entity.BasicInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RestBasicInfoRepository extends
		PagingAndSortingRepository<BasicInfo, Long> {

}
