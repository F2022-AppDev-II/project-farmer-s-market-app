package com.example.farmersmarketapp.utils.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.db.FarmerViewModel;
import com.example.farmersmarketapp.db.models.Product;
import com.example.farmersmarketapp.utils.model.ProductItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder> {

    private static boolean ADMIN_MODE;

    private List<ProductItem> productItemsList;
    private ProductClickedListeners productClickedListeners;
    private FarmerViewModel viewModel;
    private Context ctx;

    public ProductItemAdapter(ProductClickedListeners productClickedListeners, Context ctx){
        this.productClickedListeners = productClickedListeners;
        this.viewModel = viewModel;
        this.ctx = ctx;
    }

    public void setProductItems(List<ProductItem> productItemsList) {
        this.productItemsList = productItemsList;
    }

    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_product,parent,false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {

        ProductItem productItem = productItemsList.get(position);
        holder.productImage.setImageResource(productItem.getImage());
        holder.productName.setText(productItem.getProductName());
        holder.productPrice.setText(String.valueOf(productItem.getPrice()));
        holder.productSoldBy.setText(productItem.getHarvestByFarmer());

        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickedListeners.onCardClicked(productItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickedListeners.onAddToCartBtnClicked(productItem);
            }
        });

        if (ADMIN_MODE){
            holder.adminSettings.setVisibility(View.VISIBLE);
        }
        else {
            holder.adminSettings.setVisibility(View.GONE);
        }

        holder.adminSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(ctx, holder.adminSettings);
                popupMenu.inflate(R.menu.admin_options);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.admin_update:
                                productClickedListeners.onUpdateProductItem(productItem);
                                return true;
                            case R.id.admin_delete:
                                productClickedListeners.onDeleteProductItem(productItem);
                                notifyDataSetChanged();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

    public void setAdminModeSetting(boolean isAdmin){
        ADMIN_MODE = isAdmin;
    }

    @Override
    public int getItemCount() {
        if(productItemsList != null){
            return productItemsList.size();
        }
        return 0;
    }

    public class ProductItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage, addToCartBtn, adminSettings;
        private TextView productName, productPrice, productSoldBy;
        private CardView productCardView;
        public ProductItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.eachProductIv);
            addToCartBtn = itemView.findViewById(R.id.eachProductAddToCartBtn);
            productName = itemView.findViewById(R.id.eachProductName);
            productPrice = itemView.findViewById(R.id.eachProductPriceTv);
            productSoldBy = itemView.findViewById(R.id.eachSoldByTv);
            productCardView = itemView.findViewById(R.id.eachShoeCardView);
            adminSettings = itemView.findViewById(R.id.eachProductAdminSettings);
        }
    }

    public interface ProductClickedListeners {
        void onCardClicked(ProductItem productItem);

        void onAddToCartBtnClicked(ProductItem productItem);

        void onUpdateProductItem(ProductItem productItem);
        void onDeleteProductItem(ProductItem productItem);
    }
}
