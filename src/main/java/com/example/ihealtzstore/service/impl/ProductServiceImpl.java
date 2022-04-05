package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.view.AdminProductListView;
import com.example.ihealtzstore.model.view.ProductDetailsViewModel;
import com.example.ihealtzstore.model.view.ProductsView;
import com.example.ihealtzstore.repository.ProductRepository;
import com.example.ihealtzstore.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<AdminProductListView> getAll() {

        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, AdminProductListView.class))
                .collect(Collectors.toList());
    }


    //Returning view model by Id
    @Override
    public ProductDetailsViewModel findById(Long id) {
        return productRepository.findById(id)
                .map(productEntity -> modelMapper.map(productEntity, ProductDetailsViewModel.class))
                .orElse(null);
    }

    @Override
    public List<ProductsView> findAll() {
        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsView.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductEntity findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
