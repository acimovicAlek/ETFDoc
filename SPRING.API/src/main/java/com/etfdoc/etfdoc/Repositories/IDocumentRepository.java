package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Document;
import com.etfdoc.etfdoc.Models.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface IDocumentRepository extends PagingAndSortingRepository<Document, Long> {

    List<Document> findAllByOwner(Account owner);

    List<Document> findAllByPrivateFlag(Boolean privateFlag);

    List<Document> findAllByFolder(Folder folder);

    Document findById(Long id);

    List<Document> findAllByOwnerAndFolderIsNull(Account owner);

    List<Document> findAllByOwnerAndFolder(Account owner, Folder folder);

    List<Document> findAllByFolderIsNullAndPrivateFlagIsFalse();

   /* @Query("SELECT DISTINCT d FROM docmument d, privileges p WHERE d.owner_id = ?2 OR " +
            "p.account_id = ?2 OR d.prvate_flag = TRUE AND d.name LIKE  ?1")
    List<Document> findAllByKeywordAndCollaborator(String keyword, Long colabID);*/

}