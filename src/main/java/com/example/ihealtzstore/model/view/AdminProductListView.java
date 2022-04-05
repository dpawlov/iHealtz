package com.example.ihealtzstore.model.view;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminProductListView {
    private Long id;
    private String title;
    private String brand;
    private String category;
    private double size;
    private double price;
    private int inStockNumber;
}
