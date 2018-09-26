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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SortActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nElements;
    private RecyclerView elementsList;
    private Button sortElements;
    private SortListAdapter sortListAdapter;
    private String pageTitle;
    private List<Integer> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        ActionBar actionBar = getSupportActionBar();
        pageTitle = getIntent().getStringExtra(SortVizActivity.PAGE_TITLE);
        if (actionBar != null) {
            actionBar.setTitle(pageTitle);
        }

        nElements = findViewById(R.id.n_elements);
        elementsList = findViewById(R.id.elements_list);
        sortElements = findViewById(R.id.button_sort);
        elementsList = findViewById(R.id.elements_list);
        eventHandling();
    }

    private void eventHandling(){
        nElements.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0 && (Integer.parseInt(nElements.getText().toString()))<= 15){
                    sortElements.setVisibility(View.VISIBLE);
                }else
                {
                    Toast.makeText(SortActivity.this, "Please enter between 1 to 15", Toast.LENGTH_SHORT).show();
                    sortElements.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        sortElements.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.button_sort)){
            if (sortElements.getText().equals(getResources().getString(R.string.generate_random_numbers)))
            {
                int n = Integer.parseInt(nElements.getText().toString());
                list = getRandomNumbers(n);
                if (list!=null){
                    sortListAdapter = new SortListAdapter(SortActivity.this,list);
                    sortElements.setText(getResources().getString(R.string.sort));
                }
            }else if (pageTitle.equals("Heap Sort")&&
                    sortElements.getText().equals(getResources().getString(R.string.sort))){
                    int arr[] = new int[list.size()];
                    for (int i = 0;i< arr.length;i++)
                        arr[i] = list.get(i);
                    invokeHeapSort(arr);
                    list = getSortedHeapList(arr);
                    sortListAdapter = new SortListAdapter(SortActivity.this,list);
                    nElements.setVisibility(View.INVISIBLE);
            }else if (pageTitle.equals("Bucket Sort")&&
                    sortElements.getText().equals(getResources().getString(R.string.sort))){
                //TODO implement bucket sort logic
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(SortActivity.this,LinearLayoutManager.HORIZONTAL,false);
            elementsList.setLayoutManager(layoutManager);
            elementsList.setHasFixedSize(true);
            elementsList.setAdapter(sortListAdapter);
        }
    }

    private void invokeHeapSort(int[] arr) {
        int n = arr.length;
        for(int i = n/2-1;i >= 0;i--)
            reArrangeHeap(arr,n,i);
        for (int i = n-1; i>=0; i--){
            int temp  = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            reArrangeHeap(arr,i,0);
        }
    }

    private void reArrangeHeap(int[] arr, int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;
        if (left < n && arr[left] > arr[largest]){
            largest = left;
        }
        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i){
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            reArrangeHeap(arr,n,largest);
        }
    }

    private List<Integer> getSortedHeapList(int arr[]){
        List<Integer> list= new ArrayList<>();
        for (int anArr : arr)
            list.add(anArr);
        return list;
    }

}
