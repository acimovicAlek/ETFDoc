package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.ROLE;

import javax.management.relation.Role;

public class AccountVM {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private ROLE role;

    public AccountVM(Long id, String email, String password, String firstName, String lastName, ROLE role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public AccountVM(String email, String password, String firstName, String lastName, ROLE role){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public AccountVM(Account account){
        this.id = account.getId();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.role = account.getRole();
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public ROLE getRole() { return role; }

}
