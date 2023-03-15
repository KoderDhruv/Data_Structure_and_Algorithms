// Learning Stacks

import java.util.*;

public class stack {

  public static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

  public static class StackLL {
    static Node head = null;

    public boolean isEmpty() {
      return (head == null);
    }

    public void push(int data) {
      Node newNode = new Node(data);
      if (isEmpty()) {
        head = newNode;
        return;
      }
      newNode.next = head;
      head = newNode;
    }
    
    public int pop() {
      if (isEmpty()) {
        return -1;
      }
      int top = head.data;
      head = head.next;
      return top;
    }

    public int peek() {
      if (isEmpty()) {
        return -1;
      }
      return head.data;
    }
  }

  public static class StackAL {
    static ArrayList<Integer> list = new ArrayList<>();

    public boolean isEmpty() {
      return (list.size() == 0);
    }

    public void push(int data) {
      list.add(data);
    }

    public int pop() {
      if (isEmpty()) {
        return -1;
      }
      int top = list.get(list.size() - 1);
      list.remove(list.size() - 1);
      return top;
    }

    public int peek() {
      return list.get(list.size() - 1);
    }
  }

  public static void printStack(Stack<Integer> s) {
    while (!s.isEmpty()) {
      System.out.println(s.pop());
    }
  }

  public static void pushAtBottom(Stack<Integer> s, int data) {
    if (s.isEmpty()) {
      s.push(data);
      return;
    }
    int top = s.pop();
    pushAtBottom(s, data);
    s.push(top);
  }

  public static String reverseString(String str) {
    Stack<Character> s = new Stack<>();
    for (int idx = 0; idx < str.length(); idx++) {
      s.push(str.charAt(idx));
    }
    StringBuilder result = new StringBuilder("");
    while (!s.isEmpty()) {
      char curr = s.pop();
      result.append(curr);
    }
    return result.toString();
  }

  public static void reverseStack(Stack<Integer> s) {
    if (s.isEmpty()) {
      return;
    }
    int top = s.pop();
    reverseStack(s);
    pushAtBottom(s, top);
  }

  public static void stockSpan(int stocks[], int span[]) {
    Stack<Integer> s = new Stack<>();
    span[0] = 1;
    s.push(0);
    for (int i = 0; i < stocks.length; i++) {
      while (!s.isEmpty() && stocks[s.peek()] <= stocks[i]) {
        s.pop();
      }
      if (s.isEmpty()) {
        span[i] = i + 1;
      } else {
        span[i] = i - s.peek();
      }
      s.push(i);
    }
    for (int i = 0; i < span.length; i++) {
      System.out.print(span[i] + " ");
    }
  }

  public static void nextGreater(int arr[], int next[]) {
    Stack<Integer> s = new Stack<>();
    for (int i = (arr.length - 1); i >= 0; i--) {
      while (!s.isEmpty() && arr[s.peek()] <= arr[i]) {
        s.pop();
      }
      if (s.isEmpty()) {
        next[i] = -1;
      } else {
        next[i] = arr[s.peek()];
      }
      s.push(i);
    }
    for (int i = 0; i < next.length; i++) {
      System.out.print(next[i] + " ");
    }
  }

  public static boolean validParentheses(String str) {
    Stack<Character> s = new Stack<>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch == '(' || ch == '[' || ch == '{') {
        s.push(ch);
      } else {
        if (s.isEmpty()) {
          return false;
        }
        if ((s.peek() == '(' && ch == ')') ||
            (s.peek() == '[' && ch == ']') ||
            (s.peek() == '{' && ch == '}')) {
          s.pop();
        } else {
          return false;
        }
      }
    }
    if (s.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean duplicateParantheses(String str) {
    Stack<Character> s = new Stack<>();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (ch == ')') {
        int count = 0;
        while (s.pop() != '(') {
          count++;
        }
        if (count < 1) {
          return true;
        }
      } else {
        s.push(ch);
      }
    }
    return false;
  }

  public static int maxArea(int arr[]) {
    int maxArea = 0;
    int nsr[] = new int[arr.length];
    int nsl[] = new int[arr.length];
    Stack<Integer> s1 = new Stack<>();
    for (int i = (arr.length - 1); i >= 0; i--) {
      while (!s1.isEmpty() && arr[s1.peek()] >= arr[i]) {
        s1.pop();
      }
      if (s1.isEmpty()) {
        nsr[i] = arr.length;
      } else {
        nsr[i] = s1.peek();
      }
      s1.push(i);
    }
    Stack<Integer> s2 = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      while (!s2.isEmpty() && arr[s2.peek()] >= arr[i]) {
        s2.pop();
      }
      if (s2.isEmpty()) {
        nsl[i] = -1;
      } else {
        nsl[i] = s2.peek();
      }
      s2.push(i);
    }
    for (int i = 0; i < arr.length; i++) {
      maxArea = Math.max(maxArea, (arr[i] * (nsr[i] - nsl[i] - 1)));
    }
    return maxArea;
  }

  public static void main(String args[]) {
    StackLL s = new StackLL();
    // Stack<Integer> s = new Stack<>();
    s.push(1);
    s.push(2);
    s.push(3);
    while (!s.isEmpty()) {
      System.out.println(s.peek());
      s.pop();
    }

    // Stock span
    // int stocks[] = { 100, 80, 60, 70, 60, 85, 100 };
    // int span[] = new int[stocks.length];
    // stockSpan(stocks, span);

    // Next Greater
    // int arr[] = { 6, 8, 0, 1, 3 };
    // int next[] = new int[arr.length];
    // nextGreater(arr, next);

    // Valid Parentheses
    // String str = "({})[](";
    // System.out.println(validParentheses(str));

    // Duplicate Parentheses
    String str = "(((a+b)+(c+d)))";
    System.out.println(duplicateParantheses(str));

    // Maximum Area in Histogram
    int arr[] = { 2, 1, 5, 6, 2, 3 };
    System.out.println(maxArea(arr));
  }
}
