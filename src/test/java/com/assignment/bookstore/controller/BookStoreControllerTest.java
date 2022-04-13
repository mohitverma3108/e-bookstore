package com.assignment.bookstore.controller;

import com.assignment.bookstore.dao.BookRepository;
import com.assignment.bookstore.dao.BookTypeRepository;
import com.assignment.bookstore.exception.BookStoreCustomException;
import com.assignment.bookstore.exception.GlobalControllerExceptionHandler;
import com.assignment.bookstore.model.BookRequest;
import com.assignment.bookstore.service.BookStoreService;
import com.assignment.bookstore.util.BookStoreTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookStoreControllerTest {
    private static final String API_BASE_PATH = "/store/";

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    @Autowired
    BookStoreController controller;

    @Mock
    BookStoreService bookStoreService;

    @MockBean
    BookRepository bookRepository;

    @Mock
    BookTypeRepository bookTypeRepository;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalControllerExceptionHandler())
                .build();
    }

    @Test
    public void testAddBook_Created() throws Exception {
        given(bookRepository.save(any()))
                .willReturn(BookStoreTestUtil.buildBookDTORequest(1l, 15l));
        mockMvc.perform(
                        post(API_BASE_PATH + "add-book").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(BookStoreTestUtil.buildBookRequest())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddBook_Invalid() throws Exception {
        BookRequest request = BookStoreTestUtil.buildBookRequest();
        request.setType("invalid");
        mockMvc.perform(
                        post(API_BASE_PATH + "add-book").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testSearchBook_NotFound() throws Exception {
        mockMvc.perform(
                        get(API_BASE_PATH + "search-book/1").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchBook_Found() throws Exception {
        // given
        given(bookRepository.findById(anyLong()))
                .willReturn(Optional.of(BookStoreTestUtil.buildBookDTORequest(1l, 15l)));
        mockMvc.perform(
                        get(API_BASE_PATH + "search-book/1").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchBook_Invalid() throws Exception {
        // given
        /*given(bookRepository.findById(anyLong()))
                .willReturn(Optional.of(BookStoreTestUtil.buildBookDTORequest(1l, 15l)));*/
        given(bookRepository.findById(anyLong())).willThrow(BookStoreCustomException.class);
        mockMvc.perform(
                        get(API_BASE_PATH + "search-book/1").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testModifyBook_NotFound() throws Exception {

        mockMvc.perform(
                        put(API_BASE_PATH + "modify-book/1").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(BookStoreTestUtil.buildBookRequest())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testModifyBook_Updated() throws Exception {
        // given
        given(bookRepository.findById(anyLong()))
                .willReturn(Optional.of(BookStoreTestUtil.buildBookDTORequest(1l, 15l)));

        given(bookRepository.save(any()))
                .willReturn(BookStoreTestUtil.buildBookDTORequest(1l, 15l));
        mockMvc.perform(
                        put(API_BASE_PATH + "modify-book/1").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(BookStoreTestUtil.buildBookRequest())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRemoveBook() throws Exception {

        mockMvc.perform(
                        delete(API_BASE_PATH + "remove-book/1").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(BookStoreTestUtil.buildBookRequest())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testRemoveBook_NotFound() throws Exception {
        willThrow(BookStoreCustomException.class).given(bookRepository).deleteById(anyLong());

        mockMvc.perform(
                        delete(API_BASE_PATH + "remove-book/2").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(BookStoreTestUtil.buildBookRequest())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void checkoutBook() throws Exception {
        mockMvc.perform(
                        get(API_BASE_PATH + "checkout-book").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(BookStoreTestUtil.buildCheckoutBooksRequest())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void checkoutBook_NotFound() throws Exception {
        willThrow(BookStoreCustomException.class).given(bookRepository).checkoutAllBooks(any(), any());

        mockMvc.perform(
                        get(API_BASE_PATH + "checkout-book").contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(BookStoreTestUtil.buildCheckoutBooksRequest())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}