package com.example.farmersmarketapp.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductItem implements Parcelable {


    private String productName;

    private String harvestByFarmer;

    private String description;

    private String category;

    private int image;

    private double price;

    public ProductItem(String productName, String harvestByFarmer, String description, String category, int image, double price) {
        this.productName = productName;
        this.harvestByFarmer = harvestByFarmer;
        this.description = description;
        this.category = category;
        this.image = image;
        this.price = price;
    }

    protected ProductItem(Parcel in) {
        productName = in.readString();
        harvestByFarmer = in.readString();
        description = in.readString();
        category = in.readString();
        image = in.readInt();
        price = in.readDouble();
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHarvestByFarmer() {
        return harvestByFarmer;
    }

    public void setHarvestByFarmer(String harvestByFarmer) {
        this.harvestByFarmer = harvestByFarmer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productName);
        parcel.writeString(harvestByFarmer);
        parcel.writeString(description);
        parcel.writeString(category);
        parcel.writeInt(image);
        parcel.writeDouble(price);
    }

}

