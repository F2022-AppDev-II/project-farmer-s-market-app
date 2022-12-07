package com.example.farmersmarketapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.db.FarmerViewModel;

public class ShoppingCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FarmerViewModel farmerViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
//    private CardView cardView;
//    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
    }
}