package com.example.farmersmarketapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.enums.ProductCategory;
import com.example.farmersmarketapp.utils.adapter.ProductItemAdapter;
import com.example.farmersmarketapp.utils.model.ProductItem;
import com.example.farmersmarketapp.views.DetailedActivity;
import com.example.farmersmarketapp.views.SettingsActivity;
import com.example.farmersmarketapp.views.ShoppingCartActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ProductItemAdapter.ProductClickedListeners {

    private RecyclerView recyclerView;
    private List<ProductItem> productItems;
    private ProductItemAdapter productAdapter;
    private FarmerViewModel cartViewModel;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        cartViewModel = new ViewModelProvider(this).get(FarmerViewModel.class);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        initializeVariable();
        setUpList();
        productAdapter.setProductItems(productItems);
        productAdapter.setAdminModeSetting(sharedPreferences.getBoolean(SettingsActivity.ADMIN_MODE, false));
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        productAdapter.setAdminModeSetting(sharedPreferences.getBoolean(SettingsActivity.ADMIN_MODE, false));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.settingsOption:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT);
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.shopping_cart:
                Intent intent1 = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent1);
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
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
        productItems.add(new ProductItem("Apple", "Bob", "apple",ProductCategory.FRUIT.ordinal(), R.drawable.apple, 1.99));
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

        //Before starting activity save the item clicked



        startActivity(intent);
    }

    private void saveLastProduct(ProductItem item){
        SharedPreferences sharedPref = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //Editor
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Key and values stored
        //Currently using the list, convert to use db later
        editor.putInt("recent", item.getId());

    }

    @Override
    public void onAddToCartBtnClicked(ProductItem productItem) {
        //Add item to cart DB
//        CartItemRepository cartDb = Room.databaseBuilder(getApplicationContext(), CartItemDao.class)
    }
}