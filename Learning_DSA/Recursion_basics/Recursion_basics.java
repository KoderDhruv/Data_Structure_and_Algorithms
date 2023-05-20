public class Recursion_basics {

  public static void binaryString(){
    
  }

  public static int friendsPairing(int n) {
    if (n == 1 || n == 2) {
      return n;
    }
    return (friendsPairing(n - 1) + ((n - 1) * friendsPairing(n - 2)));
  }

  public static void removeDuplicate(String str, int idx, StringBuilder newStr, boolean map[]) {
    if (idx == str.length()) {
      System.out.println(newStr);
      return;
    }
    if (map[str.charAt(idx) - 'a'] == true) {
      removeDuplicate(str, idx + 1, newStr, map);
    } else {
      map[str.charAt(idx) - 'a'] = true;
      removeDuplicate(str, idx + 1, newStr.append(str.charAt(idx)), map);
    }
  }

  public static int tilingProblem(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }
    return tilingProblem(n - 1) + tilingProblem(n - 2);
  }

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
    if (n == 0 || n == 1) {
      return n;
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
    String str = "dhruvbanuni";
    System.out.println(fib(n));
    System.out.println(isSorted(arr, 0));
    System.out.println(lastOccurence(arr, 9, 0));
    System.out.println(powerOptimized(2, 5));
    removeDuplicate(str, 0, new StringBuilder(""), new boolean[26]);
    System.out.println(friendsPairing(4));
  }
}
