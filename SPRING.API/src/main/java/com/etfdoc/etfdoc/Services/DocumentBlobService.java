package com.etfdoc.etfdoc.Services;

import com.etfdoc.etfdoc.Models.DocumentBlob;
import com.etfdoc.etfdoc.Repositories.IDocumentBlobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentBlobService {

    @Autowired
    private IDocumentBlobRepository documentBlobRepository;

    public void uploadDocument(DocumentBlob documentBlob){
        documentBlobRepository.saveAndFlush(documentBlob);
    }

    public DocumentBlob getByDocumentId(Long id){
        return documentBlobRepository.getByDocumentId(id);
    }

    public int deleteByDocumentId(Long id){
        return deleteByDocumentId(id);
    }

}
