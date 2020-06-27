package com.findmore.myshop.adapters;

/**
 * Created by Shivayogi Hiremath on 07,June,2020
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.findmore.myshop.R;
import com.findmore.myshop.models.Products;

import java.util.List;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.RecyclerViewViewHolder> {

    private Activity context;
    private List<Products> productsList;

    private AdapterView.OnItemClickListener onItemClickListener;

    public ProductsListAdapter(Activity context, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.items_products, parent, false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        Products products = productsList.get(position);
        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.tvCategoryLabel.setText(products.getProductCategoryName());
        viewHolder.productName.setText(products.getProductLabel());
        if (products.getProductAmount() >0){
            viewHolder.productPrice.setText(products.getProductAmount()+" SAR");
            viewHolder.productPrice.setVisibility(View.VISIBLE);
        }else{
            viewHolder.productPrice.setVisibility(View.GONE);
        }

        if (products.getProductDiscountOffer() !=null && !products.getProductDiscountOffer().isEmpty()){
            viewHolder.productDiscount.setVisibility(View.VISIBLE);
            viewHolder.productDiscount.setText(products.getProductDiscountOffer());
        }else{
            viewHolder.productDiscount.setVisibility(View.GONE);
        }

        try {
            if (products.getProductImage() != null) {
                Glide.with(context)
                        .load(context.getResources().getIdentifier(products.getProductImage(), "drawable", context.getPackageName()))
                        .into(viewHolder.imgView_icon);
            } else {
             /*
               Glide.with(context)
                            .load(, "drawable", context.getPackageName()))
                            .into(viewHolder.imgView_icon);
                            */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void updateData(List<Products> likeCategories) {
        this.productsList = likeCategories;
        notifyDataSetChanged();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgView_icon;
        ImageView imgView_Cart;
        ImageView imgView_Like;
        TextView tvCategoryLabel;
        TextView productName;
        TextView productPrice;
        TextView productDiscount;


        RecyclerViewViewHolder(@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            imgView_icon = itemView.findViewById(R.id.imgProduct);
            tvCategoryLabel = itemView.findViewById(R.id.tvCategory);
            productName = itemView.findViewById(R.id.tvProductName);
            productPrice = itemView.findViewById(R.id.tvProductPrice);
            productDiscount = itemView.findViewById(R.id.tvOffer);
            imgView_Cart = itemView.findViewById(R.id.imgCart);
            imgView_Like = itemView.findViewById(R.id.imgLikes);
            imgView_Cart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }
}
