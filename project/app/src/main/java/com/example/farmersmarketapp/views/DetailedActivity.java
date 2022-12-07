package com.example.farmersmarketapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.db.models.CartItem;
import com.example.farmersmarketapp.db.models.Product;
import com.example.farmersmarketapp.utils.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    public static final String PRODUCT_ITEM_KEY = "productItem";

    private ImageView productImage;
    private TextView productName, productPrice, productSoldBy, productDescription, recText;
    private AppCompatButton addToCartBtn;
    private Button recBtn;
    private ProductItem productItem;
    private FarmerViewModel farmerViewModel;
    private List<CartItem> productCartList;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        productItem = getIntent().getParcelableExtra(PRODUCT_ITEM_KEY);

        initializeVariable();

        farmerViewModel.getAllCartItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                productCartList.addAll(cartItems);
            }
        });

        if(productItem != null){
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToCart();
            }
        });
    }

    private void insertToCart() {

        CartItem cartItem =  new CartItem();
        cartItem.setProductId(productItem.getId());
        cartItem.setProductName(productItem.getProductName());
        cartItem.setSoldBy(productItem.getHarvestByFarmer());
        cartItem.setLineTotal(productItem.getPrice());
        cartItem.setImage(productItem.getImage());
        //check if the product list is not empty
        if (productCartList.size() > 0){
            //check if the product is already in the cart
            for (CartItem item : productCartList){
                if (item.getProductId() == productItem.getId()){
                    //if the product is already in the cart, update the quantity
                    item.setQuantity(item.getQuantity() + 1);
                    item.setLineTotal(item.getLineTotal() + productItem.getPrice());
                    farmerViewModel.updateCartItem(item);
                    return;
                }

            }
            cartItem.setQuantity(1);
            farmerViewModel.insertCartItem(cartItem);
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        }
        else{
            //if the product is not in the cart, add it to the cart
            cartItem.setQuantity(1);
            farmerViewModel.insertCartItem(cartItem);
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        }
//        //toast
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();

    }

    private void setDataToWidgets() {
        productName.setText(productItem.getProductName());
        productSoldBy.setText(productItem.getHarvestByFarmer());
        productPrice.setText(String.valueOf(productItem.getPrice()));
        productImage.setImageResource(productItem.getImage());
        productDescription.setText(productItem.getDescription());

        Product product = farmerViewModel.getProductFromCategory(productItem.getCategory(), productItem.getId());
        if (product != null){
            recText.setVisibility(View.VISIBLE);
            recBtn.setVisibility(View.VISIBLE);
            recBtn.setText(product.getProductName());
            Integer image;
            switch (product.getImage()){
                case 0:
                    image = R.drawable.apple;
                    break;
                case 1:
                    image = R.drawable.orange;
                    break;
                case 2:
                    image = R.drawable.carrots;
                    break;
                case 3:
                    image = R.drawable.broccoli;
                    break;
                default:
                    image = null;
                    break;
            }

            ProductItem item = new ProductItem(product, image);
            recBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailedActivity.this, DetailedActivity.class);
                    intent.putExtra(PRODUCT_ITEM_KEY, item);

                    startActivity(intent);
                }
            });
        }
    }

    private void initializeVariable() {
        productCartList = new ArrayList<>();

        productImage = findViewById(R.id.detailActivityProductIV);
        productName = findViewById(R.id.detailActivityProductNameTv);
        productSoldBy = findViewById(R.id.detailActivityProductSoldByTv);
        productPrice = findViewById(R.id.detailActivityProductPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);
        productDescription = findViewById(R.id.detailActivityProductDetail);
        recText = findViewById(R.id.recommended_textview);
        recBtn = findViewById(R.id.recommended_button);
        farmerViewModel = new ViewModelProvider(this).get(FarmerViewModel.class);
    }
}