public class Backtracking {

  public static boolean sudokuSolver(int sudoku[][], int row, int col) {
    // base case
    if (row == 0) {
      return true;
    }

    // iterations
    int nextRow = row, nextCol = col + 1;
    if ((col + 1) == 9) {
      nextRow = row + 1;
      nextCol = 0;
    }

    // recursion
    if (sudoku[row][col]!=0){
      return sudokuSolver(sudoku, nextRow, nextCol);
    }
    for(int digit=1; digit<=9; digit++){
      if (isSafe_sudoku(sudoku, row, col, digit)){
        sudoku[row][col]=digit;
        if(sudokuSolver(sudoku, nextRow, nextCol))
      }
    }
  }

  public static int gridWays(int curr_x, int curr_y, int rows, int columns) {
    // base case
    if (curr_x == (rows - 1) && curr_y == (columns - 1)) {
      return 1;
    } else if (curr_x == rows || curr_y == columns) {
      return 0;
    }

    // recursion
    return (gridWays(curr_x + 1, curr_y, rows, columns) + gridWays(curr_x, curr_y + 1, rows, columns));
  }

  public static void nQueens(char board[][], int row) {
    // base case
    if (row == board.length) {
      printBoard(board);
      return;
    }
    // column loop
    for (int j = 0; j < board.length; j++) {
      if (isSafe_chess(board, row, j)) {
        board[row][j] = 'Q';
        nQueens(board, row + 1); // function call
        board[row][j] = 'x'; // backtracking step
      }
    }
  }

  public static boolean isSafe_chess(char board[][], int row, int col) {
    // vertical up
    for (int i = row - 1; i >= 0; i--) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }
    // diagonally left
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }
    // diagonally right
    for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }
    return true;
  }

  public static void printBoard(char board[][]) {
    System.out.println("-------chess-board-------");
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void findPermutation(String str, String ans) {
    // base case
    if (str.length() == 0) {
      System.out.print(ans + " ");
    }

    // rescursion, when one character is not included
    for (int i = 0; i < str.length(); i++) {
      findPermutation(str.substring(0, i) + str.substring(i + 1), ans + str.charAt(i));
    }
  }

  public static void findSubsets(String str, String ans, int i) {
    // base case
    if (i == str.length()) {
      if (ans.length() == 0) {
        System.out.print("null");
      }
      System.out.print(ans + " ");
      return;
    }

    // char included
    findSubsets(str, ans + str.charAt(i), i + 1);
    // char not included
    findSubsets(str, ans, i + 1);
  }

  public static void changeArr(int arr[], int i, int val) {
    // base case
    if (i == arr.length) {
      System.out.print("Array after hitting basecase: ");
      printArr(arr);
      return;
    }

    // recursion
    arr[i] = val;
    changeArr(arr, i + 1, val + 1); // function call step
    arr[i] = arr[i] - 2; // backtracking step
  }

  public static void printArr(int arr[]) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String args[]) {
    int sudoku[][] = {
        { 0, 0, 8, 0, 0, 0, 0, 0, 0 },
        { 4, 9, 0, 1, 5, 7, 0, 0, 2 },
        { 0, 0, 3, 0, 0, 4, 1, 9, 0 },
        { 1, 8, 5, 0, 6, 0, 0, 2, 0 },
        { 0, 0, 0, 0, 2, 0, 0, 6, 0 },
        { 9, 6, 0, 4, 0, 5, 3, 0, 0 },
        { 0, 3, 0, 0, 7, 2, 0, 0, 4 },
        { 0, 4, 9, 0, 3, 0, 0, 5, 7 },
        { 8, 2, 7, 0, 0, 9, 0, 1, 3 }
    };
    int n = 4;
    char board[][] = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = 'x';
      }
    }
    int arr[] = new int[5];
    String str = "abc";
    changeArr(arr, 0, 1);
    printArr(arr);
    findSubsets(str, "", 0);
    System.out.println();
    findPermutation(str, "");
    System.out.println();
    nQueens(board, 0);
    System.out.println();
    System.out.println(gridWays(0, 0, 3, 3));
  }
}
