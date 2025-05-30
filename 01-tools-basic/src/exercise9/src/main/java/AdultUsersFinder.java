import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdultUsersFinder {

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

  public static String readString(String prompt, BufferedReader reader) throws IOException {
    while (true) {
      System.out.print(prompt);
      String line = reader.readLine();
      if (line != null && !line.trim().isEmpty()) {
        return line.trim();
      }
      System.out.println("Name cannot be empty. Please try again");
    }
  }

  public static List<User> readUsers(int expectedSize, BufferedReader reader) throws IOException {
    List<User> users = new ArrayList<>();

    for (int i = 0; i < expectedSize; i++) {
      String name = readString("Enter a name: ", reader);
      int age = readInt("Enter an age: ", reader);

      if (age <= 0) {
        System.out.println("Incorrect input. Age <= 0");
        continue;
      }

      users.add(new User(name, age));
    }

    return users;
  }

  public static void processUsers() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int size = readInt("Enter the number of users: ", reader);

    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
      return;
    }

    List<User> users = readUsers(size, reader);

    List<String> adultNames =
        users.stream()
            .filter(u -> u.getAge() >= 18)
            .map(User::getName)
            .collect(Collectors.toList());

    if (!adultNames.isEmpty()) {
      System.out.println(String.join(", ", adultNames));
    }
  }

  public static void main(String[] args) throws IOException {
    processUsers();
  }
}
