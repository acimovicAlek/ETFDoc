package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Privileges;
import com.etfdoc.etfdoc.Repositories.*;
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
    private IAccountRepository accountRepository;

    @Autowired
    private IPrivilegesRepository privilegesRepository;

    @Autowired
    private IDocumentBlobRepository documentBlobRepository;

    public Document createDocument(DocumentVM documentVM){

        Account account = accountRepository.getAccountByEmail(documentVM.getOwner());

        Document newDocument = new Document(
                documentVM.getName(),
                account,
                documentVM.getPrivateFlag(),
                documentVM.getNative_flag()
        );

        Document createdDocument = documentRepository.save(newDocument);

        return createdDocument;
    }

    public Boolean deleteDocument(Long documentID){

        Document document = documentRepository.findById(documentID);
        if(!document.getNativeFlag()){
            documentBlobRepository.deleteByDocumentId(documentID);
        }

        List<Privileges> privileges = privilegesRepository.findAllByDocument_Id(documentID);
        for (Privileges i:
             privileges) {

            privilegesRepository.delete(i);

        }
        documentRepository.delete(documentID);

        return (null != documentRepository.findById(documentID));

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


    public List<Document> findByKeywordAndColobarator(String keyword, String email){


        Account owner = accountRepository.getAccountByEmail(email);

        return documentRepository.findAllByKeywordAndCollaborator("%"+keyword+"%", owner.getId());

    }

}
