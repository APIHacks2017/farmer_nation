package com.example.sivasankar.farmernation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sivasankar.farmernation.R;
import com.example.sivasankar.farmernation.SubCategoryActivity;
import com.example.sivasankar.farmernation.SubSubCategoryActivity;

import java.util.List;

/**
 * Created by Sivasankar on 6/17/2017.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {


    private Context context;
    private LayoutInflater inflter;
    private List<String> CategoryArray, CategoryName;

    public SubCategoryAdapter(Context context, List<String> CategoryArray, List<String> CategoryName) {
        this.context = context;
        this.CategoryArray = CategoryArray;
        this.CategoryName = CategoryName;
        inflter = LayoutInflater.from(context);
    }

    @Override
    public SubCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflter.inflate(R.layout.category_adapter, parent, false);
        SubCategoryAdapter.ViewHolder viewHolder = new SubCategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubCategoryAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(CategoryName.get(position));
        Glide
                .with(context)
                .load(CategoryArray.get(position))
                .into(holder.imgCategory);

        if (position == 0) {
            System.out.println("mmm CategoryArray.get(position)=" + CategoryArray.get(position));
            holder.relLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, SubSubCategoryActivity.class));
                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return CategoryArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imgCategory;
        private RelativeLayout relLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            imgCategory = (ImageView) itemView.findViewById(R.id.imgCategory);
            relLayout = (RelativeLayout) itemView.findViewById(R.id.relLayout);
        }
    }
}

