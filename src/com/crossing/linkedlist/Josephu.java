package com.crossing.linkedlist;

/**
 * @author Crossing
 * @Date 2020-07-27
 * <p>
 * 约瑟夫环实现
 */
public class Josephu {

  public static void main(String[] args) {

    // 测试一把看看构建环形链表，和遍历是否ok
    CircleSingleLinkedList list = new CircleSingleLinkedList();
    list.addBoy(5); // 加入5个小孩节点
    list.showBoy();

    //测试一把小孩出圈是否正确
    list.countBoy(1, 2, 5); // 2->4->1->5->3

  }

}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {

  // 创建一个first节点,当前没有编号
  private Boy first = null;

  // 添加小孩节点，构建成一个环形的链表
  public void addBoy(int nums) {
    // nums 做一个数据校验
    if (nums < 1) {
      System.out.println("nums的值不正确");
      return;
    }

    Boy cur = null;
    for (int i = 1; i <= nums; i++) {

      // 根据编号，创建小孩节点
      Boy boy = new Boy(i);
      // 如果是第一个小孩
      if (i == 1) {
        first = boy;
        boy.setNext(first); // 构成环
        cur = first;  // 让curBoy指向第一个小孩
      } else {
        cur.setNext(boy);
        boy.setNext(first);
        cur = boy;
      }
    }
  }

  // 遍历当前的环形链表
  public void showBoy() {
    // 判断链表是否为空
    if (first == null) {
      System.out.println("没有任何小孩~~");
      return;
    }
    // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
    Boy cur = first;
    while (true) {
      System.out.printf("小孩的编号 %d \n", cur.getNo());
      if (cur.getNext().equals(first)) {  // 说明已经遍历完毕
        break;
      }
      cur = cur.getNext();  // curBoy后移
    }
  }

  /**
   * 根据用户的输入，计算出小孩出圈的顺序
   *
   * @param startNo  表示从第几个小孩开始数数
   * @param countNum 表示数几下
   * @param nums     表示最初有多少小孩在圈中
   */
  public void countBoy(int startNo, int countNum, int nums) {

    // 先对数据进行校验
    if (first == null || startNo < 1 || startNo > nums) {
      System.out.println("参数输入有误， 请重新输入");
      return;
    }
    // 创建要给辅助指针,帮助完成小孩出圈
    Boy helper = first;

    // 需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
    while (helper.getNext() != first) {  //出循环说明helper.next = first
      helper = helper.getNext();
    }

    //小孩报数前，先让first和helper移动 k - 1次
    for (int i = 0; i < startNo - 1; i++) {
      first = first.getNext();
      helper = helper.getNext();
    }

    //当小孩报数时，让first和helper指针同时的移动m - 1次, 然后出圈
    //这里是一个循环操作，知道圈中只有一个节点
    while (helper != first) {  //出循环说明圈中只有一个节点

      //让 first 和 helper 指针同时 的移动 countNum - 1
      for (int j = 0; j < countNum - 1; j++) {
        first = first.getNext();
        helper = helper.getNext();
      }

      //这时first指向的节点，就是要出圈的小孩节点
      System.out.printf("小孩%d出圈\n", first.getNo());

      //这时将first指向的小孩节点出圈
      first = first.getNext();
      helper.setNext(first);
    }

    System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());

  }

}

/**
 * 创建一个Boy类，表示一个节点
 */
class Boy {

  private int no;// 编号
  private Boy next; // 指向下一个节点,默认null

  public Boy(int no) {
    this.no = no;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Boy getNext() {
    return next;
  }

  public void setNext(Boy next) {
    this.next = next;
  }

}
