package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.Repositories.IRoleRepository;
import com.etfdoc.etfdoc.ViewModels.AccountVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IRoleRepository roleRepository;

    public AccountVM getAccountById(Long id){

        Account account = accountRepository.getAccountById(id);
        return  new AccountVM(account);

    }

    public AccountVM getAccountByEmail(String email){

        Account account = accountRepository.getAccountByEmail(email);
        return  new AccountVM(account);

    }

    public Boolean createAccount(AccountVM accountVM, String role) throws NoSuchAlgorithmException {

        if(accountRepository.getAccountByEmail(accountVM.getEmail()) != null){
            throw new ServiceException("Account with the entered email already exists!");
        }else if(accountVM == null  || isValidEmailAddress(accountVM.getEmail()) ) {
            return  false;
        }else{

            /* Generating password */
            String authPassword = accountVM.getPassword() + accountVM.getEmail() + "probamosecurity";

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(authPassword.getBytes());
            byte[] digest = md.digest();
            String encodedPassword = DatatypeConverter.printHexBinary(digest).toUpperCase();

            Account newAccount = new Account(
                    accountVM.getEmail(),
                    encodedPassword,
                    accountVM.getFirstName(),
                    accountVM.getLastName(),
                    roleRepository.findByName(role));
            Account createdAccount = accountRepository.save(newAccount);
            return (createdAccount != null);
        }

    }



    public boolean isValidEmailAddress(String email) {
        String ePattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}
