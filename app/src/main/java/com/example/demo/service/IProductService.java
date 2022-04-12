package com.example.demo.service;

import com.example.demo.model.ProductDTO;
import com.example.demo.model.exception.NotFoundException;

import java.util.List;

public interface IProductService {

    ProductDTO getProductById(String productId) throws NotFoundException;


    List<String> getProductSimilar(String productIdList);

}
