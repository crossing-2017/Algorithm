package com.crossing.tree;

/**
 * @author Crossing
 * @Date 2020-08-24
 */
public class BinaryTreeDemo {

}

//定义BinaryTree 二叉树
class BinaryTree {

  private HeroNode root;

  public void setRoot(HeroNode root) {
    this.root = root;
  }
}

//先创建HeroNode 结点
class HeroNode {

  private int no;
  private String name;
  private HeroNode left; //默认null
  private HeroNode right; //默认null

  public HeroNode(int no, String name) {
    this.no = no;
    this.name = name;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HeroNode getLeft() {
    return left;
  }

  public void setLeft(HeroNode left) {
    this.left = left;
  }

  public HeroNode getRight() {
    return right;
  }

  public void setRight(HeroNode right) {
    this.right = right;
  }

  @Override
  public String toString() {
    return "HeroNode [no=" + no + ", name=" + name + "]";
  }

  //编写前序遍历的方法
  public void preOrder() {
    System.out.println(this); //先输出父结点
    //递归向左子树前序遍历
    if (this.left != null) {
      this.left.preOrder();
    }
    //递归向右子树前序遍历
    if (this.right != null) {
      this.right.preOrder();
    }
  }

  //中序遍历
  public void infixOrder() {

    //递归向左子树中序遍历
    if (this.left != null) {
      this.left.infixOrder();
    }
    //输出父结点
    System.out.println(this);
    //递归向右子树中序遍历
    if (this.right != null) {
      this.right.infixOrder();
    }
  }

  //后序遍历
  public void postOrder() {
    if (this.left != null) {
      this.left.postOrder();
    }
    if (this.right != null) {
      this.right.postOrder();
    }
    System.out.println(this);
  }
}
