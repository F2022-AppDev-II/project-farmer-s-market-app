package com.example.farmersmarketapp;

import static com.example.farmersmarketapp.MainActivity.EXTRA_UPDATE_PRODUCT_REQUEST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.TimedText;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.farmersmarketapp.db.models.Product;
import com.example.farmersmarketapp.enums.ImageType;
import com.example.farmersmarketapp.enums.ProductCategory;
import com.example.farmersmarketapp.utils.model.ProductItem;

import java.text.DecimalFormat;

public class UpdateProductActivity extends AppCompatActivity {

    public static final String EXTRA_UPDATE_PRODUCT_ID = "update_product";

    private ProductItem productItem;
    private int position;
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
        position = getIntent().getIntExtra("position", -1);

        initializeVariable();
        if (productItem != null){
            setDataToWidgets();
        }

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(productName.getText())
                    || TextUtils.isEmpty(harvestedByFarmer.getText())
                    || TextUtils.isEmpty(description.getText())
                    || TextUtils.isEmpty(price.getText())
                    || category.getCheckedRadioButtonId() == -1
                    || image.getCheckedRadioButtonId() == -1){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    Product newProduct = new Product(
                            productItem.getId(),
                            productName.getText().toString(),
                            description.getText().toString(),
                            harvestedByFarmer.getText().toString(),
                            getProductCategory().ordinal(),
                            getImageType().ordinal(),
                            Double.parseDouble(price.getText().toString())
                    );
                    ProductItem item = new ProductItem(newProduct, newProduct.getImage());
                    replyIntent.putExtra(EXTRA_UPDATE_PRODUCT_ID, EXTRA_UPDATE_PRODUCT_REQUEST);
                    replyIntent.putExtra("productItemResult", item);
                    replyIntent.putExtra("positionResult", position);
                    replyIntent.putExtra("product_id", getIntent().getIntExtra("id", 0));
                    replyIntent.putExtra("product_name", newProduct.getProductName());
                    replyIntent.putExtra("product_harv", newProduct.getHarvestByFarmer());
                    replyIntent.putExtra("product_desc", newProduct.getDescription());
                    replyIntent.putExtra("product_category", newProduct.getCategory());
                    replyIntent.putExtra("product_image", newProduct.getImage());
                    replyIntent.putExtra("product_price", newProduct.getPrice());
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    private ProductCategory getProductCategory(){
        switch (category.getCheckedRadioButtonId()){
            case R.id.update_category_fruit:
                return ProductCategory.FRUIT;
            case R.id.update_category_vegetable:
                return ProductCategory.VEGETABLE;
            default:
                return null;
        }
    }

    private ImageType getImageType(){
        switch (image.getCheckedRadioButtonId()){
            case R.id.update_image_apple:
                return ImageType.Apple;
            case R.id.update_image_orange:
                return ImageType.Orange;
            case R.id.update_image_carrot:
                return ImageType.Carrot;
            case R.id.update_image_broccoli:
                return ImageType.Broccoli;
            default:
                return null;
        }
    }

    private void setDataToWidgets(){
        productName.setText(productItem.getProductName());
        harvestedByFarmer.setText(productItem.getHarvestByFarmer());
        description.setText(productItem.getDescription());
        price.setText(String.format("%.2f", productItem.getPrice()));
        switch (productItem.getCategory()){
            case 0:
                category.check(R.id.update_category_fruit);
                break;
            case 1:
                category.check(R.id.update_category_vegetable);
                break;
        }
        // Uses the images from the Parcelable, not the product itself.
        switch (productItem.getImage()){
            case R.drawable.apple:
                image.check(R.id.update_image_apple);
                break;
            case R.drawable.orange:
                image.check(R.id.update_image_orange);
                break;
            case R.drawable.carrots:
                image.check(R.id.update_image_carrot);
                break;
            case R.drawable.broccoli:
                image.check(R.id.update_image_broccoli);
                break;
            default:
                image.clearCheck();
                break;
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