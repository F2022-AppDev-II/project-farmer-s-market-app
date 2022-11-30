package com.example.farmersmarketapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.utils.adapter.ProductItemAdapter;
import com.example.farmersmarketapp.utils.model.ProductItem;
import com.example.farmersmarketapp.views.DetailedActivity;
import com.example.farmersmarketapp.views.SettingsActivity;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Toast.makeText(this, "Settings not working", Toast.LENGTH_SHORT);

        switch (item.getItemId()){
            case R.id.settingsOption:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT);
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:

                return false;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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