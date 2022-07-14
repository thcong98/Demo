package com.tmasolutions.service;

import com.tmasolutions.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookService {
    List<Book> findByNameContaining(String search);

    //Required
    Book updateNameRequired(Long id, String name);
    Book updateDescriptionRequired(Long id, String description);
    Book updateLanguageRequired(Long id, String language);
    Book updateAuthorNameRequired(Long id, String author);

    //
    Book updateNameReadOnly(Long id, String name);
    Book updateNameRollBack(Long id, String name) throws SQLException;

//    NEVER
    Book updateNameNever(Long id, String name);
    Book updateDescriptionNever(Long id, String description);
    Book updateLanguageNever(Long id, String language);
    Book updateAuthorNameNever(Long id, String authorName);

    //SUPPORTS
    Book updateNameSupports(Long id, String name);
    Book updateDescriptionSupports(Long id, String description);
    Book updateLanguageSupports(Long id, String language);
    Book updateAuthorNameSupports(Long id, String authorName);
    //NOT_SUPPORT
    Book updateNameNotSupports(Long id, String name);
    Book updateDescriptionNotSupports(Long id, String description);
    Book updateLanguageNotSupports(Long id, String language);
    Book updateAuthorNameNotSupports(Long id, String authorName);

    //REQUIRES_NEW
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Book updateNameRequiresNew(Long id, String name);

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Book updateDescriptionRequiresNew(Long id, String description);
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Book updateLanguageRequiresNew(Long id, String language);
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Book updateAuthorNameRequiresNew(Long id, String authorName);

    //NESTED
    Book updateNameNested(Long id, String name);
    Book updateDescriptionNested(Long id, String description);
    Book updateLanguageNested(Long id, String language);
    Book updateAuthorNameNested(Long id, String authorName);

    //MANDATORY
   // @Transactional(propagation = Propagation.MANDATORY)
    Book updateNameMandatory(Long id, String name);

  //  @Transactional(propagation = Propagation.MANDATORY)
    Book updateDescriptionMandatory(Long id, String description);

  //  @Transactional(propagation = Propagation.MANDATORY)
    Book updateLanguageMandatory(Long id, String language);

    //@Transactional(propagation = Propagation.MANDATORY)
    Book updateAuthorNameMandatory(Long id, String authorName);
}
