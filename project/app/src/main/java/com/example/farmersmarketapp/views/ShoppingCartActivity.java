package com.example.farmersmarketapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.db.models.CartItem;
import com.example.farmersmarketapp.db.models.Product;
import com.example.farmersmarketapp.utils.adapter.CartAdapter;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {
    private RecyclerView recyclerView;
    private FarmerViewModel farmerViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        initializeVariables();


        farmerViewModel.getAllCartItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                double price = 0;
                cartAdapter.setCartItemList(cartItems);
                for (int i=0;i<cartItems.size();i++){
                    price = price + cartItems.get(i).getLineTotal();
                }
                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                farmerViewModel.deleteAllCartItems();
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPriceTv.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeVariables() {

        cartAdapter = new CartAdapter(this);
        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        farmerViewModel = new ViewModelProvider(this).get(FarmerViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);

    }

    @Override
    public void onDeleteClicked(CartItem cartItem) {
        farmerViewModel.deleteCartItem(cartItem);
    }

    @Override
    public void onPlusClicked(CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setLineTotal(cartItem.getLineTotal() + cartItem.getPrice());
        farmerViewModel.updateCartItem(cartItem);
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(CartItem cartItem) {
        int quantity = cartItem.getQuantity() - 1;
        if (quantity != 0){
            cartItem.setLineTotal(cartItem.getLineTotal() - cartItem.getPrice());
            cartItem.setQuantity(quantity);
            farmerViewModel.updateCartItem(cartItem);
            cartAdapter.notifyDataSetChanged();
        }else{
            farmerViewModel.deleteCartItem(cartItem);
        }
    }
}