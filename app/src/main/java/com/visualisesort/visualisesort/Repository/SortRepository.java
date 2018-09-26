package com.visualisesort.visualisesort.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortRepository {

    public static final String TYPE_FACE = "fonts/Interstate Light Regular.otf";
    private static int[] elementsArr;

    public static void invokeHeapSort(int[] arr) {
        elementsArr = arr;
        int n = elementsArr.length;
        for(int i = n/2-1;i >= 0;i--)
            reArrangeHeap(elementsArr,n,i);
        for (int i = n-1; i>=0; i--){
            int temp  = arr[0];
            elementsArr[0] = elementsArr[i];
            elementsArr[i] = temp;
            reArrangeHeap(elementsArr,i,0);
        }
    }

    private static void reArrangeHeap(int[] arr, int n, int i) {
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

    public static void invokeBucketSort(int[] arr) {
        elementsArr = arr;
        int i,j;
        int[] bucket = new int[elementsArr.length+1];
        Arrays.fill(bucket,0);
        for (i = 0;i<arr.length;i++){
            bucket[elementsArr[i]]++;
        }
        int k=0;
        for (i =0;i<bucket.length;i++){
            for (j =0 ;j<bucket[i];j++){
                arr[k++] = i;
            }
        }
    }
    public static List<Integer> getRandomNumbers(int n) {

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

    public static List<Integer> getSortedHeapList(){
        List<Integer> list= new ArrayList<>();
        if (elementsArr != null)
        {
            for (int anArr : elementsArr)
                list.add(anArr);

        }
        return list;
    }


}
