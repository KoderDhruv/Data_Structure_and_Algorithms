public class strings {

  public static String compress(String str) {
    String new_str = "";
    for (int i = 0; i < str.length(); i++) {
      Integer count = 1;
      while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
        count++;
        i++;
      }
      new_str += str.charAt(i);
      if (count > 1) {
        new_str += count.toString();
      }
    }
    return new_str;
  }

  public static String to_upper_case(String str) {
    StringBuilder sb = new StringBuilder("");
    char ch = Character.toUpperCase(str.charAt(0));
    sb.append(ch);

    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == ' ' && i < str.length() - 1) {
        sb.append(str.charAt(i));
        i++;
        sb.append(Character.toUpperCase(str.charAt(i)));
      } else {
        sb.append(str.charAt(i));
      }
    }
    return sb.toString();
  }

  public static void shortest_distance(String str) {
    int x = 0;
    int y = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'N') {
        y++;
      } else if (str.charAt(i) == 'S') {
        y--;
      } else if (str.charAt(i) == 'E') {
        x++;
      } else if (str.charAt(i) == 'W') {
        x--;
      }
    }
    System.out.println("Shortest Distance is " + (float) Math.sqrt((x * x) + (y * y)));
  }

  public static boolean is_palindrome(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
        System.out.println("Not a Palindrome!");
        return false;
      }
    }
    System.out.println("The given string is a Palindrome!");
    return true;
  }

  public static void string_builder() {
    StringBuilder sb = new StringBuilder("");
    for (char ch = 'a'; ch <= 'z'; ch++) {
      sb.append(ch);
    }
    sb.toString();
    System.out.println(sb);
  }

  public static void main(String args[]) {
    String sentence = "hi, my name is dhruv.";
    String str = "rascecar";
    String path = "WENNSSSWNESWEW";
    is_palindrome(str);
    shortest_distance(path);
    System.out.println(to_upper_case(sentence));
    System.out.println(compress(path));
  }
}
