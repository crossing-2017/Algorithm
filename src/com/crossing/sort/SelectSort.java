package com.crossing.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Crossing
 * @Date 2020-08-10
 * <p>
 * 选择排序
 */
public class SelectSort {

  public static void main(String[] args) {

//    int [] arr = {101, 34, 119, 1, -1, 90, 123};

    System.out.println("排序前");
//    System.out.println(Arrays.toString(arr));

    //创建要给80000个的随机的数组
    int[] arr = new int[80000];
    for (int i = 0; i < 80000; i++) {
      arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
    }

    Date beforeSortTime = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String beforeSortTimeStr = simpleDateFormat.format(beforeSortTime);
    System.out.println("排序前的时间是=" + beforeSortTimeStr);

    selectSort(arr);

    Date afterSortTime = new Date();
    String afterSortTimeStr = simpleDateFormat.format(afterSortTime);
    System.out.println("排序后的时间是=" + afterSortTimeStr);

//    System.out.println("排序后");
//    System.out.println(Arrays.toString(arr));
  }

  //选择排序
  public static void selectSort(int[] arr) {

    //在推导的过程，我们发现了规律，因此，可以使用for来解决
    //选择排序时间复杂度是 O(n^2)

    for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      int min = arr[i];
      for (int j = i + 1; j < arr.length; j++) {
        if (min > arr[j]) { // 说明假定的最小值，并不是最小
          min = arr[j]; // 重置min
          minIndex = j; // 重置minIndex
        }
      }
      // 将最小值，放在arr[0], 即交换
      if (minIndex != i) {
        arr[minIndex] = arr[i];
        arr[i] = min;
      }
    }
  }
}
