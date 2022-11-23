package com.example.farmersmarketapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.farmersmarketapp.db.generics.GenericDao;
import com.example.farmersmarketapp.db.models.CartItem;

import java.util.List;

@Dao
public abstract class CartItemDao extends GenericDao<CartItem> {

    private static final String TABLE_NAME = "CartItem";

    protected CartItemDao() {
        super(TABLE_NAME);
    }

    @Override
    public int doDeleteAll(SimpleSQLiteQuery sqLiteQuery) {return 0;}

    @Query("SELECT * FROM " + TABLE_NAME)
    public abstract LiveData<List<CartItem>> getAll();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE id = :id")
    public abstract CartItem getCartItem(int id);

    @Query("SELECT * FROM " + TABLE_NAME + " LIMIT 1")
    public abstract CartItem[] getAnyCartItem();

    @Query("SELECT COUNT(*) FROM " + TABLE_NAME)
    public abstract int getSize();


}
