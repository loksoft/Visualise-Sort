package com.visualisesort.visualisesort.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.visualisesort.visualisesort.R;
import com.visualisesort.visualisesort.SortListAdapter;
import com.visualisesort.visualisesort.Repository.SortRepository;
import com.visualisesort.visualisesort.ViewModel.SortViewModel;

import java.util.List;

public class SortActivity extends AppCompatActivity implements View.OnClickListener, Observer<List<Integer>>  {
    private EditText nElementsEdit;
    private RecyclerView elementsList;
    private Button sortButton, generateElementsButton;
    private SortListAdapter sortListAdapter;
    private String pageTitle;
    private List<Integer> list;
    private SortViewModel sortViewModel;
    private TextView successSortText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ActionBar actionBar = getSupportActionBar();
        pageTitle = getIntent().getStringExtra(SortVizMainActivity.PAGE_TITLE);
        if (actionBar != null) {
            actionBar.setTitle(pageTitle);
        }

        nElementsEdit = findViewById(R.id.n_elements);
        elementsList = findViewById(R.id.elements_list);
        generateElementsButton = findViewById(R.id.generate_random_numbers);
        sortButton = findViewById(R.id.button_sort);
        elementsList = findViewById(R.id.elements_list);
        successSortText = findViewById(R.id.success_sort_text);
        sortViewModel = ViewModelProviders.of(this).get(SortViewModel.class);
        Typeface typeface = Typeface.createFromAsset(getAssets(),SortRepository.TYPE_FACE);
        sortButton.setTypeface(typeface);
        nElementsEdit.setTypeface(typeface);
        generateElementsButton.setTypeface(typeface);
        successSortText.setTypeface(typeface);
        successSortText.setTextColor(getResources().getColor(R.color.successTextColor));
        eventHandling();

    }

    private void eventHandling(){
        nElementsEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.length() > 0 &&Integer.parseInt(nElementsEdit.getText().toString()) > 0 && (Integer.parseInt(nElementsEdit.getText().toString()))<= 15){
                    generateElementsButton.setVisibility(View.VISIBLE);
                }else
                {
                    Toast.makeText(SortActivity.this, "Please enter between 1 to 15", Toast.LENGTH_SHORT).show();
                    generateElementsButton.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        sortButton.setOnClickListener(this);
        generateElementsButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
       finish();
    }

    @Override
    public void onClick(View v) {

        if (v == findViewById(R.id.generate_random_numbers))
        {
            int n = Integer.parseInt(nElementsEdit.getText().toString());
            list = SortRepository.getRandomNumbers(n);
            if (list!=null && n >0){
                sortListAdapter = new SortListAdapter(SortActivity.this,list);
                sortButton.setVisibility(View.VISIBLE);
                nElementsEdit.setVisibility(View.INVISIBLE);
                generateElementsButton.setVisibility(View.INVISIBLE);
            }
        }
        else if (v == findViewById(R.id.button_sort))
        {
            generateElementsButton.setVisibility(View.INVISIBLE);
            if (pageTitle.equals(getResources().getString(R.string.heap_sort))){
                int arr[] = new int[list.size()];
                for (int i = 0;i<arr.length;i++)
                    arr[i] = list.get(i);
                SortRepository.invokeHeapSort(arr);
                sortViewModel.getListOfElements().observe(SortActivity.this,SortActivity.this);
                sortListAdapter = new SortListAdapter(SortActivity.this,list);

            }else if (pageTitle.equals(getResources().getString(R.string.bucket_sort))){
                int arr[] = new int[list.size()];
                for (int i = 0; i<arr.length;i++)
                    arr[i] = list.get(i);
                SortRepository.invokeBucketSort(arr);
                sortViewModel.getListOfElements().observe(SortActivity.this,SortActivity.this);
                sortListAdapter = new SortListAdapter(SortActivity.this,list);
            }
            sortButton.setVisibility(View.INVISIBLE);
            successSortText.setVisibility(View.VISIBLE);
            successSortText.setText(getResources().getString(R.string.success)+" " +pageTitle);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(SortActivity.this,LinearLayoutManager.HORIZONTAL,false);
        elementsList.setLayoutManager(layoutManager);
        elementsList.setHasFixedSize(true);
        elementsList.setAdapter(sortListAdapter);
    }
    @Override
    public void onChanged(@Nullable List<Integer> integers) {
        if (integers != null)
            list = integers;
    }
}
