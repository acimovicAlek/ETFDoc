package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAccountRepository extends PagingAndSortingRepository<Account, Long> {

    Account getAccountById(Long id);

    Account getAccountByEmail(String email);

    @Query("SELECT count(u) > 0 FROM Account u WHERE u.email = ?1")
    boolean checkMail(String email);

    @Query("SELECT u FROM Account u WHERE u.email = ?1 AND u.password = ?2")
    Page<Account> findByMailAndPass(String email, String password, Pageable pageable);


}
