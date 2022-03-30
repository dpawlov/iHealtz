package com.example.ihealtzstore.service;

import com.example.ihealtzstore.model.service.AddProductServiceModel;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {

    void addProduct(AddProductServiceModel addProductServiceModel);
}
