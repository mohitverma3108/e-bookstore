package com.assignment.bookstore.service.impl;

import com.assignment.bookstore.dao.BookRepository;
import com.assignment.bookstore.dao.BookTypeRepository;
import com.assignment.bookstore.dto.BookDTO;
import com.assignment.bookstore.dto.BookTypeDTO;
import com.assignment.bookstore.exception.BookNotFoundException;
import com.assignment.bookstore.exception.BookStoreCustomException;
import com.assignment.bookstore.model.Book;
import com.assignment.bookstore.model.BookRequest;
import com.assignment.bookstore.model.CheckoutBooksRequest;
import com.assignment.bookstore.model.CheckoutBooksResponse;
import com.assignment.bookstore.service.BookStoreService;
import com.assignment.bookstore.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    static Logger LOG = LogManager.getLogger(BookStoreServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookTypeRepository bookTypeRepository;

    @Override
    public ResponseEntity<Book> searchBook(long bookId) {
        LOG.debug("Processing :: BookStoreServiceImpl : searchBook");
        try {
            LOG.info(Constants.BOOK_ID_MESSAGE + bookId);
            Optional<BookDTO> bookDTO = bookRepository.findById(bookId);
            if (bookDTO.isPresent()) {
                Optional<BookTypeDTO> bookTypeDTO = bookTypeRepository.findById(bookDTO.get().getTypeId());
                Book bookResponse = Book.builder().build();
                if(bookTypeDTO.isPresent()) {
                    bookResponse.setType(bookTypeDTO.get().getTypeName());
                }
                BeanUtils.copyProperties(bookDTO.get(), bookResponse);
                LOG.info("Retrieved book details from DB - " + bookResponse);
                return new ResponseEntity<>(bookResponse, HttpStatus.OK);
            } else {
                throw new BookNotFoundException();
            }
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOG.error(e);
            throw new BookStoreCustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Book> addBook(BookRequest book) {
        LOG.debug("Processing :: BookStoreServiceImpl : addBook");
        try {
            return saveOrUpdateBook(book, null);
        } catch (Exception e) {
            LOG.error(e);
            throw new BookStoreCustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Book> updateBookDetails(long bookId, BookRequest book) {
        LOG.debug("Processing :: BookStoreServiceImpl : updateBookDetails");
        try {
            LOG.info(Constants.BOOK_ID_MESSAGE + bookId);
            Optional<BookDTO> bookDTO = bookRepository.findById(bookId);
            if (bookDTO.isPresent()) {
                return saveOrUpdateBook(book, bookId);
            } else {
                throw new BookNotFoundException();
            }
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            LOG.error(e);
            throw new BookStoreCustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<HttpStatus> removeBook(long bookId) {
        LOG.debug("Processing :: BookStoreServiceImpl : removeBook");
        try {
            LOG.info(Constants.BOOK_ID_MESSAGE + bookId);
            bookRepository.deleteById(bookId);
            LOG.info("Book details removed successfully!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOG.error(e);
            throw new BookStoreCustomException(e.getMessage());
        }
    }
    @Override
    public ResponseEntity<CheckoutBooksResponse> checkoutAllBooks(CheckoutBooksRequest checkoutBooks) {
        LOG.debug("Processing :: BookStoreServiceImpl : checkoutAllBooks");
        try {
            List<Long> bookIdList = checkoutBooks.getBooks().stream().mapToLong(Book::getId).boxed().collect(Collectors.toList());
            LOG.info(Constants.BOOK_ID_MESSAGE + bookIdList);
            LOG.info("Received promotion code - " + checkoutBooks.getPromotionCode());
            String promotionCd = checkoutBooks.getPromotionCode() != null ? checkoutBooks.getPromotionCode() : "";
            Double totalPrice = bookRepository.checkoutAllBooks(promotionCd,bookIdList);
            LOG.info("Calculated total price of all books - "+totalPrice);
            return new ResponseEntity<CheckoutBooksResponse>(CheckoutBooksResponse.builder().totalPrice(totalPrice).build(), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e);
            throw new BookStoreCustomException(e.getMessage());
        }
    }

    public ResponseEntity<Book> saveOrUpdateBook(BookRequest book, Long bookId) {
        LOG.debug("Processing :: BookStoreServiceImpl : saveOrUpdateBook");
        BookDTO bookDTO = BookDTO.builder().build();
        if (bookId != null) {
            bookDTO.setId(bookId);
        }
        List<BookTypeDTO> bookTypeListDTO = bookTypeRepository.findByTypeName(book.getType().toLowerCase(Locale.ROOT));
        if (CollectionUtils.isEmpty(bookTypeListDTO)) {
            throw new BookStoreCustomException(Constants.INVALID_BOOK_TYPE_FOUND_ERROR_MESSAGE + bookTypeRepository.findAll().stream().map(BookTypeDTO::getTypeName).collect(Collectors.toList()));
        }
        BeanUtils.copyProperties(book, bookDTO);
        bookDTO.setTypeId(bookTypeListDTO.get(0).getId());
        BookDTO savedBookDTO = bookRepository.save(bookDTO);
        LOG.info("Saved/Updated Book details into DB " + savedBookDTO);
        Book bookResponse = Book.builder().type(book.getType().toLowerCase(Locale.ROOT)).build();
        BeanUtils.copyProperties(savedBookDTO, bookResponse);
        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

}
