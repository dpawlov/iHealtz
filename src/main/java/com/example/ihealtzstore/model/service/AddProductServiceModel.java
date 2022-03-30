package com.example.ihealtzstore.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import javax.persistence.Transient;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AddProductServiceModel {

    private String title;
    private String brand;
    private String flavour;
    private String description;
    private String suggestedUse;
    private String category;
    private String sin;
    private String manufacturer;
    private Date expirationDate;
    private String allergenInformation;
    private double size;
    private double price;
    private double discountPrice;
    private int inStockNumber;
    private MultipartFile productImage;

}
