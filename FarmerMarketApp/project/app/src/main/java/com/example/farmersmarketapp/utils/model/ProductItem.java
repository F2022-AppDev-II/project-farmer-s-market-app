package com.example.farmersmarketapp.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.farmersmarketapp.db.models.Product;

public class ProductItem implements Parcelable {

    private Product product;

    private int Id;

    private String productName;

    private String harvestByFarmer;

    private String description;

    private Integer category;

    private Integer image;

    private double price;


    public ProductItem(Product product, Integer image){
        this.product = product;
        this.productName = product.getProductName();
        this.harvestByFarmer = product.getHarvestByFarmer();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.image = image;
        this.price = product.getPrice();
        this.Id = product.getId();
    }

    protected ProductItem(Parcel in) {
        productName = in.readString();
        harvestByFarmer = in.readString();
        description = in.readString();
        category = in.readInt();
        image = in.readInt();
        price = in.readDouble();
        Id = in.readInt();
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
    public int getId(){return this.Id;}

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
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
        parcel.writeInt(category);
        parcel.writeInt(image);
        parcel.writeDouble(price);
        parcel.writeInt(Id);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.harvestByFarmer = product.getHarvestByFarmer();
        this.category = product.getCategory();
        this.image = product.getImage();
        this.price = product.getPrice();
        this.Id = product.getId();
    }
}

