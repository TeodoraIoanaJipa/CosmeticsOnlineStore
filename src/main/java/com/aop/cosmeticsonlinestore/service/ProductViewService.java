package com.aop.cosmeticsonlinestore.service;

import com.aop.cosmeticsonlinestore.model.ProductView;
import com.aop.cosmeticsonlinestore.repository.ProductViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductViewService {

    @Autowired
    ProductViewRepository productViewRepository;

    public Optional<ProductView> findById(Long id) {
        return productViewRepository.findById(id);
    }

    public Collection<ProductView> findByProductId(Long id) {
        return productViewRepository.findByProductId(id);
    }

    public ProductView save(ProductView productView) {
        return productViewRepository.save(productView);
    }
}
