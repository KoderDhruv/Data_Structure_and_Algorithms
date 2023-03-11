import java.util.*;
import java.util.LinkedList;

public class Queues {

  static class QueueA {
    static int arr[];
    static int size;
    static int rear;

    QueueA(int n) {
      arr = new int[n];
      size = n;
      rear = -1;
    }

    public boolean isEmpty() {
      return rear == -1;
    }

    public void add(int data) {
      if (rear == size - 1) {
        System.out.println("Queue is full");
        return;
      }
      rear = rear + 1;
      arr[rear] = data;
    }

    public int remove() {
      if (isEmpty()) {
        System.out.println("Empty queue");
        return -1;
      }
      int front = arr[0];
      for (int i = 0; i < rear; i++) {
        arr[i] = arr[i + 1];
      }
      rear = rear - 1;
      return front;
    }

    public int peek() {
      if (isEmpty()) {
        System.out.println("Empty queue");
        return -1;
      }
      return arr[0];
    }
  }

  static class CircularQueueA {
    static int arr[];
    static int size;
    static int rear;
    static int front;

    CircularQueueA(int n) {
      arr = new int[n];
      size = n;
      rear = -1;
      front = -1;
    }

    public boolean isEmpty() {
      return rear == -1 && front == -1;
    }

    public boolean isFull() {
      return (rear + 1) % size == front;
    }

    public void add(int data) {
      if (isFull()) {
        System.out.println("Queue if full");
        return;
      }
      if (front == -1) {
        front = 0;
      }
      rear = (rear + 1) % size;
      arr[rear] = data;
    }

    public int remove() {
      if (isEmpty()) {
        System.out.println("Queue is empty");
        return -1;
      }
      int result = arr[front];
      if (rear == front) {
        rear = front = -1;
      } else {
        front = (front + 1) % size;
      }
      return result;
    }

    public int peek() {
      if (isEmpty()) {
        System.out.println("Queue is empty");
        return -1;
      }
      return arr[front];
    }
  }

  static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
      this.next = null;
    }
  }

  static class QueueLL {
    static Node head = null;
    static Node tail = null;

    public boolean isEmpty() {
      return head == null && tail == null;
    }

    public void add(int data) {
      Node newNode = new Node(data);
      if (head == null) {
        head = tail = newNode;
        return;
      }
      tail.next = newNode;
      tail = newNode;
    }

    public int remove() {
      if (isEmpty()) {
        System.out.println("Empty queue");
        return -1;
      }
      int front = head.data;
      if (tail == head) {
        tail = head = null;
      } else {
        head = head.next;
      }
      return front;
    }

    public int peek() {
      if (isEmpty()) {
        System.out.println("Empty queue");
        return -1;
      }
      return head.data;
    }
  }

  static class Queue2Stack {
    static Stack<Integer> s1 = new Stack<>();
    static Stack<Integer> s2 = new Stack<>();

    public boolean isEmpty() {
      return s1.isEmpty();
    }

    public void add(int data) {
      while (!s1.isEmpty()) {
        s2.push(s1.pop());
      }
      s1.push(data);
      while (!s2.isEmpty()) {
        s1.push(s2.pop());
      }
    }

    public int remove() {
      if (isEmpty()) {
        System.out.println("Queue Empty!");
        return -1;
      }
      return s1.pop();
    }

    public int peek() {
      if (isEmpty()) {
        System.out.println("Queue Empty!");
        return -1;
      }
      return s1.peek();
    }
  }

  static class Stack2Queues {
    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();
  }

  public static void main(String args[]) {
    QueueLL q = new QueueLL();
    q.add(1);
    q.add(2);
    q.add(3);
    System.out.println(q.remove());
    q.add(4);
    System.out.println(q.remove());
    q.add(5);
    while (!q.isEmpty()) {
      System.out.println(q.peek());
      q.remove();
    }
  }
}
