import java.util.*;

public class StackB {

  static class Stack {
    static ArrayList<Integer> list = new ArrayList<>();

    public static boolean isEmpty() {
      return (list.size() == 0);
    }

    public static void push(int data) {
      list.add(data);
    }

    public static int pop() {
      int top = list.get(list.size() - 1);
      list.remove(list.size() - 1);
      return top;
    }

    public static int peek() {
      return list.get(list.size() - 1);
    }
  }

  public static void main(String args[]) {

  }
}
