package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Privileges;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface IDocumentRepository extends PagingAndSortingRepository<Document, Long> {

    List<Document> findAllByOwner(Account owner);

    List<Document> findAllByPrivateFlag(Boolean privateFlag);

    Document findById(Long id);


    @Query("SELECT DISTINCT d FROM Document d, Privileges p WHERE (d.owner.id = ?2 OR " +
            "p.account.id = ?2 OR d.privateFlag = FALSE) AND (d.name LIKE  ?1)")

    List<Document> findAllByKeywordAndCollaborator(String keyword, Long colabID);

}