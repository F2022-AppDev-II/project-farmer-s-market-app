package com.example.farmersmarketapp.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.db.models.CartItem;
import com.example.farmersmarketapp.utils.model.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productSoldBy, productDescription;
    private AppCompatButton addToCartBtn;
    private ProductItem productItem;
    private FarmerViewModel farmerViewModel;
    private List<CartItem> productCartList;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        productItem = getIntent().getParcelableExtra("productItem");

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
                IntertToCart();
            }
        });
    }

    private void IntertToCart() {

        CartItem cartItem =  new CartItem();
        cartItem.setProductId(productItem.getId());
        cartItem.setProductName(productItem.getProductName());
        cartItem.setSoldBy(productItem.getHarvestByFarmer());
        cartItem.setLineTotal(productItem.getPrice());
        cartItem.setImage(productItem.getImage());
        //check if the produclist is not empty
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
        }
        else{
            //if the product is not in the cart, add it to the cart
            cartItem.setQuantity(1);
            farmerViewModel.insertCartItem(cartItem);
        }
//        //toast
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();

    }

    private void setDataToWidgets() {
        productName.setText(productItem.getProductName());
        productSoldBy.setText(productItem.getHarvestByFarmer());
        productPrice.setText(String.valueOf(productItem.getPrice()));
        productImage.setImageResource(productItem.getImage());
    }

    private void initializeVariable() {
        productCartList = new ArrayList<>();

        productImage = findViewById(R.id.detailActivityProductIV);
        productName = findViewById(R.id.detailActivityProductNameTv);
        productSoldBy = findViewById(R.id.detailActivityProductSoldByTv);
        productPrice = findViewById(R.id.detailActivityProductPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);
        productDescription = findViewById(R.id.detailActivityProductDetail);
        farmerViewModel = new ViewModelProvider(this).get(FarmerViewModel.class);
    }
}