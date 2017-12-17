package com.etfdoc.etfdoc.Services;


import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Privileges;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.Repositories.IDocumentRepository;
import com.etfdoc.etfdoc.Repositories.IPrivilegesRepository;
import com.etfdoc.etfdoc.ViewModels.PrivilegesVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class PrivilegesService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IDocumentRepository documentRepository;
    @Autowired
    private IPrivilegesRepository privilegesRepository;

    public Privileges findByAccountAndDocument(String email, Long documentID){

        return privilegesRepository.findByAccount_EmailAndDocument_Id(email,documentID);

    }

    public List<Privileges> findAllByAccount(String email){

        return privilegesRepository.findAllByAccount_Email(email);

    }

    public List<Privileges> finfAllByDocument(Long documentID){

        return privilegesRepository.findAllByDocument_Id(documentID);

    }

    public Privileges createPrivilege(PrivilegesVM privilegesVM){

        Account account = accountRepository.getAccountByEmail(privilegesVM.getAccount());
        Document document = documentRepository.findById(privilegesVM.getDocument());

        Privileges newPrivileges = new Privileges(
                account,
                document,
                privilegesVM.getRead(),
                privilegesVM.getWrite(),
                privilegesVM.getUpdate()
        );

        return privilegesRepository.save(newPrivileges);

    }

}
