package com.example.ihealtzstore.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class AddProductBindingModel {

    @NotNull(message = "Title cannot be empty !")
    private String title;

    @NotNull(message = "Brand cannot be empty !")
    private String brand;

    @NotNull(message = "Flavour cannot be empty !")
    private String flavour;

    @NotNull(message = "Description cannot be empty !")
    private String description;

    @NotNull(message = "Suggested use cannot be empty !")
    private String suggestedUse;

    @NotNull(message = "Category cannot be empty !")
    private String category;

    @NotNull(message = "Sin cannot be empty !")
    @Size(min = 5, max = 10, message = "Sin must be between 5 and 10 characters!")
    private String sin;

    @NotNull(message = "Manufacturer cannot be empty !")
    private String manufacturer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

    @NotNull(message = "Allergen Information cannot be empty !")
    private String allergenInformation;

    @NotNull(message = "Size cannot be empty!")
    @DecimalMin(value = "1", message = "Size must be positive!")
    private double size;

    @NotNull(message = "Price cannot be empty")
    @DecimalMin(value = "1", message = "Price must be positive!")
    private double price;

    @NotNull(message = "Discount price cannot be empty")
    @DecimalMin(value = "1", message = "Discount price must be positive!")
    private double discountPrice;

    @NotNull(message = "In stock number must be positive!")
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
