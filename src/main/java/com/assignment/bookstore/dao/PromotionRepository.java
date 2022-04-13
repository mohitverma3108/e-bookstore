package com.assignment.bookstore.dao;

import com.assignment.bookstore.dto.PromotionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<PromotionDTO, Long> {

}
