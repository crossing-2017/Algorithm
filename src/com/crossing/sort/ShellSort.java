package com.crossing.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Crossing
 * @Date 2020-08-10
 * <p>
 * 希尔排序
 */
public class ShellSort {

  public static void main(String[] args) {

//    int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

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

//    shellSort(arr); //交换式
    shellSort2(arr);//移位方式

    Date afterSortTime = new Date();
    String afterSortTimeStr = simpleDateFormat.format(afterSortTime);
    System.out.println("排序后的时间是=" + afterSortTimeStr);

//    System.out.println("排序后");
//    System.out.println(Arrays.toString(arr));

  }

  // 使用逐步推导的方式来编写希尔排序
  // 希尔排序时， 对有序序列在插入时采用交换法,
  // 思路(算法) ===> 代码
  public static void shellSort(int[] arr) {

    // 根据前面的逐步分析，使用循环处理
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      int temp;
      for (int i = gap; i < arr.length; i++) {
        // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
        for (int j = i - gap; j >= 0; j -= gap) {
          // 如果当前元素大于加上步长后的那个元素，说明交换
          if (arr[j] > arr[j + gap]) {
            temp = arr[j];
            arr[j] = arr[j + gap];
            arr[j + gap] = temp;
          }
        }
      }
    }
  }

  //对交换式的希尔排序进行优化->移位法
  public static void shellSort2(int[] arr) {

    // 增量gap, 并逐步的缩小增量
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      int i, j, insertNode;
      // 从第gap个元素，逐个对其所在的组进行直接插入排序
      for (i = gap; i < arr.length; i++) {
        insertNode = arr[i];
        j = i - gap;
        while (j >= 0 && insertNode < arr[j]) {
          //移动
          arr[j + gap] = arr[j];
          j -= gap;
        }
        //当退出while后，就给temp找到插入的位置
        arr[j + gap] = insertNode;
      }
    }
  }
}
