package com.assignment.bookstore.dao;

import com.assignment.bookstore.dto.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookDTO, Long> {

    @Query(value = "SELECT SUM(CASE WHEN EXISTS (SELECT 1 FROM promotion WHERE prom_cd = :promotionCd AND prom_valid = 'Y')" +
            " THEN (price - (b.price * bt.total_discount/100))  ELSE b.price END)" +
            " FROM (select sum(price) AS price, type_id FROM books" +
            " WHERE id in (:bookIdList) GROUP BY type_id) b" +
            " INNER JOIN book_type bt ON b.type_id = bt.id",
            nativeQuery = true)
    Double checkoutAllBooks(String promotionCd, List<Long> bookIdList);

}

