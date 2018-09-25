package com.visualisesort.visualisesort;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class SortActivity extends AppCompatActivity {
    private EditText nElements;
    private RecyclerView elementsList;
    private Button sortElements;
    private SortListAdapter sortListAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ActionBar actionBar = getSupportActionBar();
        String pageTitle = getIntent().getStringExtra(SortVizActivity.PAGE_TITLE);
        if (actionBar != null) {
            actionBar.setTitle(pageTitle);
        }

        nElements = findViewById(R.id.n_elements);
        elementsList = findViewById(R.id.elements_list);
        sortElements = findViewById(R.id.button_sort);
        elementsList = findViewById(R.id.elements_list);
        progressBar = findViewById(R.id.progress_bar);
        eventHandling();
    }

    private void eventHandling(){
        nElements.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0){
                    sortElements.setVisibility(View.VISIBLE);
                }else
                {
                    sortElements.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        sortElements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(nElements.getText().toString());
                List<Integer> list = getRandomNumbers(n);
                if (list!=null){
                     progressBar.setVisibility(View.VISIBLE);
                     sortListAdapter = new SortListAdapter(SortActivity.this,list);
                     elementsList.setLayoutManager(new LinearLayoutManager(SortActivity.this));
                     elementsList.setHasFixedSize(true);
                     elementsList.setAdapter(sortListAdapter);
                     progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private List<Integer> getRandomNumbers(int n) {

        List<Integer> list = new ArrayList<>();
        for (int i =0 ;i < n;){
            int rand = ((int)(Math.random() * n));
            if (!list.contains(rand)){
                list.add(rand);
                i++;
            }
        }
        return list;
    }

    @Override
    public void onBackPressed() {
       finish();
    }
}
