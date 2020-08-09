package com.crossing.linkedlist;

import java.util.Stack;

/**
 * @author Crossing
 * @Date 2020-07-27
 * <p>
 * 单向链表Demo
 */
public class SingleLinkedListDemo {

  public static void main(String[] args) {

    //进行测试
    //先创建节点
    SingleNode hero1 = new SingleNode(1, "宋江", "及时雨");
    SingleNode hero2 = new SingleNode(2, "卢俊义", "玉麒麟");
    SingleNode hero3 = new SingleNode(3, "吴用", "智多星");
    SingleNode hero4 = new SingleNode(4, "林冲", "豹子头");

    //创建要给链表
    SingleLinkedList singleLinkedList = new SingleLinkedList();

    //加入
    singleLinkedList.addSingleNode(hero1);
    singleLinkedList.addSingleNode(hero4);
    singleLinkedList.addSingleNode(hero2);
    singleLinkedList.addSingleNode(hero3);

    // 测试一下单链表的反转功能
    System.out.println("原来链表的情况~~");
    singleLinkedList.list();

    System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
    reversePrint(singleLinkedList.getHead());

//    System.out.println("反转单链表~~");
//    reverseList(singleLinkedList.getHead());
//    singleLinkedList.list();

/*    //加入按照编号的顺序
    singleLinkedList.addSingleNodeByOrder(hero1);
    singleLinkedList.addSingleNodeByOrder(hero4);
    singleLinkedList.addSingleNodeByOrder(hero2);
    singleLinkedList.addSingleNodeByOrder(hero3);

    //显示一把
    singleLinkedList.list();


    //测试修改节点的代码
    SingleNode SingleNode = new SingleNode(2, "小卢", "玉麒麟~~");
    singleLinkedList.updateSingleNode(SingleNode);

    System.out.println("修改后的链表情况~~");
    singleLinkedList.list();

    //删除一个节点
    SingleNode SingleNode1 = singleLinkedList.delSingleNode(1);
    SingleNode SingleNode4 = singleLinkedList.delSingleNode(4);
    System.out.println("删除后的链表情况~~");
    singleLinkedList.list();
    System.out.println("---------------");
    System.out.println(SingleNode1);
    System.out.println(SingleNode4);

    //测试一下 求单链表中有效节点的个数
    System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));//2


    //测试一下看看是否得到了倒数第K个节点
    SingleNode res = findLastIndexSingleNode(singleLinkedList.getHead(), 3);
    System.out.println("res=" + res);*/

  }

  //方式2：
  //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
  public static void reversePrint(SingleNode head) {

    if (head.next == null) {
      return;//空链表，不能打印
    }
    //创建要给一个栈，将各个节点压入栈
    Stack<SingleNode> stack = new Stack<>();

    SingleNode temp = head.next;
    //将链表的所有节点压入栈
    while (temp != null) {
      stack.push(temp);
      temp = temp.next; //cur后移，这样就可以压入下一个节点
    }

    //将栈中的节点进行打印,pop 出栈
    while (stack.size() > 0) {
      System.out.println(stack.pop()); //stack的特点是先进后出
    }

  }

  //将单链表反转
  public static void reverseList(SingleNode head) {

    //如果当前链表为空，或者只有一个节点，无需反转，直接返回
    if (head.next == null || head.next.next == null) {
      return;
    }

    SingleNode reverseHead = new SingleNode(-1, "", "");

    //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
    SingleNode temp = head.next;

    SingleNode next; // 指向当前节点[cur]的下一个节点

    //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
    //动脑筋
    while (temp != null) {
      next = temp.next; //先暂时保存当前节点的下一个节点，因为后面需要使用
      temp.next = reverseHead.next; //将cur的下一个节点指向新的链表的最前端
      reverseHead.next = temp;  //将cur 连接到新的链表上
      temp = next;  //让cur后移
    }
    //将head.next 指向 reverseHead.next , 实现单链表的反转
    head.next = reverseHead.next;
  }


  //查找单链表中的倒数第k个结点 【新浪面试题】
  //思路
  //1. 编写一个方法，接收head节点，同时接收一个index
  //2. index 表示是倒数第index个节点
  //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
  //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
  //5. 如果找到了，则返回该节点，否则返回null
  public static SingleNode findLastIndexSingleNode(SingleNode head, int index) {
    //判断如果链表为空，返回null
    if (head.next == null) {
      return null;//没有找到
    }
    //第一个遍历得到链表的长度(节点个数)
    int size = getLength(head);
    //第二次遍历  size-index 位置，就是我们倒数的第K个节点
    //先做一个index的校验
    if (index <= 0 || index > size) {
      return null;
    }

    //定义给辅助变量， for 循环定位到倒数的index
    SingleNode cur = head.next; //3 // 3 - 1 = 2
    for (int i = 0; i < size - index; i++) {
      cur = cur.next;
    }
    return cur;
  }

