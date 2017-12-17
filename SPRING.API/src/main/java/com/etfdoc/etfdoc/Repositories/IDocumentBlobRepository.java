package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.DocumentBlob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IDocumentBlobRepository extends JpaRepository<DocumentBlob,Long> {

    @Query("select p from DocumentBlob p where p.document.id = ?1")
    DocumentBlob getByDocumentId(Long id);

    @Modifying
    @Transactional
    @Query("delete from DocumentBlob p where p.document.id =?1")
    int deleteByDocumentId(Long id);

}
