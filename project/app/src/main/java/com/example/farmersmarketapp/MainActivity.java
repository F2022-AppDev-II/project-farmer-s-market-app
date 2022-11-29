package com.example.farmersmarketapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.utils.adapter.ProductItemAdapter;
import com.example.farmersmarketapp.utils.model.ProductItem;
import com.example.farmersmarketapp.views.DetailedActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ProductItemAdapter.ProductClickedListeners {

    private RecyclerView recyclerView;
    private List<ProductItem> productItems;
    private ProductItemAdapter productAdapter;
    private FarmerViewModel cartViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartViewModel = new ViewModelProvider(this).get(FarmerViewModel.class);


        initializeVariable();
        setUpList();
        productAdapter.setProductItems(productItems);
        recyclerView.setAdapter(productAdapter);
    }

    private  void setUpList(){
        productItems = new ArrayList<>();
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Nike", "apple","fruit", R.drawable.apple, 1.99));





    }
    private void initializeVariable() {
        productItems =new ArrayList<>();

        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductItemAdapter( this);



    }

    @Override
    public void onCardClicked(ProductItem productItem) {
        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("productItem", productItem);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(ProductItem productItem) {

    }
}