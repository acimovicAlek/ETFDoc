package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Folder;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.Repositories.IDocumentRepository;
import com.etfdoc.etfdoc.Repositories.IFolderRepository;
import com.etfdoc.etfdoc.ViewModels.DocumentVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private IDocumentRepository documentRepository;

    @Autowired
    private IFolderRepository folderRepository;

    @Autowired
    private IAccountRepository accountRepository;

    public Boolean createDocument(DocumentVM documentVM){

        Account account = accountRepository.getAccountByEmail(documentVM.getOwner());

        Folder parent = null;

        if(documentVM.getFolder() != -1) parent = folderRepository.getById(documentVM.getFolder());

        Document newDocument = new Document(
                documentVM.getName(),
                account,
                documentVM.getPrivate_flag(),
                parent
        );

        Document createdDocument = documentRepository.save(newDocument);

        return (createdDocument != null);

    }

    public void deleteDocument(Long documentID){

        documentRepository.delete(documentID);

    }

    public List<Document> getAllByOwnerAndRoot(String email){

        Account owner = accountRepository.getAccountByEmail(email);

        if(owner != null) return documentRepository.findAllByOwnerAndFolderIsNull(owner);

        return null;

    }

    public List<Document> getAllByOwnerAndFolder(String email, Long folderID){

        Account owner = accountRepository.getAccountByEmail(email);

        Folder folder = folderRepository.getById(folderID);

        if(owner != null && folder != null) return documentRepository.findAllByOwnerAndFolder(owner, folder);

        return null;

    }

    public List<Document> getAllRootAndPublic(){
        return documentRepository.findAllByFolderIsNullAndPrivateFlagIsFalse();
    }

}
