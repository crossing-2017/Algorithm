package com.crossing.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Crossing
 * @Date 2020-08-10
 * <p>
 * 插入排序
 */
public class InsertSort {

  public static void main(String[] args) {

    //int[] arr = {101, 34, 119, 1, -1, 89};
    // 创建要给80000个的随机的数组
    int[] arr = new int[80000];
    for (int i = 0; i < 80000; i++) {
      arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
    }

    System.out.println("插入排序前");
    Date beforeSortTime = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String beforeSortTimeStr = simpleDateFormat.format(beforeSortTime);
    System.out.println("排序前的时间是=" + beforeSortTimeStr);

    insertSort(arr); //调用插入排序算法

    Date afterSortTime = new Date();
    String afterSortTimeStr = simpleDateFormat.format(afterSortTime);
    System.out.println("排序后的时间是=" + afterSortTimeStr);

    //System.out.println(Arrays.toString(arr));
  }

  //插入排序
  public static void insertSort(int[] arr) {

    for (int i = 1; i < arr.length; i++) {  // 从数组的第二个元素开始循环将数组中的元素插入
      int current = arr[i];  // 设置数组中的第2个元素为第一次循环要插入的数据
      int j = i - 1;
      while (j >= 0 && current < arr[j]) {
        arr[j + 1] = arr[j];  // 如果要插入的元素小于第j个元素,就将第j个元素向后移动
        j--;
      }
      arr[j + 1] = current;  // 直到要插入的元素不小于第j个元素,将insertNote插入到数组中
    }
  }
}
