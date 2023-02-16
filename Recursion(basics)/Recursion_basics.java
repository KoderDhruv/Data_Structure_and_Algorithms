public class Recursion_basics {

  public static int powerOptimized(int x, int n) {
    if (n == 0) {
      return 1;
    }
    int halfPower = powerOptimized(x, n / 2);
    if (n % 2 != 0) {
      return (x * halfPower * halfPower);
    } else {
      return (halfPower * halfPower);
    }
  }

  public static int power(int x, int n) {
    if (n == 0) {
      return 1;
    }
    return x * power(x, n - 1);
  }

  public static int lastOccurence(int arr[], int key, int i) {
    if (i == arr.length - 1) {
      return -1;
    }
    if ((arr[i] == key) && (lastOccurence(arr, key, i + 1) == -1)) {
      return i;
    }
    return lastOccurence(arr, key, i + 1);
  }

  public static int firstOccurence(int arr[], int key, int i) {
    if (i == arr.length - 1) {
      return -1;
    }
    if (arr[i] == key) {
      return i;
    }
    return firstOccurence(arr, key, i + 1);
  }

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
      System.out.println(n + " ");
      return;
    }
    System.out.println(n + " ");
    printDecreasing(n - 1);
  }

  public static void printIncreasing(int n) {
    if (n == 1) {
      System.out.println(n + " ");
      return;
    }
    printIncreasing(n - 1);
    System.out.println(n + " ");
  }

  public static void main(String args[]) {
    int n = 6;
    int arr[] = { 1, 9, 9, 5, 6 };
    printDecreasing(n);
    System.out.println(isSorted(arr, 0));
    System.out.println(lastOccurence(arr, 9, 0));
    System.out.println(powerOptimized(2, 5));
  }
}
