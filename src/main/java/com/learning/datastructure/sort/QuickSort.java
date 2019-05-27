package com.learning.datastructure.sort;

import org.junit.Test;

/**
 * Created by Administrator on 2019/3/19.
 */
public class QuickSort {


    @Test
    public void ascsortTest() {

        int array[] = {9,2,1,3,8,6,4};
        //ascsort(array,0,array.length-1);
        descsort(array,0,array.length-1);
        for(int i=0;i<array.length;i++) {
            System.out.println(array[i]);
        }
    }

    public void ascsort(int[] array,int low,int high) {
        int start = low;
        int end = high;
        int baseNum = array[low];

        while (start < end) {
            while ((start < end) && (array[end] > baseNum)) {
                end--;
            }
            if(array[end] < baseNum) {
                int temp =array[end];
                array[end] = array[start];
                array[start] = temp;
            }

            while ((start<end)&&(array[start]<baseNum)) {
                start++;
            }
            if(array[start]>baseNum) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }

            if(low < start) {
                ascsort(array,low,start-1);
            }
            if(high > end) {
                ascsort(array,end+1,high);
            }
        }
    }

    public void descsort(int[] array,int low,int high) {
        int start = low;
        int end = high;
        int baseNum = array[low];

        while (start < end) {
            while ((start < end) && (array[end] < baseNum)) {
                end--;
            }
            if(array[end] > baseNum) {
                int temp =array[end];
                array[end] = array[start];
                array[start] = temp;
            }

            while ((start<end)&&(array[start]>baseNum)) {
                start++;
            }
            if(array[start]<baseNum) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }

            if(low < start) {
                descsort(array,low,start-1);
            }
            if(high > end) {
                descsort(array,end+1,high);
            }
        }
    }


    public void ascsort2(int[] array,int low,int high) {
        if(low>=high) {
            return;
        }

        int baseNum = array[low];
        int start = low;
        int end = high;

        while (start<end) {
            while ((start<end) && (baseNum<=array[end])) {
                end--;
            }
            int temp = array[end];
            array[end] = array[start];
            array[start] = temp;

            while ((start<end) && (baseNum>=array[start])) {
                start++;
            }
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
        }

        if(low<start) ascsort(array,low,start-1);
        if(end<high) ascsort(array,end+1,high);
    }
}
