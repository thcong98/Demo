package com.tmasolutions.service.Impl;

import com.tmasolutions.model.Book;
import com.tmasolutions.repo.BookRepository;
import com.tmasolutions.service.IBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    private BookRepository bookRepository;

    @Override
    public List<Book> findByNameContaining(String search) {
        return bookRepository.findByNameContaining(search);
    }

    /*Transactional_REQUIRED*/
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Book updateNameRequired(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Book updateDescriptionRequired(Long id, String description) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(description);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Book updateLanguageRequired(Long id, String language) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(language);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Book updateAuthorNameRequired(Long id, String authorName) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(authorName);
        bookRepository.save(bk);
        return bk;
    }

    /////////////////////////////////////////////////////////////////////////
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Book updateNameReadOnly(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional
    public Book updateNameRollBack(Long id, String name) throws SQLException {
        try {
            Book bk = bookRepository.findById(id).get();
            bk.setLanguage(name);
            bookRepository.save(bk);
            int i = 1/0;
            return bk;
        }catch (Exception ex)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new SQLException("Throwing exception for demoing rollback");
        }
    }

    /*Transactional_NEVER*/
    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Book updateNameNever(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Book updateDescriptionNever(Long id, String description) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(description);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Book updateLanguageNever(Long id, String language) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(language);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Book updateAuthorNameNever(Long id, String authorName) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(authorName);
        bookRepository.save(bk);
        return bk;
    }

    /*Transactional_SUPPORTS*/
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Book updateNameSupports(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Book updateDescriptionSupports(Long id, String description) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(description);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Book updateLanguageSupports(Long id, String language) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(language);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Book updateAuthorNameSupports(Long id, String authorName) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(authorName);
        bookRepository.save(bk);
        return bk;
    }

    /*Transactional_NOT_SUPPORTED*/
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Book updateNameNotSupports(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Book updateDescriptionNotSupports(Long id, String description) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(description);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Book updateLanguageNotSupports(Long id, String language) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(language);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Book updateAuthorNameNotSupports(Long id, String authorName) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(authorName);
        bookRepository.save(bk);
        return bk;
    }

    /*Transactional_REQUIRES_NEW*/
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book updateNameRequiresNew(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book updateDescriptionRequiresNew(Long id, String description) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(description);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book updateLanguageRequiresNew(Long id, String language) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(language);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book updateAuthorNameRequiresNew(Long id, String authorName) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(authorName);
        bookRepository.save(bk);
        return bk;
    }

    /*Transactional_NESTED*/
    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Book updateNameNested(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Book updateDescriptionNested(Long id, String description) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(description);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Book updateLanguageNested(Long id, String language) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(language);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Book updateAuthorNameNested(Long id, String authorName) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(authorName);
        bookRepository.save(bk);
        return bk;
    }


    /*Transactional_MANDATORY*/
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Book updateNameMandatory(Long id, String name) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(name);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Book updateDescriptionMandatory(Long id, String description) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(description);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Book updateLanguageMandatory(Long id, String language) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(language);
        bookRepository.save(bk);
        return bk;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Book updateAuthorNameMandatory(Long id, String authorName) {
        Book bk = bookRepository.findById(id).get();
        bk.setName(authorName);
        bookRepository.save(bk);
        return bk;
    }
}
