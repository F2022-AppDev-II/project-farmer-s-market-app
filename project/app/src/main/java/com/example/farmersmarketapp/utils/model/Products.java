package com.example.farmersmarketapp.utils.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Products_Table")
//https://developer.android.com/reference/android/os/Parcel
///https://developer.android.com/reference/android/os/Parcelable
public class Products {

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
    private int image;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "recentlyViewed")
    private boolean recentlyViewed;



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

    public String getHarvestByFarmer() {
        return harvestByFarmer;
    }

    public void setHarvestByFarmer(String harvestByFarmer) {
        this.harvestByFarmer = harvestByFarmer;
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

    @Nullable
    public int getImage() {
        return image;
    }

    public void setImage(@Nullable int image) {
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



    //TODO move this to a new database called CartItems
    //TODO add a new table called CartItems


    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "totalPrice")
    private double totalPrice;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}


