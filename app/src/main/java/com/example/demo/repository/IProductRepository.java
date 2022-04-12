package com.example.demo.repository;

import com.example.demo.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findByIdContainingOrderById(String id);

}
