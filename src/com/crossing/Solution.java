package com.crossing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Crossing
 * @Date 2020-08-25
 */
public class Solution {
  public int majorityElement(int[] nums) {

    Map<Integer, Integer> map = new HashMap<>();

    for (int num : nums) {
      if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
      } else {
        map.put(num, 1);
      }
    }

    for (int num : nums) {
      Integer value = map.get(num);
      if (value > nums.length / 2) {
        return num;
      }
    }
    return 0;
  }

  public char firstUniqChar(String s) {

    int[] array = new int[26];
    if (s.equals("")) {
      return ' ';
    }

    for (int i = 0; i < s.length(); i++) {
      array[s.charAt(i) - 'a']++;
    }

    for (int i = 0; i < s.length(); i++) {
      if (array[s.charAt(i) - 'a'] == 1) {
        return s.charAt(i);
      }
    }
    return ' ';
  }

  public int[] twoSum(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int curNum;
    while (left < right) {
      curNum = nums[left] + nums[right];
      if (curNum == target) {
        return new int[]{nums[left], nums[right]};
      } else if (curNum > target) {
        right--;
      } else {
        left++;
      }
    }
    return new int[2];
  }

  public int[][] findContinuousSequence(int target) {

    int l = 1, sum = 0;
    List<int[]> list = new ArrayList<>();

    for (int i = 1; i < target; i++) {
      sum += i;
      while (sum > target) {
        sum -= l++;
      }
      if (sum == target) {
        int[] temp = new int[i - l + 1];
        for (int j = l; j < temp.length; j++) {
          temp[j] = l;
        }
        list.add(temp);
      }
    }

    int[][] result = new int[list.size()][];

    for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i);
    }
    return result;
  }

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    Stack<ListNode> stackA = new Stack<>();
    Stack<ListNode> stackB = new Stack<>();

    while (headA != null) {
      stackA.push(headA);
      headA = headA.next;
    }

    while (headB != null) {
      stackB.push(headB);
      headB = headB.next;
    }

    if (stackA.peek().val != stackB.peek().val) {
      return null;
    }
    ListNode temp = null;
    while (!stackA.isEmpty() || !stackB.isEmpty()) {
      temp = stackA.peek();
      if (stackA.pop().val != stackB.pop().val) {
        break;
      }

    }
    return temp;
  }

  public String reverseWords(String s) {
    String[] split = s.split(" ");
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = split.length - 1; i >= 0; i--) {
      if (i == 0) {
        stringBuffer.append(split[i].trim());
      } else {
        stringBuffer.append(split[i].trim()).append(" ");
      }
    }
    return stringBuffer.toString().trim();
  }

}

class ListNode {

  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}
