package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.ViewModels.AccountVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;

    public AccountVM getAccountById(Long id){

        Account account = accountRepository.getAccountById(id);
        return  new AccountVM(account);

    }

    public AccountVM getAccountByEmail(String email){

        Account account = accountRepository.getAccountByEmail(email);
        return  new AccountVM(account);

    }

}
