package com.crm.utils;
import java.util.Arrays;
/**
 @author blz
**/

public class QuickSort {

    public static void main(String[] args) {
    	
    	System.out.println(Math.ceil(5.0));
    	//Arrays.sort(null, null);
 /*
        // 性能测试 对比快速排序和Arrays.sort()
        long startTime;
        long endTime;

        int[] a1 = new int[20000];
        int[] a2 = new int[20000];

        for(int i=0;i<a1.length;i++){
            int random=((int)(Math.random()*50000));
            a1[i]=random;
            a2[i]=random;
        }
        System.out.println("排序前的a1：");
        System.out.println(Arrays.toString(a1));
        startTime=System.currentTimeMillis();
        quickSort(a1, 0, a1.length-1);
        endTime=System.currentTimeMillis();

        System.out.println("排序后的a1：");
        System.out.println(Arrays.toString(a1));
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        //----------------------------------------------------------

        System.out.println("排序前的a2：");
        System.out.println(Arrays.toString(a2));
        startTime = System.currentTimeMillis();
        Arrays.sort(a2);
        endTime=System.currentTimeMillis();

        System.out.println("排序后的a2：");
        System.out.println(Arrays.toString(a2));
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");*/


/*        int[] test = {3, 7, 2, 9, 1, 4, 6, 8, 10, 5};
        quickSort(test, 0, test.length-1);
        System.out.println(Arrays.toString(test));*/


    }
    // 每次排序都需要待排序数列，左指针坐标和右指针坐标
    public static int divide(int[] a, int start, int end){
        // 每次都以最右边的元素作为基准值
        int base = a[end];
        // start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮遍历。
        while(start < end){
            while(start < end && a[start] <= base)
                // 从左边开始遍历，如果比基准值小，就继续向右走，直到找到比基准值大的元素
                start++;
            // 上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换，放到基准值右边
            if(start < end){
                //交换
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                // 交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while(start < end && a[end] >= base)
                // 从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            // 上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换，放到基准值左边
            if(start < end){
                // 交换
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                // 交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        // 这里返回start或者end皆可，此时的start和end都为基准值所在的位置
        return end;
    }

    public static void quickSort(int[] a, int start, int end){
        if(start > end){
            //如果只有一个元素，就不用再排下去了
            return;
        }
        else{
            //如果不止一个元素，继续划分两边递归排序下去
            int partition = divide(a, start, end);
            quickSort(a, start, partition-1);
            quickSort(a, partition+1, end);
        }

    }


}