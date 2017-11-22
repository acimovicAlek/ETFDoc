package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoleRepository extends PagingAndSortingRepository<Role,Long> {

    Role findByName(String name);

}
