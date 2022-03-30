package com.example.ihealtzstore.service.impl;

import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.service.AddProductServiceModel;
import com.example.ihealtzstore.repository.ProductRepository;
import com.example.ihealtzstore.service.AdminService;
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
        productEntity.setSin(addProductServiceModel.getSin());
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
}
