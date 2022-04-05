package com.example.ihealtzstore.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductsView {
    private Long id;
    private String title;
    private String brand;
    private String flavour;
    private String description;
    private String category;
    private double size;
    private double price;
    private double discountPrice;
}
