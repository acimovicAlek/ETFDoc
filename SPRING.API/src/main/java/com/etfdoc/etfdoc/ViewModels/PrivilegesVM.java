package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Privileges;

public class PrivilegesVM {

    private Long id;
    private AccountVM account;
    private DocumentVM document;
    private Boolean read = false;
    private Boolean write = false;
    private Boolean update = false;

    public PrivilegesVM(Long id, AccountVM account, DocumentVM document, Boolean read, Boolean write, Boolean update) {
        this.id = id;
        this.account = account;
        this.document = document;
        this.read = read;
        this.write = write;
        this.update = update;
    }

    public PrivilegesVM(AccountVM account, DocumentVM document, Boolean read, Boolean write, Boolean update) {
        this.account = account;
        this.document = document;
        this.read = read;
        this.write = write;
        this.update = update;
    }

    public PrivilegesVM(Privileges privileges) {
        this.id = privileges.getId();
        this.account = new AccountVM(privileges.getAccount());
        this.document = new DocumentVM(privileges.getDocument());
        this.read = privileges.getRead();
        this.write = privileges.getWrite();
        this.update = privileges.getUpdate();
    }

    public Long getId() {
        return id;
    }

    public AccountVM getAccount() {
        return account;
    }

    public DocumentVM getDocument() {
        return document;
    }

    public Boolean getRead() {
        return read;
    }

    public Boolean getWrite() {
        return write;
    }

    public Boolean getUpdate() {
        return update;
    }
}
