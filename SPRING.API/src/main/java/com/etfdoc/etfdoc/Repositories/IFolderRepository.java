package com.etfdoc.etfdoc.Repositories;

import com.etfdoc.etfdoc.Models.Account;
import com.etfdoc.etfdoc.Models.Folder;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IFolderRepository extends PagingAndSortingRepository<Folder,Long> {

    List<Folder> findAllByOwner(Account owner);

    List<Folder> findAllByParentFolder(Folder parentFolder);

}
