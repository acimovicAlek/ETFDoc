package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Folder;

public class FolderVM {

    private Long id;
    private Long parentFolder;
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
