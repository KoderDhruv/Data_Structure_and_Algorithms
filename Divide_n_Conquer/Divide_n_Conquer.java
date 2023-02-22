
public class Divide_n_Conquer {

  public static int searchRotatedArr(int arr[], int key, int si, int ei) {
    if (si > ei) {
      return -1;
    }

    int mid = si + (ei - si) / 2;

    if (arr[mid] == key) {
      return mid;
    }

    // mid on line_1
    if (arr[si] <= arr[mid]) {
      if (arr[si] <= key && key <= arr[mid]) {
        return searchRotatedArr(arr, key, si, mid - 1);
      } else {
        return searchRotatedArr(arr, key, mid + 1, ei);
      }
    }

    // mid on line_2
    else {
      if (arr[mid] <= key && key <= arr[ei]) {
        return searchRotatedArr(arr, key, mid + 1, ei);
      } else {
        return searchRotatedArr(arr, key, si, mid - 1);
      }
    }

  }

  public static void quickSort(int arr[], int si, int ei) {
    if (si >= ei) {
      return;
    }

    int pIdx = partition(arr, si, ei);
    quickSort(arr, si, pIdx - 1);
    quickSort(arr, pIdx + 1, ei);
  }

  public static int partition(int arr[], int si, int ei) {
    int pivot = arr[ei];
    int i = si - 1;
    // 'i' does not change when arr[j] is larger than pivot and then swaps when
    // arr[j] is smaller, so smaller elements comes first in the array.
    // We can alternatively, run two pointers in a loop which swaps under certain
    // condition making smaller elements come first and larger after the pivot.
    for (int j = si; j < ei; j++) {
      if (arr[j] <= pivot) {
        i++;
        // swap
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }
    // swap with pivot
    i++;
    int temp = pivot;
    arr[ei] = arr[i];
    arr[i] = temp;
    return i;
  }

  public static void mergeSort(int arr[], int si, int ei) {
    if (si >= ei) {
      return;
    }

    int mid = si + (ei - si) / 2;
    mergeSort(arr, si, mid);
    mergeSort(arr, mid + 1, ei);
    merge(arr, si, mid, ei);
  }

  public static void merge(int arr[], int si, int mid, int ei) {
    int temp[] = new int[ei - si + 1];
    int i = si;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= ei) {
      if (arr[i] < arr[j]) {
        temp[k] = arr[i];
        i++;
      } else {
        temp[k] = arr[j];
        j++;
      }
      k++;
    }

    // left part
    while (i <= mid) {
      temp[k++] = arr[i++];
    }

    // right part
    while (j <= ei) {
      temp[k++] = arr[j++];
    }

    // copy temporary to original
    for (k = 0, i = si; k < temp.length; k++, i++) {
      arr[i] = temp[k];
    }
  }

  public static void printArr(int arr[]) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }

  public static void main(String args[]) {
    int arr[] = { 6, 3, 9, 5, 2, 8 };
    int rotatedArr[] = { 4, 5, 6, 7, 0, 1, 2 };
    System.out.println(searchRotatedArr(rotatedArr, 5, 0, arr.length-1));
    quickSort(arr, 0, arr.length - 1);
    printArr(arr);
  }
}
