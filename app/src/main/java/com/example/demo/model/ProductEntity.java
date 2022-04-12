package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "PRICE")
    @NotNull
    private BigDecimal price;

    @Column(name = "AVAILABILITY")
    @NotNull
    private Boolean availability;

}
