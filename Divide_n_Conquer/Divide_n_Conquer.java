
public class Divide_n_Conquer {

  public static void quicksort(int arr[], int si, int ei) {

    if (si >= ei) {
      return;
    }

    int pIdx = partition(arr, si, ei);
    quicksort(arr, si, pIdx - 1);
    quicksort(arr, pIdx + 1, ei);
  }

  public static int partition(int arr[], int si, int ei) {
    int pivot = arr[ei];
    int i = si - 1; // make place for elements less than pivot
    for (int j = si; j < ei; j++) {
      if (arr[j] <= pivot) {
        i++;
        // swap
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }
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
    quicksort(arr, 0, arr.length - 1);
    printArr(arr);
  }
}
