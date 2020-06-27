package com.findmore.myshop.adapters;

/**
 * Created by Shivayogi Hiremath on 07,June,2020
 *
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
import com.findmore.myshop.models.CartProducts;
import com.findmore.myshop.models.Products;

import java.util.ArrayList;
import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

         private Activity context;
         private List<CartProducts> products=new ArrayList<>();
         private static AdapterView.OnItemClickListener onItemClickListener;

        public CartItemsAdapter(Activity context, AdapterView.OnItemClickListener onItemClickListener) {
            this.context = context;
            this.onItemClickListener=onItemClickListener;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);
            return new RecyclerViewViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (products.get(position).getProducts()!=null){
                Products product= products.get(position).getProducts();
                String count= String.valueOf(products.get(position).getProduct_quantity());

                RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
                viewHolder.tvCategoryLabel.setText(product.getProductCategoryName());
                viewHolder.tvProductName.setText(product.getProductLabel());

                viewHolder.tvPrice.setText(String.valueOf("AED "+ product.getProductAmount()));
                viewHolder.tvQuantity.setText(String.valueOf(products.get(position).getProduct_quantity()));

                try{
                    if (product.getProductImage() !=null){
                        Glide.with(context)
                                .load(context.getResources().getIdentifier(product.getProductImage(), "drawable", context.getPackageName()))
                                .into(viewHolder.img_product);
                    }else{
             /*
               Glide.with(context)
                            .load(, "drawable", context.getPackageName()))
                            .into(viewHolder.imgView_icon);
                            */
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getItemCount() {
            return products.size();
        }


        public void updateData(List<CartProducts> products) {
            this.products =products;
            notifyDataSetChanged();
        }

    static class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

            ImageView img_product,btnDelete,btnRemove,btnAdd;
            TextView tvCategoryLabel;
            TextView tvProductName;
            TextView tvPrice;
            TextView tvSpecification;
            TextView tvQuantity;

            RecyclerViewViewHolder(@NonNull View itemView) {
                super(itemView);
                img_product = itemView.findViewById(R.id.imgProcuct);
                tvCategoryLabel = itemView.findViewById(R.id.tvCategory);
                tvProductName = itemView.findViewById(R.id.tvProductName);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                tvSpecification = itemView.findViewById(R.id.tvSpecification);
                tvQuantity = itemView.findViewById(R.id.tvCountQuantity);
                btnDelete = itemView.findViewById(R.id.btn_delete);
                btnRemove = itemView.findViewById(R.id.btn_Remove);
                btnAdd = itemView.findViewById(R.id.btn_Add);

                btnDelete.setOnClickListener(this);
                btnRemove.setOnClickListener(this);
                btnAdd.setOnClickListener(this);
            }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }
}
