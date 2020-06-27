package com.findmore.myshop.adapters;

/**
 * Created by Shivayogi Hiremath on 07,June,2020
 *
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.findmore.myshop.R;
import com.findmore.myshop.models.LikeCategories;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

         private Activity context;
         private List<LikeCategories> likeCategories;

        public RecyclerViewAdapter(Activity context) {
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(context).inflate(R.layout.item_category_userlikes,parent,false);
            return new RecyclerViewViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            LikeCategories likeCategory = likeCategories.get(position);
            RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
            viewHolder.tvCategoryLabel.setText(likeCategory.getCategoryLabel());


            try{
                if (likeCategory.getCategoryImage() !=null){
                    Glide.with(context)
                            .load(context.getResources().getIdentifier(likeCategory.getCategoryImage(), "drawable", context.getPackageName()))
                            .into(viewHolder.imgView_icon);
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

        @Override
        public int getItemCount() {
            return likeCategories.size();
        }

        public void updateData(List<LikeCategories> likeCategories) {
            this.likeCategories=likeCategories;
            notifyDataSetChanged();
        }

    static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
            ImageView imgView_icon;
            TextView tvCategoryLabel;

            RecyclerViewViewHolder(@NonNull View itemView) {
                super(itemView);
                imgView_icon = itemView.findViewById(R.id.imgCategory);
                tvCategoryLabel = itemView.findViewById(R.id.tvCategoryLabel);
            }
        }
}
