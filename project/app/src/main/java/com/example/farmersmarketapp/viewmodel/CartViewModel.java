package com.example.farmersmarketapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.utils.model.Products;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private CartRepo cartRepo;
    public CartViewModel(@NotNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }
    public LiveData<List<Products>> getAllCartItemsLiveData() {
        return cartRepo.getAllCartItemsLiveData();
    }
    public void insertCartItem(Products product){
        cartRepo.insertCartItem(product);
    }
    public void updateQuantity(int quantity, int id){
        cartRepo.updateQuantity(quantity, id);
    }
    public void updatePrice(int id, double totalPrice){
        cartRepo.updatePrice(id, totalPrice);
    }
    public void deleteCartItem(Products product){
        cartRepo.deleteCartItem(product);
    }

    public void deleteAllItems(){
        cartRepo.deleteAllItems();
    }

}
