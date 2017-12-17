package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Role;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.Repositories.IRoleRepository;
import com.etfdoc.etfdoc.ViewModels.AccountVM;
import com.etfdoc.etfdoc.ViewModels.RoleVM;
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

    public Account getAccountById(Long id){

        Account account = accountRepository.getAccountById(id);
        return  account;

    }

    public Account getAccountByEmail(String email){

        Account account = accountRepository.getAccountByEmail(email);
        return  account;

    }

    public Account createAccount(AccountVM accountVM, String role) throws NoSuchAlgorithmException {

        if(accountRepository.getAccountByEmail(accountVM.getEmail()) != null){
            throw new ServiceException("Account with the entered email already exists!");
        }else if(accountVM == null  || !isValidEmailAddress(accountVM.getEmail()) ) {
            throw new SecurityException("Mail is not valid or accountVM is null");
        }else{

            /* Generating password
            String authPassword = accountVM.getPassword() + accountVM.getEmail() + "probamosecurity";

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(authPassword.getBytes());
            byte[] digest = md.digest();
            String encodedPassword = DatatypeConverter.printHexBinary(digest).toUpperCase();
            */

            Account newAccount = new Account(
                    accountVM.getEmail(),
                    accountVM.getPassword(),
                    accountVM.getFirstName(),
                    accountVM.getLastName(),
                    roleRepository.findByName(role));
            return accountRepository.save(newAccount);
        }

    }


    public RoleVM getRolaForUser(String email) {
        Account account = accountRepository.getAccountByEmail(email);
        return new RoleVM(account.getRole().getName());
    }


    public Boolean addRole(RoleVM role){

        Role newRola = new Role(role.getName());
        Role created = roleRepository.save(newRola);

        return (created != null);

    }

    public RoleVM getRole(Long id) {

        RoleVM role = new RoleVM(roleRepository.findOne(id).getName());

        if(role != null)
            return  role;
        return  null;
    }


    public boolean isValidEmailAddress(String email) {
        String ePattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public Boolean update(AccountVM userVM, String email){
        Account user = accountRepository.getAccountByEmail(email);

        if(user.getPassword().equals(userVM.getOldPassword())) {
            user.setEmail(userVM.getEmail());
            user.setEmail(userVM.getEmail());
            if(!userVM.getPassword().equals("")) {
                user.setPassword(userVM.getPassword());
            }
            Account createdUser = accountRepository.save(user);
            return createdUser != null;
        }
        return false;

    }

}
