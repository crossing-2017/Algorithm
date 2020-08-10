package com.crossing.sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author Crossing
 * @Date 2020-08-10
 * <p>
 * 快速排序
 */
public class QuickSort {

  public static void main(String[] args) {
//    int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};

    System.out.println("排序前");
//    System.out.println(Arrays.toString(arr));

    //创建要给80000个的随机的数组
    int[] arr = new int[80000];
    for (int i = 0; i < 80000; i++) {
      arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
    }

    Date beforeSortTime = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String beforeSortTimeStr = simpleDateFormat.format(beforeSortTime);
    System.out.println("排序前的时间是=" + beforeSortTimeStr);

    quickSort(arr, 0, arr.length - 1);  //递归排序
//    nonRecQuickSort(arr, 0, arr.length - 1);  //非递归排序

    Date afterSortTime = new Date();
    String afterSortTimeStr = simpleDateFormat.format(afterSortTime);
    System.out.println("排序后的时间是=" + afterSortTimeStr);

//    System.out.println("排序后");
//    System.out.println(Arrays.toString(arr));
  }

  //非递归排序
  private static void nonRecQuickSort(int[] a, int start, int end) {
    LinkedList<Integer> stack = new LinkedList<>(); // 用栈模拟
    if (start < end) {
      stack.push(end);
      stack.push(start);
      while (!stack.isEmpty()) {
        int l = stack.pop();
        int r = stack.pop();
        int index = partition(a, l, r);
        if (l < index - 1) {
          stack.push(index - 1);
          stack.push(l);
        }
        if (r > index + 1) {
          stack.push(r);
          stack.push(index + 1);
        }
      }
    }
  }

  //递归排序
  private static void quickSort(int[] arr, int start, int end) {
    int index;
    if (start < end) {
      index = partition(arr, start, end);
      quickSort(arr, 0, index - 1);
      quickSort(arr, index + 1, end);
    }
  }

  private static int partition(int[] arr, int start, int end) {
    int pivot = arr[start];
    while (start < end) {
      while (start < end && arr[end] >= pivot) {
        end--;
      }
      arr[start] = arr[end];
      while (start < end && arr[start] <= pivot) {
        start++;
      }
      arr[end] = arr[start];
    }
    arr[start] = pivot;
    return start;
  }
}
