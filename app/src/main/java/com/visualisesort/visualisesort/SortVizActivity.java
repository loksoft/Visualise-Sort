package com.visualisesort.visualisesort;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SortVizActivity extends AppCompatActivity implements View.OnClickListener {

    private Button heapSort,bucketSort;
    public static final String PAGE_TITLE = "page_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heapSort = findViewById(R.id.button_heap);
        bucketSort = findViewById(R.id.button_bucket);
        heapSort.setOnClickListener(this);
        bucketSort.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == heapSort){
            Intent heapSortActivity = new Intent(SortVizActivity.this,SortActivity.class);
            heapSortActivity.putExtra(PAGE_TITLE,"Heap Sort");
            startActivity(heapSortActivity);
        }else if (v == bucketSort){
            Intent bucketSortActivity = new Intent(SortVizActivity.this,SortActivity.class);
            bucketSortActivity.putExtra(PAGE_TITLE,"Bucket Sort");
            startActivity(bucketSortActivity);
        }
    }
}
