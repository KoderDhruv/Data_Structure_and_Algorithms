
public class Matrices {

  public static boolean search_matrix(int matrix[][], int key) {
    int row = 0, col = matrix[0].length - 1;

    while (row < matrix.length && col >= 0) {
      if (key > matrix[row][col]) {
        row++;
      } else if (key < matrix[row][col]) {
        col--;
      } else if (key == matrix[row][col]) {
        System.out.println("Found key at (" + (row + 1) + ", " + (col + 1) + ").");
        return true;
      }
    }
    System.out.println("Key not found!");
    return false;
  }

  public static int diagonal_sum(int matrix[][]) {
    int sum = 0;

    // for (int i = 0; i < matrix.length; i++) {
    // for (int j = 0; j < matrix[0].length; j++) {
    // if (i == j) {
    // sum += matrix[i][j];
    // }
    // if (i == matrix.length - j - 1) {
    // sum += matrix[i][j];
    // }
    // }
    // }
    // return sum;

    for (int i = 0; i < matrix.length; i++) {
      sum += matrix[i][i];
      if (i != matrix.length - i - 1) {
        sum += matrix[i][matrix.length - i - 1];
      }
    }
    return sum;
  }

  public static void print_spiral(int matrix[][]) {
    int start_row = 0;
    int start_col = 0;
    int end_row = matrix.length - 1;
    int end_col = matrix[0].length - 1;

    while (start_row <= end_row && start_col <= end_row) {
      // Top
      for (int j = start_col; j <= end_col; j++) {
        System.out.print(matrix[start_row][j] + " ");
      }
      // Right
      for (int i = start_row + 1; i <= end_row; i++) {
        System.out.print(matrix[i][end_col] + " ");
      }
      // Bottom
      for (int j = end_col - 1; j >= start_col; j--) {
        if (start_row == end_row) {
          break;
        }
        System.out.print(matrix[end_row][j] + " ");
      }
      // Left
      for (int i = end_row - 1; i >= start_row + 1; i--) {
        if (start_col == end_col) {
          break;
        }
        System.out.print(matrix[i][start_col] + " ");
      }

      start_col++;
      start_row++;
      end_col--;
      end_row--;
    }
  }

  public static void main(String args[]) {
    int spiral_matrix[][] = {
        { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
    // print_spiral(spiral_matrix);
    search_matrix(spiral_matrix, 7);
  }
}
