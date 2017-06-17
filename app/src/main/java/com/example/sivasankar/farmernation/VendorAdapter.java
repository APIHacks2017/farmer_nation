package com.example.sivasankar.farmernation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sivasankar.farmernation.adapter.SubSubCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sivasankar on 6/17/2017.
 */

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.ViewHolder> {


    private Context context;
    private LayoutInflater inflter;

    private List<String> CategoryImage = new ArrayList<>();
    private List<String> CategoryName = new ArrayList<>();
    private List<String> Location = new ArrayList<>();

    public VendorAdapter(Context context) {
        this.context = context;


        inflter = LayoutInflater.from(context);
        CategoryImage.add("Siva");
        CategoryImage.add("Ranjith");
        CategoryImage.add("Muthu");

        CategoryName.add("Farmer");
        CategoryName.add("Dealer");
        CategoryName.add("Farmer");

        Location.add("Thanjavr");
        Location.add("Maurai");
        Location.add("Vilupuram");
    }

    @Override
    public VendorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflter.inflate(R.layout.vendor_adapter, parent, false);
        VendorAdapter.ViewHolder viewHolder = new VendorAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VendorAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(CategoryImage.get(position));
        holder.txtCity.setText(Location.get(position));
        holder.txtType.setText(CategoryName.get(position));
        holder.relLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ProductDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return CategoryImage.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtCity, txtType;

        private LinearLayout relLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtCity = (TextView) itemView.findViewById(R.id.txtName);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            relLayout = (LinearLayout) itemView.findViewById(R.id.relLayout);
        }
    }
}



