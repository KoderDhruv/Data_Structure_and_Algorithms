
public class java_basics {

  public static int bin_to_dec(int bin_num) {
    int decimal_num = 0;
    int pow = 0;

    while (bin_num != 0) {
      int last_digit = bin_num % 10;
      decimal_num += last_digit * (int) Math.pow(2, pow);
      pow++;
      bin_num /= 10;
    }
    return decimal_num;
  }

  public static void main(String args[]) {
    System.out.println(bin_to_dec(1001));
  }
}