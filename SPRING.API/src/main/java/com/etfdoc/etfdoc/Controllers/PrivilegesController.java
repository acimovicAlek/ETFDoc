package com.etfdoc.etfdoc.Controllers;

import com.etfdoc.etfdoc.Models.Privileges;
import com.etfdoc.etfdoc.Services.PrivilegesService;
import com.etfdoc.etfdoc.ViewModels.AccountVM;
import com.etfdoc.etfdoc.ViewModels.PrivilegesVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/privileges")
public class PrivilegesController {

    private PrivilegesService privilegesService;

    @Autowired
    public void setPrivilegesService(PrivilegesService privilegesService){

        this.privilegesService = privilegesService;

    }

    @RequestMapping(value = "/create", consumes = "application/json", method = RequestMethod.POST )
    public ResponseEntity createPrivileges(@RequestBody PrivilegesVM privilegesVM)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(privilegesService.createPrivilege(privilegesVM));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByAccountAndDocument", method = RequestMethod.GET)
    public ResponseEntity getPrivilegesByOwnerAndDocument(@RequestParam String email, Long documentID){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(privilegesService.findByAccountAndDocument(email,documentID));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByAccount", method = RequestMethod.GET)
    public ResponseEntity getPrivilegesByAccount(@RequestParam String email){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(privilegesService.findAllByAccount(email));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByDocument", method = RequestMethod.GET)
    public ResponseEntity getPrivilegesByDocument(@RequestParam Long documentID){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(privilegesService.finfAllByDocument(documentID));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

}
