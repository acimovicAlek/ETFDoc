package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Folder;
import com.etfdoc.etfdoc.Models.Privileges;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.Repositories.IDocumentRepository;
import com.etfdoc.etfdoc.Repositories.IFolderRepository;
import com.etfdoc.etfdoc.Repositories.IPrivilegesRepository;
import com.etfdoc.etfdoc.ViewModels.DocumentVM;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DocumentService {

    @Autowired
    private IDocumentRepository documentRepository;

    @Autowired
    private IFolderRepository folderRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IPrivilegesRepository privilegesRepository;

    public Document createDocument(DocumentVM documentVM){

        Account account = accountRepository.getAccountByEmail(documentVM.getOwner());

        Folder parent = null;

        if(documentVM.getFolder() != -1) parent = folderRepository.getById(documentVM.getFolder());

        Document newDocument = new Document(
                documentVM.getName(),
                account,
                documentVM.getPrivateFlag(),
                parent
        );

        Document createdDocument = documentRepository.save(newDocument);

        return createdDocument;
    }

    public Boolean deleteDocument(Long documentID){

        documentRepository.delete(documentID);

        return (null != documentRepository.findById(documentID));

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

    public List<Document> getAllRoot(){
        return documentRepository.findAllByPrivateFlag(false);
    }

    public List<Document> getAllPrivate(String email){

        Account owner = accountRepository.getAccountByEmail(email);
        List<Document> result = documentRepository.findAllByOwner(owner);

        if(result != null) result.addAll(getByPrivileges(email));
        else result = getByPrivileges(email);

        return result;

    }

    public List<Document> getByPrivileges(String email){

        List<Privileges> privileges = privilegesRepository.findAllByAccount_Email(email);

        List<Document> result = new ArrayList<Document>();

        for (Privileges i:
             privileges) {

            result.add(i.getDocument());

        }

        return result;

    }

   /* public List<Document> findByKeywordAndColobarator(String keyword, String email){

        Account owner = accountRepository.getAccountByEmail(email);

        return documentRepository.findAllByKeywordAndCollaborator("%"+keyword+"%", owner.getId());

    }*/

}
