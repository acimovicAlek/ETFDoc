package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Privileges;

public class PrivilegesVM {

    private Long id;
    private String account;
    private Long document;
    private Boolean read = false;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    private Boolean write = false;
    private Boolean update = false;

    public PrivilegesVM(){}
    public PrivilegesVM(Long id, String account, Long document, Boolean read, Boolean write, Boolean update) {
        this.id = id;
        this.account = account;
        this.document = document;
        this.read = read;
        this.write = write;
        this.update = update;
    }

    public PrivilegesVM(String account, Long document, Boolean read, Boolean write, Boolean update) {
        this.account = account;
        this.document = document;
        this.read = read;
        this.write = write;
        this.update = update;
    }


    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public Long getDocument() {
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
