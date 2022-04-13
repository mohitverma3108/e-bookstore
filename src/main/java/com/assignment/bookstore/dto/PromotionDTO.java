package com.assignment.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion")
public class PromotionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "prom_cd", unique=true)
    private String promotionCode;

    @Column(name = "prom_valid")
    private String promotionValid;

    @Column(name = "last_updated_on")
    @UpdateTimestamp
    private Timestamp lastUpdatedOn;
}