package com.example.ihealtzstore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItemEntity extends BaseEntity {

    private int qty;
    private BigDecimal productPrice;
    private OrderEntity order;
    private ProductEntity product;
    private ShoppingCartEntity shoppingCart;
    private List<ProductToCartItemEntity> productToCartItemList;

    @OneToMany(mappedBy = "cartItem")
    public List<ProductToCartItemEntity> getProductToCartItemList() {
        return productToCartItemList;
    }

    public void setProductToCartItemList(List<ProductToCartItemEntity> productToCartItemList) {
        this.productToCartItemList = productToCartItemList;
    }

    @OneToOne
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "shoppingCart_id")
    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @ManyToOne
    @JoinColumn(name="order_id")
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
