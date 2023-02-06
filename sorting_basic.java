public class sorting_basic {

  public static void counting_sort(int arr[]) {
    int largest = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      largest = Math.max(largest, arr[i]);
    }
    int count[] = new int[largest + 1];
    for (int i = 0; i < arr.length; i++) {
      count[arr[i]]++;
    }
    int j = 0;
    for (int i = 0; i < count.length; i++) {
      while (count[i] > 0) {
        arr[j] = i;
        j++;
        count[i]--;
      }
    }
  }

  public static void insertion_sort(int arr[]) {
    for (int i = 1; i < arr.length; i++) {
      int curr = arr[i];
      int previous_index = i - 1;
      while (previous_index >= 0 && arr[previous_index] > curr) {
        arr[previous_index + 1] = arr[previous_index];
        previous_index--;
      }
      arr[previous_index + 1] = curr;
    }
  }

  public static void selection_sort(int arr[]) {
    for (int i = 0; i < arr.length - 1; i++) {
      int minimum_index = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[minimum_index] > arr[j]) {
          minimum_index = j;
        }
      }
      int temp = arr[minimum_index];
      arr[minimum_index] = arr[i];
      arr[i] = temp;
    }
  }

  public static void bubble_sort(int arr[]) {
    for (int turn = 0; turn < arr.length - 1; turn++) {
      for (int j = 0; j < arr.length - 1 - turn; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }

  public static void print_array(int arr[]) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }

  public static void main(String args[]) {
    int arr[] = { 1, 4, 5, 3, 2 };
    // bubble_sort(arr);
    // selection_sort(arr);
    // insertion_sort(arr);
    counting_sort(arr);
    print_array(arr);
  }
}
