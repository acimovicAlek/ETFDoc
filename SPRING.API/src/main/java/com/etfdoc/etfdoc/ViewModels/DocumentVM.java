package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Document;

import java.util.Date;

public class DocumentVM {

    private Long id;
    private String name;
    private AccountVM owner;
    private Boolean privateFlag = false;
    private FolderVM folder;
    private Date date;

    public DocumentVM(){}

    public DocumentVM(Long id, String name, AccountVM owner, Boolean private_flag, FolderVM folder, Date date) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.folder = folder;
        this.date = date;
    }

    public DocumentVM(String name, AccountVM owner, Boolean private_flag, FolderVM folder, Date date) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.folder = folder;
        this.date = date;
    }

    public DocumentVM(Document document) {
        this.id = document.getId();
        this.name = document.getName();
        this.owner = new AccountVM(document.getOwner());
        this.privateFlag = document.getPrivateFlag();
        this.folder = new FolderVM(document.getFolder());
        this.date = document.getDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AccountVM getOwner() {
        return owner;
    }

    public Boolean getPrivate_flag() {
        return privateFlag;
    }

    public FolderVM getFolder() {
        return folder;
    }

    public Date getDate() {
        return date;
    }
}
