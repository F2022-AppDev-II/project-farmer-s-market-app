package com.example.farmersmarketapp.db;

import android.app.Application;

import com.example.farmersmarketapp.db.generics.GenericRepository;
import com.example.farmersmarketapp.db.models.CartItem;

public class CartItemRepository extends GenericRepository<CartItem> {

    public CartItemRepository(Application app){
        super(app);
    }
}
