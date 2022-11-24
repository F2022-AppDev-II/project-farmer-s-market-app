package com.example.farmersmarketapp.db.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "productName")
    private String productName;

    @ColumnInfo(name = "harvestByFarmer")
    private String harvestByFarmer;

    @Nullable
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "category")
    @NonNull
    private Integer category;

    @Nullable
    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "recentlyViewed")
    private boolean recentlyViewed;

    @ColumnInfo(name = "viewedPriority")
    private Integer viewedPriority;

    public Product(@NonNull String productName, String description, String harvestByFarmer, Integer category, double price, String image){
        this.productName = productName;
        this.description = description;
        this.harvestByFarmer = harvestByFarmer;
        this.category = category;
        this.price = price;
        this.image = image;
        this.recentlyViewed = false;
        this.viewedPriority = 0;
    }

    @Ignore
    public Product(int id, @NonNull String productName, String description, String harvestByFarmer, Integer category, double price, String image){
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.harvestByFarmer = harvestByFarmer;
        this.category = category;
        this.price = price;
        this.image = image;
        this.recentlyViewed = false;
        this.viewedPriority = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @NonNull
    public Integer getCategory() {
        return category;
    }

    public void setCategory(@NonNull Integer category) {
        this.category = category;
    }

    public String getHarvestByFarmer() {
        return harvestByFarmer;
    }

    public void setHarvestByFarmer(String harvestByFarmer) {
        this.harvestByFarmer = harvestByFarmer;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRecentlyViewed() {
        return recentlyViewed;
    }

    public void setRecentlyViewed(boolean recentlyViewed) {
        this.recentlyViewed = recentlyViewed;
    }

    public Integer getViewedPriority() {
        return viewedPriority;
    }

    public void setViewedPriority(Integer viewedPriority) {
        this.viewedPriority = viewedPriority;
    }
}
