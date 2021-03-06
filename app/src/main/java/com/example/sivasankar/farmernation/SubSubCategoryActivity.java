package com.example.sivasankar.farmernation;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.example.sivasankar.farmernation.adapter.SubCategoryAdapter;
import com.example.sivasankar.farmernation.adapter.SubSubCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubSubCategoryActivity extends AppCompatActivity {

    private List<String> CategoryImage = new ArrayList<>();
    private List<String> CategoryName = new ArrayList<>();
    private RecyclerView recyCategory;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_category);
        recyCategory = (RecyclerView) findViewById(R.id.recyCategory);
       /* linearLayoutManager = new LinearLayoutManager(SubSubCategoryActivity.this);
        recyCategory.setLayoutManager(linearLayoutManager);*/

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyCategory.setLayoutManager(mLayoutManager);
        recyCategory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyCategory.setItemAnimator(new DefaultItemAnimator());


        CategoryImage.add("http://imagespng.com/Data/DownloadLogo/mango_PNG9186%20(1).png");
        CategoryImage.add("http://pngimg.com/uploads/mango/mango_PNG9163.png");
        CategoryImage.add("http://www.pngall.com/wp-content/uploads/2016/04/Mango-PNG-Image.png");

        CategoryName.add("Alphonso");
        CategoryName.add("Bangalora");
        CategoryName.add("Banganapalli");

        SubSubCategoryAdapter categoryAdapter = new SubSubCategoryAdapter(SubSubCategoryActivity.this, CategoryImage, CategoryName);
        recyCategory.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
