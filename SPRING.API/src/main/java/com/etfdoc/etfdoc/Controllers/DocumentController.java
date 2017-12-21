package com.etfdoc.etfdoc.Controllers;

import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.DocumentBlob;
import com.etfdoc.etfdoc.Services.AccountService;
import com.etfdoc.etfdoc.Services.DocumentBlobService;
import com.etfdoc.etfdoc.Services.DocumentService;
import com.etfdoc.etfdoc.ViewModels.DocumentVM;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/document")
public class DocumentController {

    private DocumentService documentService;
    private AccountService accountService;
    private DocumentBlobService documentBlobService;

    @Autowired
    public void setDocumentService(DocumentService documentService){
        this.documentService = documentService;
    }
    @Autowired
    public void setAccountService(AccountService accountService) {this.accountService = accountService; }
    @Autowired
    public void setDocumentBlobService(DocumentBlobService documentBlobService) { this.documentBlobService = documentBlobService; }


    @RequestMapping(value = "/create", method = RequestMethod.POST )
    public ResponseEntity createFolder(@RequestBody DocumentVM documentVM)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(documentService.createDocument(documentVM));
        }
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getLocalizedMessage());
        }
    }



    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public ResponseEntity getAllPublic(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(documentService.getAllRoot());
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public ResponseEntity getAllPrivate(@RequestParam String email){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(documentService.getAllPrivate(email));
        }catch (ServiceException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestParam Long id)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(documentService.deleteDocument(id));
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/getByKeywordAndCollaborator", method = RequestMethod.GET)

    public ResponseEntity getByKeywordAndCollaborator(@RequestParam String keyword, String email){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(documentService.findByKeywordAndColobarator(keyword,email));
        }catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uplaoad(MultipartHttpServletRequest request, @RequestParam String email){

        DocumentVM documentVM = new DocumentVM();
        documentVM.setOwner(email);
        documentVM.setPrivateFlag(false);
        documentVM.setNative_flag(false);

        try{
            Iterator<String> itr =  request.getFileNames();

            while(itr.hasNext()){
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String fileType = file.getContentType();
                String fileName = file.getOriginalFilename();
                documentVM.setName(fileName);

                Document document = documentService.createDocument(documentVM);

                byte[] data = file.getBytes();

                DocumentBlob newDoc = new DocumentBlob(
                        data,
                        fileType,
                        document
                );

                documentBlobService.uploadDocument(newDoc);

            }
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded!");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
        }

    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity download(@RequestParam Long id){
        DocumentBlob file =documentBlobService.getByDocumentId(id);

        if(file==null){
            return new ResponseEntity<>("{}",HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();

        headers.add("content-disposition", "attachment; filename=" + file.getName());

        String primaryType,subType;
        try {
            primaryType = file.getFileType().split("/")[0];
            subType = file.getFileType().split("/")[1];
        }
        catch (IndexOutOfBoundsException | NullPointerException ex) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        headers.setContentType( new MediaType(primaryType, subType) );

        return new ResponseEntity<>(file.getData(), headers, HttpStatus.OK);
    }

}
