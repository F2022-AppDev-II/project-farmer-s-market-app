package com.example.farmersmarketapp;

import static com.example.farmersmarketapp.views.UpdateProductActivity.EXTRA_UPDATE_PRODUCT_ID;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import androidx.preference.PreferenceManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.db.models.CartItem;
import com.example.farmersmarketapp.db.models.Product;
import com.example.farmersmarketapp.enums.ProductCategory;
import com.example.farmersmarketapp.utils.adapter.ProductItemAdapter;
import com.example.farmersmarketapp.utils.model.ProductItem;
import com.example.farmersmarketapp.views.DetailedActivity;
import com.example.farmersmarketapp.views.SettingsActivity;
import com.example.farmersmarketapp.views.ShoppingCartActivity;
import com.example.farmersmarketapp.views.UpdateProductActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ProductItemAdapter.ProductClickedListeners {

    public static final int EXTRA_UPDATE_PRODUCT_REQUEST = 1337;

    private ConstraintLayout layout;
    private RecyclerView recyclerView;
    private List<ProductItem> productItems;
    private ProductItemAdapter productAdapter;
    private FarmerViewModel farmerViewModel;
    private List<CartItem> productCartList;
    private RadioGroup categoryFilter;

    private SharedPreferences sharedPreferences;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        farmerViewModel = new ViewModelProvider(this).get(FarmerViewModel.class);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        initializeVariable();
        setBackgroundColorBySeason();
        categoryFilter.check(R.id.filter_none);

        farmerViewModel.getAllCartItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                productCartList.addAll(cartItems);
            }
        });

        productAdapter.setAdminModeSetting(sharedPreferences.getBoolean(SettingsActivity.ADMIN_MODE, false));
        recyclerView.setAdapter(productAdapter);


        farmerViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setUpList(products);
                productAdapter.setProductItems(productItems);
            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            // Update ProductItem
                            if (result.getData().getExtras().getInt(EXTRA_UPDATE_PRODUCT_ID) == EXTRA_UPDATE_PRODUCT_REQUEST){
                                ProductItem item = result.getData().getParcelableExtra("productItemResult");
                                int position = result.getData().getIntExtra("positionResult", -1);
                                Product newProduct = new Product(
                                        result.getData().getIntExtra("product_id", -1),
                                        result.getData().getStringExtra("product_name"),
                                        result.getData().getStringExtra("product_desc"),
                                        result.getData().getStringExtra("product_harv"),
                                        result.getData().getIntExtra("product_category", -1),
                                        result.getData().getIntExtra("product_image", -1),
                                        result.getData().getDoubleExtra("product_price", 0.00)
                                );
                                farmerViewModel.updateProduct(newProduct);
                                Integer image = getImageResourceFromImageType(newProduct);
                                productAdapter.updateProductItem(newProduct, position, image);
                            }
                        }
                    }
                }
        );

        categoryFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    // No filter
                    case R.id.filter_none:
                        productAdapter.unfilterList();
                        break;
                    // Filter list with Fruit
                    case R.id.filter_fruit:
                        productAdapter.filterListByCategory(ProductCategory.FRUIT);
                        break;
                    // Filter list with Vegetable
                    case R.id.filter_vegetable:
                        productAdapter.filterListByCategory(ProductCategory.VEGETABLE);
                        break;
                }
            }
        });
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


    private void setUpList(List<Product> products){
        productItems = new ArrayList<>();
        Integer image = null;

        for (Product product : products) {
            image = getImageResourceFromImageType(product);
            productItems.add(new ProductItem(product, image));
        }
    }

    private Integer getImageResourceFromImageType(Product product){
        switch (product.getImage()){
            case 0:
                return R.drawable.apple;
            case 1:
                return R.drawable.orange;
            case 2:
                return R.drawable.carrots;
            case 3:
                return R.drawable.broccoli;
            default:
                return null;
        }
    }

    private void initializeVariable() {
        productCartList = new ArrayList<>();
        farmerViewModel = new ViewModelProvider(this).get(FarmerViewModel.class);
        productItems =new ArrayList<>();

        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductItemAdapter( this, getApplicationContext());
        layout = findViewById(R.id.constraintLayout);
        categoryFilter = findViewById(R.id.filter_group);
    }

    @Override
    public void onCardClicked(ProductItem productItem) {
        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra(DetailedActivity.PRODUCT_ITEM_KEY, productItem);

        //Before starting activity save the item clicked



        startActivity(intent);
    }

    private void setBackgroundColorBySeason(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);

        if (month == Calendar.DECEMBER
            || month == Calendar.JANUARY
            || month == Calendar.FEBRUARY){
            layout.setBackgroundColor(getResources().getColor(R.color.winter));
        }
        else if (month == Calendar.MARCH
                || month == Calendar.APRIL
                || month == Calendar.MAY){
            layout.setBackgroundColor(getResources().getColor(R.color.spring));
        }
        else if (month == Calendar.JUNE
                || month == Calendar.JULY
                || month == Calendar.AUGUST){
            layout.setBackgroundColor(getResources().getColor(R.color.summer));
        }
        else if (month == Calendar.SEPTEMBER
                || month == Calendar.OCTOBER
                || month == Calendar.NOVEMBER){
            layout.setBackgroundColor(getResources().getColor(R.color.fall));
        }
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
//
        CartItem cartItem =  new CartItem();
        cartItem.setProductId(productItem.getId());
        cartItem.setProductName(productItem.getProductName());
        cartItem.setSoldBy(productItem.getHarvestByFarmer());
        cartItem.setPrice(productItem.getPrice());
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
        //toast
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpdateProductItem(ProductItem productItem, int position) {
        Intent intent = new Intent(MainActivity.this, UpdateProductActivity.class);
        intent.putExtra("productItem", productItem);
        intent.putExtra("position", position);
        intent.putExtra("id", productItem.getProduct().getId());
        activityResultLauncher.launch(intent);
    }

    @Override
    public void onDeleteProductItem(ProductItem productItem) {
        farmerViewModel.deleteProduct(productItem.getProduct());
    }
}