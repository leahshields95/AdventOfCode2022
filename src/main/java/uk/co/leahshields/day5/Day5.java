package uk.co.leahshields.day5;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
  List<Stack<String>> crateArrangements = new ArrayList<>();
  List<List<Integer>> instructions = new ArrayList<>();
  int numberOfStacks;

  public Day5() {
  }

  private void readFromFile(String inputLocation) {
    List<String> crates = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        if (line.contains("[")) {
          for (int i = 0; i < line.length(); i += 4) {
            String lineSection = line.substring(i, i + 3);

            if (lineSection.contains("   ")) {
              crates.add("");
            } else {
              crates.add(lineSection.replaceAll("[ \\[ \\]]", ""));
            }
          }
        }
        if (line.contains("move")) {
          Matcher matcher = Pattern.compile("\\d+").matcher(line);

          List<Integer> numbers = new ArrayList<>();
          while (matcher.find()) {
            numbers.add(Integer.valueOf(matcher.group()));
          }
          instructions.add(numbers);
        }
        if (line.matches("[ *\\d+ ]+")) {
          line = line.trim();
          numberOfStacks = Integer.parseInt(line.substring(line.length() - 1));
          System.out.println(numberOfStacks);
        }
      }

      for (int i = numberOfStacks; i >= 1; i--) {
        Stack<String> crateStack = new Stack<>();
        for (int j = crates.size() - i; j >= 0; j -= numberOfStacks) {
          if (crates.get(j) != "") {
            crateStack.push(crates.get(j));
          }
        }
        crateArrangements.add(crateStack);
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }
  private void moveCratesInOrder(int numberOfCrates, int from, int to) {
    String[] cratesMoving = new String[numberOfCrates];
    for (int i = 0; i < numberOfCrates; i++) {
      cratesMoving[i] = crateArrangements.get(from - 1).pop();
    }
    for (int i = cratesMoving.length -1; i >= 0; i--) {
      crateArrangements.get(to - 1).push(cratesMoving[i]);
    }
  }

  private void moveCrates(int numberOfCrates, int from, int to) {
    for (int i = 0; i < numberOfCrates; i++) {
      String topCrate = crateArrangements.get(from - 1).pop();
      crateArrangements.get(to - 1).push(topCrate);
    }
  }

  private void followInstructions() {
    for (List<Integer> instruction : instructions) {
      moveCrates(instruction.get(0), instruction.get(1), instruction.get(2));
    }
  }

  private void followInstructionsKeepOrder() {
    for (List<Integer> instruction : instructions) {
      moveCratesInOrder(instruction.get(0), instruction.get(1), instruction.get(2));
    }
  }

  private String getTopCrates() {
    String topCrates = "";
    for (Stack<String> stack : crateArrangements) {
      topCrates += stack.peek();
    }
    return topCrates;
  }

  public String part1(String inputLocation) {
    readFromFile(inputLocation);
    followInstructions();
    return getTopCrates();
  }

  public String part2(String inputLocation) {
    readFromFile(inputLocation);
    followInstructionsKeepOrder();
    return getTopCrates();
  }
}
