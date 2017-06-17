package com.example.sivasankar.farmernation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sivasankar.farmernation.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> CategoryImage = new ArrayList<>();
    private List<String> CategoryName = new ArrayList<>();
    private RecyclerView recyCategory;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyCategory = (RecyclerView) findViewById(R.id.recyCategory);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyCategory.setLayoutManager(linearLayoutManager);
        CategoryImage.add("http://producetechnology.co.ke/images/footerbanner.png");
        CategoryImage.add("http://www.fruitconcentratevietnam.com/wp-content/uploads/2014/09/banner2.png");
        CategoryImage.add("http://carrollpartners.com.au/images/home_banner.png");

        CategoryName.add("Vegetable");
        CategoryName.add("Fruits");
        CategoryName.add("Nuts");

        CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, CategoryImage, CategoryName);
        recyCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

    }
}
