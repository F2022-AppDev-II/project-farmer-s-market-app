package com.example.farmersmarketapp.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmersmarketapp.db.models.CartItem;
import com.example.farmersmarketapp.db.models.Product;

import java.util.List;

public class FarmerViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

    private LiveData<List<Product>> allProducts;
    private LiveData<List<Product>> allViewedProducts;
    private LiveData<List<CartItem>> allCartItems;

    public FarmerViewModel(@NonNull Application application) {
        super(application);

        this.productRepository = new ProductRepository(application);
        this.cartItemRepository = new CartItemRepository(application);

        this.allProducts = productRepository.getAllProducts();
        this.allViewedProducts = productRepository.getAllViewedProducts();
        this.allCartItems = cartItemRepository.getAllCartItems();
    }

    // Product Repo
    public LiveData<List<Product>> getAllProducts(){
        return allProducts;
    }
    public LiveData<List<Product>> getAllViewedProducts(){
        return allViewedProducts;
    }
    public Product getProduct(int id){
        return productRepository.getProduct(id);
    }
    public int getProductListSize(){
        return productRepository.getSize();
    }
    public void insertProduct(Product product){
        productRepository.insert(product);
    }
    public void updateProduct(Product product){
        productRepository.update(product);
    }
    public void deleteAllProducts(){
        productRepository.deleteAll();
    }
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }
    public void setProductToTopViewedPriority(Product product){
        productRepository.setToTopViewedPriority(product.getId());
    }

    // CartItem Repo
    public LiveData<List<CartItem>> getAllCartItems(){
        return allCartItems;
    }
    public CartItem getCartItem(int id){
        return cartItemRepository.getCartItem(id);
    }
    public int getCartItemListSize(){
        return cartItemRepository.getSize();
    }
    public void insertCartItem(CartItem cartItem){
        cartItemRepository.insert(cartItem);
    }
    public void updateCartItem(CartItem cartItem){
        cartItemRepository.update(cartItem);
    }
    public void deleteAllCartItems(){
        cartItemRepository.deleteAll();
    }
    public void deleteCartItem(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }
}
