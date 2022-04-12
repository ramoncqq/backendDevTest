package com.example.demo.service.impl;

import com.example.demo.model.ProductDTO;
import com.example.demo.model.ProductEntity;
import com.example.demo.model.exception.NotFoundException;
import com.example.demo.repository.IProductRepository;
import com.example.demo.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public ProductDTO getProductById(String productId) throws NotFoundException {
        log.debug("Se consulta el producto con id {}", productId);
        return productRepository.findById(productId)
                .map(ProductDTO::convertEntityToDTO)
                .orElseThrow(() -> new NotFoundException("Product Not found"));

    }

    @Override
    public List<String> getProductSimilar(String productId) {
        log.debug("Se consulta listado de productos en base al id {}", productId);
        return productRepository.findByIdContainingOrderById(productId).stream().map(ProductEntity::getId)
                .collect(Collectors.toList());

    }
}
