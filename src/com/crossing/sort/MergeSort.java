package com.crossing.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Crossing
 * @Date 2020-08-10
 * <p>
 * 归并排序
 */
public class MergeSort {

  public static void main(String[] args) {
//    int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};

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

    mergeSort(arr, 0, arr.length - 1);  //递归排序
//    nonRecMergeSort(arr);  //非递归排序

    Date afterSortTime = new Date();
    String afterSortTimeStr = simpleDateFormat.format(afterSortTime);
    System.out.println("排序后的时间是=" + afterSortTimeStr);

//    System.out.println("排序后");
//    System.out.println(Arrays.toString(arr));
  }

  //递归排序
  public static void mergeSort(int[] arr, int low, int high) {
    //使用递归的方式进行归并排序，所需要的空间复杂度是O（N+logN）
    int mid = (low + high) / 2;
    if (low < high) {
      //递归地对左右两边进行排序
      mergeSort(arr, low, mid);
      mergeSort(arr, mid + 1, high);
      //合并
      merge(arr, low, mid, high);
    }
  }

  // 非递归排序
  public static void nonRecMergeSort(int[] arr) {
    //使用非递归的方式来实现归并排序
    int len = arr.length;
    int k = 1;

    while (k < len) {
      mergePass(arr, k, len);
      k *= 2;
    }
  }

  //MergePass方法负责将数组中的相邻的有k个元素的字序列进行归并
  private static void mergePass(int[] arr, int k, int n) {
    int i = 0;

    //从前往后,将2个长度为k的子序列合并为1个
    while (i < n - 2 * k + 1) {
      merge(arr, i, i + k - 1, i + 2 * k - 1);
      i += 2 * k;
    }

    //这段代码保证了，将那些“落单的”长度不足两两merge的部分和前面merge起来。
    if (i < n - k) {
      merge(arr, i, i + k - 1, n - 1);
    }
  }

  /**
   * merge函数实际上是将两个有序数组合并成一个有序数组，因为数组有序，合并很简单，只要维护几个指针就可以了
   *
   * @param arr  排序的原始数组
   * @param low  左边有序序列的初始索引
   * @param mid  中间索引
   * @param high 右边索引
   */
  public static void merge(int[] arr, int low, int mid, int high) {

    //temp数组用于暂存合并的结果
    int[] temp = new int[high - low + 1];
    //左半边的指针
    int i = low;
    //右半边的指针
    int j = mid + 1;
    //合并后数组的指针
    int k = 0;

    //将记录由小到大地放进temp数组
    for (; i <= mid && j <= high; k++) {
      if (arr[i] < arr[j]) {
        temp[k] = arr[i++];
      } else {
        temp[k] = arr[j++];
      }
    }

    //接下来两个while循环是为了将剩余的（比另一边多出来的个数）放到temp数组中
    while (i <= mid) {
      temp[k++] = arr[i++];
    }

    while (j <= high) {
      temp[k++] = arr[j++];
    }

    //将temp数组中的元素写入到待排数组中
    if (temp.length >= 0) {
      System.arraycopy(temp, 0, arr, low, temp.length);
    }
  }
}
