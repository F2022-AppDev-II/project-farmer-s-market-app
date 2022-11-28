package com.example.farmersmarketapp.views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.utils.model.ProductItem;

public class DetailedActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productSoldBy, productDescription;
    private CardView productCardView;
    private AppCompatButton addToCartBtn;
    private ProductItem productItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        productItem = getIntent().getParcelableExtra("productItem");
        initializeVariable();
        if(productItem != null){
            setDataToWidgets();
        }
    }

    private void setDataToWidgets() {
        productName.setText(productItem.getProductName());
        productSoldBy.setText(productItem.getHarvestByFarmer());
        productPrice.setText(String.valueOf(productItem.getPrice()));
        productImage.setImageResource(productItem.getImage());
    }

    private void initializeVariable() {
        productImage = findViewById(R.id.detailActivityProductIV);
        productName = findViewById(R.id.detailActivityProductNameTv);
        productSoldBy = findViewById(R.id.detailActivityProductSoldByTv);
        productPrice = findViewById(R.id.detailActivityProductPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);
        productDescription = findViewById(R.id.detailActivityProductDetail);
    }
}