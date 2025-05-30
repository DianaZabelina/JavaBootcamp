import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstLastDigitMatch {

  public static void showParseError() {
    System.out.println("Couldn't parse a number. Please, try again");
  }

  public static int readInt(String prompt, BufferedReader reader) throws IOException {
    while (true) {
      System.out.print(prompt);
      String line = reader.readLine();
      try {
        return Integer.parseInt(line);
      } catch (NumberFormatException e) {
        showParseError();
      }
    }
  }

  public static List<Integer> readNumbers(int expectedSize, String prompt, BufferedReader reader)
      throws IOException {
    while (true) {
      System.out.print(prompt);
      String line = reader.readLine();
      if (line == null) {
        showParseError();
        continue;
      }

      String[] parts = line.trim().split("\\s+");
      if (parts.length != expectedSize) {
        showParseError();
        continue;
      }

      List<Integer> numbers = new ArrayList<>();
      boolean allParsed = true;

      for (String p : parts) {
        try {
          numbers.add(Integer.parseInt(p));
        } catch (NumberFormatException e) {
          allParsed = false;
          break;
        }
      }

      if (allParsed) return numbers;
      showParseError();
    }
  }

  public static boolean hasSameFirstAndLastDigit(int num) {
    int absNum = Math.abs(num);
    int last = absNum % 10;
    int first = absNum;

    while (first >= 10) {
      first /= 10;
    }

    return first == last;
  }

  public static void printMatches(List<Integer> matches) {
    if (matches.isEmpty()) {
      System.out.println("There are no such elements");
    } else {
      String result = matches.stream().map(String::valueOf).collect(Collectors.joining(" "));
      System.out.println(result);
    }
  }

  public static void printResult(int size, BufferedReader reader) throws IOException {
    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
      return;
    }

    List<Integer> numbers = readNumbers(size, "Enter the numbers separated by spaces: ", reader);
    List<Integer> matches = new ArrayList<>();

    for (int num : numbers) {
      if (hasSameFirstAndLastDigit(num)) {
        matches.add(num);
      }
    }

    printMatches(matches);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int size = readInt("Enter the number of elements: ", reader);
    printResult(size, reader);
  }
}
