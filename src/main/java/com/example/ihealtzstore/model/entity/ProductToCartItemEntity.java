package com.example.ihealtzstore.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_to_cart_item")
public class ProductToCartItemEntity extends BaseEntity{

    private ProductEntity product;
    private CartItemEntity cartItem;
    private List<ProductToCartItemEntity> productToCartItemList;


    @ManyToOne
    @JoinColumn(name = "product_id")
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    public CartItemEntity getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItemEntity cartItem) {
        this.cartItem = cartItem;
    }

    @OneToMany(mappedBy = "product")
    public List<ProductToCartItemEntity> getProductToCartItemList() {
        return productToCartItemList;
    }

    public void setProductToCartItemList(List<ProductToCartItemEntity> productToCartItemList) {
        this.productToCartItemList = productToCartItemList;
    }
}
