import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FilterBySubstring {

  public static int readInt(String prompt, BufferedReader reader) throws IOException {
    while (true) {
      System.out.print(prompt);
      String line = reader.readLine();
      try {
        return Integer.parseInt(line);
      } catch (NumberFormatException e) {
        System.out.println("Couldn't parse a number. Please, try again");
      }
    }
  }

  public static String[] readStrings(int expectedSize, BufferedReader reader) throws IOException {
    String[] arr = new String[expectedSize];

    for (int i = 0; i < expectedSize; i++) {
      String line = reader.readLine();
      arr[i] = line != null ? line : "";
    }

    return arr;
  }

  public static boolean manualContains(String str, String pattern) {
    if (pattern.isEmpty()) return true;
    if (pattern.length() > str.length()) return false;

    for (int i = 0; i <= str.length() - pattern.length(); i++) {
      boolean match = true;

      for (int j = 0; j < pattern.length(); j++) {
        if (str.charAt(i + j) != pattern.charAt(j)) {
          match = false;
          break;
        }
      }

      if (match) return true;
    }

    return false;
  }

  public static List<String> stringFilter(String[] input, String pattern) {
    List<String> res = new ArrayList<>();

    for (String str : input) {
      if (manualContains(str, pattern)) {
        res.add(str);
      }
    }

    return res;
  }

  public static void processStrings() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int size = readInt("Enter the number of strings: ", reader);
    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
      return;
    }

    System.out.println("Enter strings:");
    String[] strings = readStrings(size, reader);

    System.out.print("Enter a substring: ");
    String substring = reader.readLine();

    List<String> filtered = stringFilter(strings, substring);
    System.out.println(String.join(", ", filtered));
  }

  public static void main(String[] args) throws IOException {
    processStrings();
  }
}
