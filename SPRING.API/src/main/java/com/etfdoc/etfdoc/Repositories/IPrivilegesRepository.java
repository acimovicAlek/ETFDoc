package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Privileges;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IPrivilegesRepository extends PagingAndSortingRepository<Privileges, Long> {

    Privileges findByAccountAndDocument(Account account, Document document);

    List<Privileges> findAllByAccount(Account account);

    List<Privileges> findAllByDocument(Account document);

}
