package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Folder;

public class FolderVM {

    private Long id;
    private Long parentFolder;

    public void setId(Long id) {
        this.id = id;
    }

    public void setParentFolder(Long parentFolder) {
        this.parentFolder = parentFolder;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrivateFlag(Boolean privateFlag) {
        this.privateFlag = privateFlag;
    }

    private String owner;
    private String name;
    private Boolean privateFlag = false;

    public FolderVM(){}

    public FolderVM(Long id, String name, String owner, Boolean private_flag, Long parent_folder) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.parentFolder = parent_folder;
    }

    public FolderVM(String name, String owner, Boolean private_flag, Long parent_folder) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.parentFolder = parent_folder;
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

    public Boolean getPrivateFlag() {
        return privateFlag;
    }

    public Long getParentFolder() {
        return parentFolder;
    }
}
