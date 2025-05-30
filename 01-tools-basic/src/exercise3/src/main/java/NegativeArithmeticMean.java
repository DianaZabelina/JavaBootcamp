import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NegativeArithmeticMean {

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

  public static Integer arithmeticMean(List<Integer> numbers) {
    int sum = 0;
    int count = 0;

    for (int num : numbers) {
      if (num < 0) {
        sum += num;
        count++;
      }
    }

    return count == 0 ? null : sum / count;
  }

  public static void printResult(int size, BufferedReader reader) throws IOException {
    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
    } else {
      List<Integer> numbers = readNumbers(size, "Enter the numbers separated by spaces: ", reader);
      Integer avg = arithmeticMean(numbers);
      System.out.println(avg == null ? "There are no negative elements" : avg);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int size = readInt("Enter the number of elements: ", reader);
    printResult(size, reader);
  }
}
