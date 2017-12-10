package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Document;

import java.util.Date;

public class DocumentVM {

    private Long id;
    private String name;

    public Boolean getPrivateFlag() {
        return privateFlag;
    }

    private String owner;

    public void setPrivateFlag(Boolean privateFlag) {
        this.privateFlag = privateFlag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setFolder(Long folder) {
        this.folder = folder;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Boolean privateFlag = false;
    private Long folder;
    private Date date;

    public DocumentVM(){}

    public DocumentVM(String name, String owner, Boolean privateFlag, Long folder) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = privateFlag;
        this.folder = folder;
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

    public Long getFolder() {
        return folder;
    }

    public Date getDate() {
        return date;
    }
}
