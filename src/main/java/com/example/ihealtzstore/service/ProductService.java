package com.example.ihealtzstore.service;


import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.view.AdminProductListView;
import com.example.ihealtzstore.model.view.ProductDetailsViewModel;
import com.example.ihealtzstore.model.view.ProductsView;

import java.util.List;

public interface ProductService {
    List<AdminProductListView> getAll();

    ProductDetailsViewModel findById(Long id);

    List<ProductsView> findAll();

    ProductEntity findProductById(Long id);

    void deleteProductById(Long id);
}
