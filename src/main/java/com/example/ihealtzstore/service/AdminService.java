package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.entity.ProductEntity;
import com.example.ihealtzstore.model.service.AddProductServiceModel;
import com.example.ihealtzstore.model.service.UpdateProductServiceModel;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    void addProduct(AddProductServiceModel addProductServiceModel);

    void updateProduct(UpdateProductServiceModel updateProductServiceModel);
}
