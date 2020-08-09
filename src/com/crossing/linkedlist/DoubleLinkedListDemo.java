package com.crossing.linkedlist;

/**
 * @author Crossing
 * @Date 2020-07-27
 * <p>
 * 双向链表Demo
 */
public class DoubleLinkedListDemo {

  public static void main(String[] args) {

    // 测试
    System.out.println("双向链表的测试");
    // 先创建节点
    DoubleNode hero1 = new DoubleNode(1, "宋江", "及时雨");
    DoubleNode hero2 = new DoubleNode(2, "卢俊义", "玉麒麟");
    DoubleNode hero3 = new DoubleNode(3, "吴用", "智多星");
    DoubleNode hero4 = new DoubleNode(4, "林冲", "豹子头");
    // 创建一个双向链表
    DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
    doubleLinkedList.add(hero1);
    doubleLinkedList.add(hero2);
    doubleLinkedList.add(hero3);
    doubleLinkedList.add(hero4);

    doubleLinkedList.list();

    // 修改
    DoubleNode newNode = new DoubleNode(4, "公孙胜", "入云龙");
    doubleLinkedList.update(newNode);
    System.out.println("修改后的链表情况");
    doubleLinkedList.list();

    // 删除
    DoubleNode res = doubleLinkedList.del(3);
    System.out.println("删除后的链表情况~~");
    doubleLinkedList.list();
    System.out.println("---------------");
    System.out.println(res);
  }

}

/**
 * 创建一个双向链表的类
 */
class DoubleLinkedList {

  // 先初始化一个头节点, 头节点不要动, 不存放具体的数据
  private DoubleNode head = new DoubleNode(0, "", "");

  // 返回头节点
  public DoubleNode getHead() {
    return head;
  }

  // 遍历双向链表的方法
  // 显示链表[遍历]
  public void list() {

    //判断链表是否为空
    if (head.next == null) {
      System.out.println("链表为空");
      return;
    }

    //因为头节点，不能动，因此我们需要一个辅助变量来遍历
    DoubleNode temp = head.next;
    while (temp != null) {
      //输出节点的信息
      System.out.println(temp);
      //将temp后移， 一定小心
      temp = temp.next;
    }
  }

  // 添加一个节点到双向链表的最后.
  public void add(DoubleNode node) {
    //因为head节点不能动，因此我们需要一个辅助遍历 temp
    DoubleNode temp = head;

    //找到链表的最后
    while (temp.next != null) {
      //如果没有找到最后, 将将temp后移
      temp = temp.next;
    }

    // 当退出while循环时，temp就指向了链表的最后
    // 形成一个双向链表
    temp.next = node;
    node.pre = temp;
  }

  // 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
  // 只是 节点类型改成 DoubleNode
  public void update(DoubleNode node) {
    //判断是否空
    if (head.next == null) {
      System.out.println("链表为空~");
      return;
    }
    //找到需要修改的节点, 根据no编号
    //定义一个辅助变量
    DoubleNode temp = head.next;
    boolean flag = false; //表示是否找到该节点

    while (temp != null) {
      if (temp.no == node.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }

    //根据flag 判断是否找到要修改的节点
    if (flag) {
      temp.name = node.name;
      temp.nickName = node.nickName;
    } else {  //没有找到
      System.out.printf("没有找到 编号 %d 的节点，不能修改\n", node.no);
    }
  }

  // 从双向链表中删除一个节点,
  // 说明
  // 1 对于双向链表，我们可以直接找到要删除的这个节点
  // 2 找到后，自我删除即可
  public DoubleNode del(int no) {

    DoubleNode result = null;

    // 判断当前链表是否为空
    if (head.next == null) {// 空链表
      System.out.println("链表为空，无法删除");
      return null;
    }

    DoubleNode temp = head.next;
    boolean flag = false; // 标志是否找到待删除节点的

    while (temp != null) {
      if (temp.no == no) {
        //找到的待删除节点的前一个节点temp
        flag = true;
        break;
      }
      temp = temp.next; //temp后移，遍历
    }

    if (flag) {
      result = temp;
      temp.pre.next = temp.next;
      // 这里我们的代码有问题?
      // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
      if (temp.next != null) {
        temp.next.pre = temp.pre;
      }
    } else {
      System.out.printf("要删除的 %d 节点不存在\n", no);
    }
    return result;
  }
}

/**
 * 定义DoubleNode，每个DoubleNode对象就是一个节点
 */
class DoubleNode {

  public int no;
  public String name;
  public String nickName;
  public DoubleNode next; // 指向下一个节点, 默认为null
  public DoubleNode pre; // 指向前一个节点, 默认为null

  //构造器
  public DoubleNode(int no, String name, String nickName) {
    this.no = no;
    this.name = name;
    this.nickName = nickName;
  }

  // 为了显示方法，我们重新toString
  @Override
  public String toString() {
    return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickName + "]";
  }
}
