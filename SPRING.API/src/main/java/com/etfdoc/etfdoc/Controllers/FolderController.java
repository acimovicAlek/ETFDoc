package com.etfdoc.etfdoc.Controllers;


import com.etfdoc.etfdoc.Models.Folder;
import com.etfdoc.etfdoc.Services.FolderService;
import com.etfdoc.etfdoc.ViewModels.AccountVM;
import com.etfdoc.etfdoc.ViewModels.FolderVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/folder")
public class FolderController {

    private FolderService folderService;

    @Autowired
    public void setFolderService(FolderService folderService){
        this.folderService = folderService;
    }

    @RequestMapping(value = "/create", consumes = "application/json", method = RequestMethod.POST )
    public ResponseEntity createFolder(@RequestBody FolderVM folderVM)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(folderService.createFolder(folderVM));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByParent", method = RequestMethod.GET)
    public ResponseEntity getFoldersByParent(@RequestParam Long id){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(folderService.getFoldersByParent(id));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByOwner", method = RequestMethod.GET)
    public ResponseEntity getFoldersByOwner(@RequestParam String email){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(folderService.getFolderByOwner(email));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getRootAndPublic", method = RequestMethod.GET)
    public ResponseEntity getRootAndPublicFolders(){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(folderService.getAllRootPublic());

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getOwnerAndRoot", method = RequestMethod.GET)
    public ResponseEntity getFoldersByOwnerAndRoot(@RequestParam String email){

        try{

            return ResponseEntity.status(HttpStatus.OK).
                    body(folderService.getAllByOwnerAndRoot(email));

        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getLocalizedMessage());
        }
    }



}
