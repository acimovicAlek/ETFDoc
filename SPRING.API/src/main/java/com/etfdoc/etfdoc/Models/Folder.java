package com.etfdoc.etfdoc.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Folder implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
    private Account owner;
    private Boolean privateFlag = false;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Folder.class)
    private Folder parentFolder;

    public Folder() {}

    public Folder(String name, Account owner, Boolean private_flag, Folder parentFolder) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.parentFolder = parentFolder;
    }

    public Folder(String name, Account owner, Boolean private_flag) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
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

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parent_folder) {
        this.parentFolder = parent_folder;
    }

}
