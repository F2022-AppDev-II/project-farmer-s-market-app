package com.example.farmersmarketapp.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "CartItem",
    foreignKeys = {@ForeignKey(entity = Product.class,
        parentColumns = "id",
        childColumns = "productId",
        onDelete = ForeignKey.CASCADE)})
public class CartItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "productId", index = true)
    private int productId;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "lineTotal")
    private double lineTotal;


    @ColumnInfo(name = "Image")
    private int image;

    @ColumnInfo(name = "SoldBy")
    private String SoldBy;

    @ColumnInfo(name = "=productName")
    private String productName;

    @ColumnInfo(name = "price")
    private double price;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CartItem(){
    }
    public CartItem(int productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    @Ignore
    public CartItem(int id, int productId, int quantity){
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSoldBy() {
        return SoldBy;
    }

    public void setSoldBy(String soldBy) {
        SoldBy = soldBy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
