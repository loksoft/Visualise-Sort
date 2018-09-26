package com.visualisesort.visualisesort.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.visualisesort.visualisesort.Repository.SortRepository;

import java.util.List;

public class SortViewModel extends AndroidViewModel {

    private MutableLiveData<List<Integer>> elements;
    public SortViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Integer>> getListOfElements(){
        if (elements == null){
            elements = new MutableLiveData<>();
            loadElements();
        }
        return elements;
    }

    private void loadElements(){
        List<Integer> list = SortRepository.getSortedHeapList();
        elements.setValue(list);
    }
}
