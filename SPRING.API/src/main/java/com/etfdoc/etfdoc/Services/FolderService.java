package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Folder;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.Repositories.IFolderRepository;
import com.etfdoc.etfdoc.ViewModels.FolderVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IFolderRepository folderRepository;

    public Boolean createFolder(FolderVM folderVM){

        Account account = accountRepository.getAccountByEmail(folderVM.getOwner());

        Folder parent = null;

        if(folderVM.getParentFolder() != -1) parent = folderRepository.getById(folderVM.getParentFolder());

        Folder newFolder = new Folder(folderVM.getName(), account,  folderVM.getPrivateFlag(), parent);
        Folder created = folderRepository.save(newFolder);

        return (created != null);
    }

    public void deleteFolder(Long id){

        folderRepository.delete(id);

    }

    public List<Folder> getFoldersByParent(Long parentID){

        Folder parent = folderRepository.getById(parentID);

        if(parent != null){
            return folderRepository.getAllByParentFolder(parent);
        }

        return  null;

    }

    public List<Folder> getFolderByOwner(String ownerEmail){

        Account owner = accountRepository.getAccountByEmail(ownerEmail);

        if(owner != null) return folderRepository.getAllByOwner(owner);

        return  null;
    }

    public List<Folder> getAllRootPublic(){

        return folderRepository.findAllByPrivateFlagIsFalseAndParentFolderIsNull();

    }

    public List<Folder> getAllByOwnerAndRoot(String ownerEmail){

        Account owner = accountRepository.getAccountByEmail(ownerEmail);

        if(owner != null) return folderRepository.findAllByOwnerAndParentFolderIsNull(owner);

        return null;

    }

}
