package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Privileges;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPrivilegesRepository extends PagingAndSortingRepository<Privileges, Long> {

    Privileges findByAccount_EmailAndDocument_Id(String email, Long documentID);

    List<Privileges> findAllByAccount_Email(String email);

    List<Privileges> findAllByDocument_Id(Long documentID);


}
