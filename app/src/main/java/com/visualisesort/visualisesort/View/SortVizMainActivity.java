package com.visualisesort.visualisesort.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.visualisesort.visualisesort.R;
import com.visualisesort.visualisesort.Repository.SortRepository;

public class SortVizMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button heapSort,bucketSort;
    public static final String PAGE_TITLE = "page_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heapSort = findViewById(R.id.button_heap);
        bucketSort = findViewById(R.id.button_bucket);
        TextView startText = findViewById(R.id.start_text);
        Typeface typeface = Typeface.createFromAsset(getAssets(), SortRepository.TYPE_FACE);
        startText.setTypeface(typeface);
        heapSort.setOnClickListener(this);
        heapSort.setTypeface(typeface);
        bucketSort.setOnClickListener(this);
        bucketSort.setTypeface(typeface);
    }

    @Override
    public void onClick(View v) {

        if (v == heapSort){
            Intent heapSortActivity = new Intent(SortVizMainActivity.this,SortActivity.class);
            heapSortActivity.putExtra(PAGE_TITLE,"Heap Sort");
            startActivity(heapSortActivity);
        }else if (v == bucketSort){
            Intent bucketSortActivity = new Intent(SortVizMainActivity.this,SortActivity.class);
            bucketSortActivity.putExtra(PAGE_TITLE,"Bucket Sort");
            startActivity(bucketSortActivity);
        }
    }
}
