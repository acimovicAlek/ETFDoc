package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Account;

public class AccountVM {

    private Long id;
    private String email;
    private String password;
    private String oldPassword;
    private String firstName;
    private String lastName;
    private RoleVM role;

    public AccountVM(){}

    public AccountVM(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public AccountVM(Long id, String email, String password, String firstName, String lastName, RoleVM role){
        this.id = id;
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
        this.role = new RoleVM(account.getRole());
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

    public RoleVM getRole() { return role; }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
