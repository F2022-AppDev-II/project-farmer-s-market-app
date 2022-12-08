package com.example.farmersmarketapp.utils.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmersmarketapp.MainActivity;
import com.example.farmersmarketapp.R;
import com.example.farmersmarketapp.db.models.Product;
import com.example.farmersmarketapp.utils.model.ProductItem;
import com.example.farmersmarketapp.views.DetailedActivity;

import java.util.List;

public class ViewedItemAdapter extends RecyclerView.Adapter<ViewedItemAdapter.ViewedItemHolder> {

   private List<ProductItem> productItemList;

   private ViewedClickedListener listener;
   private Context ctx;
   private List<ProductItem> fullList;


   public ViewedItemAdapter(ViewedClickedListener listeners, Context ctx){
       this.listener = listeners;
       this.ctx = ctx;
   }
   public void setProductItemList(List<ProductItem> productItems){
       this.productItemList = productItems;
       this.fullList = productItems;
       notifyDataSetChanged();
   }


    @NonNull
    @Override
    public ViewedItemAdapter.ViewedItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewed_items, parent, false);
       return new ViewedItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewedItemAdapter.ViewedItemHolder holder, int position) {

        ProductItem product = productItemList.get(position);

        holder.productImageView.setImageResource(product.getImage());
        holder.productNameTv.setText(product.getProductName());
        holder.productSoldByTv.setText(product.getHarvestByFarmer());
        holder.productPriceTv.setText(String.valueOf(product.getPrice()));


        holder.productCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCardClicked(product);
            }
        });


    }


    @Override
    public int getItemCount() {

        if (productItemList != null){
            return productItemList.size();
        }

        return 0;
    }

    public class ViewedItemHolder extends RecyclerView.ViewHolder{

        private TextView productNameTv, productSoldByTv, productPriceTv, productQuantity;
        private ImageView deleteProductBtn;
        private ImageView productImageView;
        private CardView productCardView;

        public ViewedItemHolder(@NonNull View itemView) {
            super(itemView);

            productNameTv = itemView.findViewById(R.id.viewedName);
            productSoldByTv = itemView.findViewById(R.id.viewedBrand);
            productPriceTv = itemView.findViewById(R.id.viewedPrice);
            productImageView = itemView.findViewById(R.id.viewedImage);

            productCardView = itemView.findViewById(R.id.viewItemCard);
        }
    }
    public interface ViewedClickedListener{

       void onCardClicked(ProductItem productItem);
    }


}
