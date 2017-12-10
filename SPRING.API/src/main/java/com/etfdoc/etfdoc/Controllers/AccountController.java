package com.etfdoc.etfdoc.Controllers;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Repositories.IAccountRepository;
import com.etfdoc.etfdoc.Services.AccountService;
import com.etfdoc.etfdoc.ViewModels.AccountVM;
import com.etfdoc.etfdoc.ViewModels.RoleVM;
import jdk.nashorn.internal.parser.JSONParser;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/account")
public class AccountController {

        private AccountService accountService;

        @Autowired
        public void setAccountService(AccountService accountService){
            this.accountService = accountService;
        }

        @Autowired
        IAccountRepository accountRepo;


        @RequestMapping(value = "/create", consumes = "application/json", method = RequestMethod.POST )
        public ResponseEntity createAccount(@RequestBody AccountVM accountVM)
        {
            try {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(accountService.createAccount(accountVM, "ROLE_USER"));
            }
            catch (ServiceException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(e.getLocalizedMessage());
            }catch (NoSuchAlgorithmException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(e.getLocalizedMessage());
            }
        }

        @RequestMapping(value = "/get", method = RequestMethod.GET)
        public ResponseEntity getAccountById(@RequestParam Long id){

            try{

                return ResponseEntity.status(HttpStatus.OK).
                        body(accountService.getAccountById(id));

            }catch (ServiceException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(e.getLocalizedMessage());
            }
        }

        @RequestMapping(value = "/getbyemail", method = RequestMethod.GET)
        public ResponseEntity getAccountByEmail(@RequestParam String email){

            try{

                return ResponseEntity.status(HttpStatus.OK).
                        body(accountService.getAccountByEmail(email));

            }catch (ServiceException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                        body(e.getLocalizedMessage());
            }

        }

    @RequestMapping(value = "/createrole", method = RequestMethod.POST )
    public ResponseEntity createRole(@RequestBody RoleVM role)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(accountService.addRole(role));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody AccountVM accountVM, Principal principal){
        try{
            if(accountService.update(accountVM, principal.getName())) {
                return ResponseEntity.status(HttpStatus.OK).body(true);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public RoleVM getRola(Principal principal) {

        return accountService.getRolaForUser(principal.getName());

    }

}
