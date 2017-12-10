package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Document;

import java.util.Date;

public class DocumentVM {

    private Long id;
    private String name;
    private String owner;
    private Boolean privateFlag = false;
    private Long folder;
    private Date date;

    public DocumentVM(){}

    public DocumentVM(Long id, String name, String owner, Boolean private_flag, Long folder, Date date) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.folder = folder;
        this.date = date;
    }

    public DocumentVM(String name, String owner, Boolean private_flag, Long folder, Date date) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.folder = folder;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Boolean getPrivate_flag() {
        return privateFlag;
    }

    public Long getFolder() {
        return folder;
    }

    public Date getDate() {
        return date;
    }
}
