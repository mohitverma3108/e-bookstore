package com.assignment.bookstore.dao;

import com.assignment.bookstore.dto.BookTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTypeRepository extends JpaRepository<BookTypeDTO, Long> {

    List<BookTypeDTO> findByTypeName(String typeName);
}
