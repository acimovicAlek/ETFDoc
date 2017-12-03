package com.etfdoc.etfdoc.ViewModels;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Folder;

public class FolderVM {

    private Long id;
    private String name;
    private AccountVM owner;
    private Boolean privateFlag = false;
    private FolderVM parentFolder;

    public FolderVM(){}

    public FolderVM(Long id, String name, AccountVM owner, Boolean private_flag, FolderVM parent_folder) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.parentFolder = parent_folder;
    }

    public FolderVM(String name, AccountVM owner, Boolean private_flag, FolderVM parent_folder) {
        this.name = name;
        this.owner = owner;
        this.privateFlag = private_flag;
        this.parentFolder = parent_folder;
    }

    public FolderVM(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
        this.owner = new AccountVM(folder.getOwner());
        this.privateFlag = folder.getPrivateFlag();
        this.parentFolder = new FolderVM(folder.getParentFolder());
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

    public Boolean getPrivateFlag() {
        return privateFlag;
    }

    public FolderVM getParentFolder() {
        return parentFolder;
    }
}
