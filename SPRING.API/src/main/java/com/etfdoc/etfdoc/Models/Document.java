package com.etfdoc.etfdoc.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
    private  Account owner;
    private Boolean privateFlag = false;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Folder.class)
    private Folder folder;
    @DateTimeFormat
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date date;

    public Document() {}

    public Document(String name, Account owner, Boolean private_flag, Folder folder) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.folder = folder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Boolean getPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(Boolean private_flag) {
        this.privateFlag = private_flag;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}