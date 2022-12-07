package com.example.farmersmarketapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.farmersmarketapp.db.generics.GenericDao;
import com.example.farmersmarketapp.db.models.Product;

import java.util.List;

@Dao
public abstract class ProductDao extends GenericDao<Product> {

    private static final String TABLE_NAME = "Product";

    protected ProductDao() {
        super(TABLE_NAME);
    }

    @Query("SELECT * FROM " + TABLE_NAME)
    public abstract LiveData<List<Product>> getAll();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE recentlyViewed = 1 ORDER BY viewedPriority DESC")
    public abstract LiveData<List<Product>> getAllRecentlyViewed();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE category = :category")
    public abstract LiveData<List<Product>> getAllByCategory(int category);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE id = :id")
    public abstract Product getProduct(int id);

    @Query("SELECT * FROM " + TABLE_NAME + " LIMIT 1")
    public abstract Product[] getAnyProduct();

    @Query("SELECT COUNT(*) FROM " + TABLE_NAME)
    public abstract int getSize();

    @Query("UPDATE " + TABLE_NAME + " SET viewedPriority = (SELECT MAX(viewedPriority) + 1 FROM " + TABLE_NAME + ") WHERE recentlyViewed = 1 AND id = :id")
    public abstract void setToTopViewedPriority(int id);
}
