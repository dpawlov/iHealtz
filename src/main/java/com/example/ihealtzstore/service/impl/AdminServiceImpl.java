package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.service.AddProductServiceModel;
import com.example.ihealtzstore.model.service.UpdateProductServiceModel;
import com.example.ihealtzstore.repository.ProductRepository;
import com.example.ihealtzstore.service.AdminService;
import com.example.ihealtzstore.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;


@Service
public class AdminServiceImpl implements AdminService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public AdminServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(AddProductServiceModel addProductServiceModel) {

        ProductEntity productEntity = new ProductEntity();

        productEntity.setTitle(addProductServiceModel.getTitle());
        productEntity.setBrand(addProductServiceModel.getBrand());
        productEntity.setFlavour(addProductServiceModel.getFlavour());
        productEntity.setDescription(addProductServiceModel.getDescription());
        productEntity.setSuggestedUse(addProductServiceModel.getSuggestedUse());
        productEntity.setCategory(addProductServiceModel.getCategory());
        productEntity.setSin(addProductServiceModel.getSin());
        productEntity.setManufacturer(addProductServiceModel.getManufacturer());
        productEntity.setExpirationDate(addProductServiceModel.getExpirationDate());
        productEntity.setAllergenInformation(addProductServiceModel.getAllergenInformation());
        productEntity.setSize(addProductServiceModel.getSize());
        productEntity.setPrice(addProductServiceModel.getPrice());
        productEntity.setDiscountPrice(addProductServiceModel.getDiscountPrice());
        productEntity.setInStockNumber(addProductServiceModel.getInStockNumber());

        productRepository.save(productEntity);

        MultipartFile productImage = addProductServiceModel.getProductImage();

        try {
            byte[] bytes = productImage.getBytes();
            String name = productEntity.getId() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream("src/main/resources/static/image/products/" + name));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateProduct(UpdateProductServiceModel updateProductServiceModel) {

        ProductEntity productEntity = productRepository.findById(updateProductServiceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Product with id " + updateProductServiceModel.getId() + " not found!"));

        productEntity.setTitle(updateProductServiceModel.getTitle());
        productEntity.setBrand(updateProductServiceModel.getBrand());
        productEntity.setFlavour(updateProductServiceModel.getFlavour());
        productEntity.setDescription(updateProductServiceModel.getDescription());
        productEntity.setSuggestedUse(updateProductServiceModel.getSuggestedUse());
        productEntity.setCategory(updateProductServiceModel.getCategory());
        productEntity.setSin(updateProductServiceModel.getSin());
        productEntity.setManufacturer(updateProductServiceModel.getManufacturer());
        productEntity.setExpirationDate(updateProductServiceModel.getExpirationDate());
        productEntity.setAllergenInformation(updateProductServiceModel.getAllergenInformation());
        productEntity.setSize(updateProductServiceModel.getSize());
        productEntity.setPrice(updateProductServiceModel.getPrice());
        productEntity.setDiscountPrice(updateProductServiceModel.getDiscountPrice());
        productEntity.setInStockNumber(updateProductServiceModel.getInStockNumber());
        productEntity.setProductImage(updateProductServiceModel.getProductImage());
        productRepository.save(productEntity);
    }
}