  /**
   * 获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
   *
   * @param head 链表的头节点
   * @return 返回的就是有效节点的个数
   */
  public static int getLength(SingleNode head) {
    if (head.next == null) { //空链表
      return 0;
    }
    int length = 0;
    //定义一个辅助的变量, 这里我们没有统计头节点
    SingleNode cur = head.next;
    while (cur != null) {
      length++;
      cur = cur.next; //遍历
    }
    return length;
  }


}

/**
 * 定义SingleLinkedList 管理我们的英雄
 */
class SingleLinkedList {

  //先初始化一个头节点, 头节点不要动, 不存放具体的数据
  private SingleNode head = new SingleNode(-1, "", "");

  //返回头节点
  public SingleNode getHead() {
    return head;
  }

  //添加节点到单向链表
  //思路，当不考虑编号顺序时
  //1. 找到当前链表的最后节点
  //2. 将最后这个节点的next 指向 新的节点
  public void addSingleNode(SingleNode singleNode) {

    //因为head节点不能动，因此我们需要一个辅助遍历 temp
    SingleNode temp = head;

    //找到链表的最后
    while (temp.next != null) {
      //如果没有找到最后, 将将temp后移
      temp = temp.next;
    }

    //当退出while循环时，temp就指向了链表的最后
    //将最后这个节点的next 指向 新的节点
    temp.next = singleNode;
  }

  //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
  //(如果有这个排名，则添加失败，并给出提示)
  public void addSingleNodeByOrder(SingleNode singleNode) {
    //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
    //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
    SingleNode temp = head;
    boolean flag = false; // flag标志添加的编号是否存在，默认为false

    while (temp.next != null) {
      if (temp.next.no == singleNode.no) {
        flag = true; //说明编号存在
        break;
      } else if (temp.next.no > singleNode.no) {
        //位置找到，就在temp的后面插入
        break;
      }
      temp = temp.next;
    }

    //判断flag 的值
    if (flag) {
      //不能添加，说明编号存在
      System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", singleNode.no);
    } else {
      //插入到链表中, temp的后面
      singleNode.next = temp.next;
      temp.next = singleNode;
    }
  }

  //修改节点的信息, 根据no编号来修改，即no编号不能改.
  //说明
  //1. 根据 newHeroSingleNode 的 no 来修改即可
  public void updateSingleNode(SingleNode singleNode) {
    //判断是否空
    if (head.next == null) {
      System.out.println("链表为空~");
      return;
    }
    //找到需要修改的节点, 根据no编号
    //定义一个辅助变量
    SingleNode temp = head.next;
    boolean flag = false; //表示是否找到该节点

    while (temp != null) {
      if (temp.no == singleNode.no) {
        flag = true;
        break;
      }
      temp = temp.next;
    }

    //根据flag 判断是否找到要修改的节点
    if (flag) {
      temp.name = singleNode.name;
      temp.nickName = singleNode.nickName;
    } else {  //没有找到
      System.out.printf("没有找到 编号 %d 的节点，不能修改\n", singleNode.no);
    }
  }

  //删除节点
  //思路
  //1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
  //2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
  public SingleNode delSingleNode(int no) {
    SingleNode result = null;
    SingleNode temp = head;
    boolean flag = false; // 标志是否找到待删除节点的

    while (temp.next != null) {
      if (temp.next.no == no) {
        //找到的待删除节点的前一个节点temp
        flag = true;
        break;
      }
      temp = temp.next; //temp后移，遍历
    }

    if (flag) {
      result = temp.next;
      temp.next = temp.next.next;
    } else {
      System.out.printf("要删除的 %d 节点不存在\n", no);
    }
    return result;
  }

  //显示链表[遍历]
  public void list() {
    //判断链表是否为空
    if (head.next == null) {
      System.out.println("链表为空");
      return;
    }

    //因为头节点，不能动，因此我们需要一个辅助变量来遍历
    SingleNode temp = head.next;
    while (temp != null) {
      //输出节点的信息
      System.out.println(temp);
      //将temp后移， 一定小心
      temp = temp.next;
    }
  }
}

/**
 * 定义SingleNode ， 每个SingleNode 对象就是一个节点
 */
class SingleNode {

  public int no;
  public String name;
  public String nickName;
  public SingleNode next;  //指向下一个节点

  //构造器
  public SingleNode(int no, String name, String nickName) {
    this.no = no;
    this.name = name;
    this.nickName = nickName;
  }

  //为了显示方法，我们重新toString
  @Override
  public String toString() {
    return "SingleNode [no=" + no + ", name=" + name + ", nickname=" + nickName + "]";
  }

}
