public class Recursion_basics {

  public static boolean isSorted(int arr[], int i) {
    if (i == arr.length - 1) {
      return true;
    }
    if (arr[i] > arr[i + 1]) {
      return false;
    }
    return isSorted(arr, i + 1);
  }

  public static int fib(int n) {
    if (n == 1 || n == 2) {
      return 1;
    }
    return (fib(n - 1) + fib(n - 2));
  }

  public static int sum(int n) {
    if (n == 1) {
      return 1;
    }
    return (n + sum(n - 1));
  }

  public static int factorial(int n) {
    if (n == 0) {
      return 1;
    }
    return (n * factorial(n - 1));
  }

  public static void printDecreasing(int n) {
    if (n == 1) {
      System.out.print(n + " ");
      return;
    }
    System.out.print(n + " ");
    printDecreasing(n - 1);
  }

  public static void printIncreasing(int n) {
    if (n == 1) {
      System.out.print(n + " ");
      return;
    }
    printIncreasing(n - 1);
    System.out.print(n + " ");
  }

  public static void main(String args[]) {
    int n = 6;
    int arr[]={1,2,9,5,6};
    System.out.println(isSorted(arr, 0));
  }
}
