import java.util.*;

public class Stack {

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
  }
}
