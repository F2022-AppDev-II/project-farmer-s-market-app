package com.example.farmersmarketapp.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.db.models.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHodler>{
    private CartClickedListeners cartClickedListeners;
    private List<CartItem> cartItemList;

    public CartAdapter(CartClickedListeners cartClickedListeners) {
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new CartViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHodler holder, int position) {
        CartItem productCart = cartItemList.get(position);
        holder.productImageView.setImageResource(productCart.getImage());
        holder.productNameTv.setText(productCart.getProductName());
        holder.productSoldByTv.setText(productCart.getSoldBy());
        holder.productQuantity.setText(productCart.getQuantity() + "");
        holder.productPriceTv.setText(productCart.getLineTotal()+ "");

        holder.deleteProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(productCart);
            }
        });

        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(productCart);
            }
        });
        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(productCart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(cartItemList != null){
            return cartItemList.size();
        }
        return 0;
    }

    public class CartViewHodler extends RecyclerView.ViewHolder {
        private TextView productNameTv, productSoldByTv, productPriceTv, productQuantity;
        private ImageView deleteProductBtn;
        private ImageView productImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHodler(@NonNull View itemView) {
            super(itemView);

            productNameTv = itemView.findViewById(R.id.eachCartItemName);
            productSoldByTv = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            productPriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteProductBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            productImageView = itemView.findViewById(R.id.eachCartItemIV);
            productQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);


        }
    }
    public interface CartClickedListeners {
        void onDeleteClicked(CartItem cartItem);

        void onPlusClicked(CartItem cartItem);

        void onMinusClicked(CartItem cartItem);
    }
}
