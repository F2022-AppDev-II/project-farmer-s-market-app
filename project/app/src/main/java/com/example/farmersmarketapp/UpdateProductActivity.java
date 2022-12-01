package com.example.farmersmarketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.farmersmarketapp.utils.model.ProductItem;

public class UpdateProductActivity extends AppCompatActivity {

    private ProductItem productItem;
    private EditText productName;
    private EditText harvestedByFarmer;
    private EditText description;
    private EditText price;
    private RadioGroup category, image;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        productItem = getIntent().getParcelableExtra("productItem");
        initializeVariable();
        if (productItem != null){
            setDataToWidgets();
        }
    }

    private void setDataToWidgets(){
        productName.setText(productItem.getProductName());
        harvestedByFarmer.setText(productItem.getHarvestByFarmer());
        description.setText(productItem.getDescription());
        price.setText(String.format("#d", productItem.getPrice()));
        switch (productItem.getProduct().getCategory()){
            
        }
    }

    private void initializeVariable(){
        productName = findViewById(R.id.update_productName_input);
        harvestedByFarmer = findViewById(R.id.update_harvestFarmer_input);
        description = findViewById(R.id.update_description_input);
        price = findViewById(R.id.update_price_input);
        category = findViewById(R.id.update_category_input);
        image = findViewById(R.id.update_image_input);
        confirmBtn = findViewById(R.id.update_confirm_button);
    }
}