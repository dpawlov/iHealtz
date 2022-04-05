package com.example.ihealtzstore.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ProductDetailsViewModel {

    private Long id;
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
    private int qty;

}
