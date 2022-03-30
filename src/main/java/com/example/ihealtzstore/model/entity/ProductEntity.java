package com.example.ihealtzstore.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    //TODO Quantity; KEY BENEFITS; NUTRITIONAL INFORMATION; REVIEWS;

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

    @Transient
    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }
}
