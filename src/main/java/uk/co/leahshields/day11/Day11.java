package uk.co.leahshields.day11;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 {
  List<Monkey> monkeys = new ArrayList<>();
  int supermodulo = 1;

  public class Monkey implements Comparable<Monkey> {
    List<Long> startingItems = new ArrayList<>();
    LongBinaryOperator operator;
    Integer value;
    int divisor;
    int trueMonkey;
    int falseMonkey;

    int numberOfInspections = 0;

    public void setStartingItems(List<Long> startingItems) {
      this.startingItems = startingItems;
    }

    public void setOperator(LongBinaryOperator operator) {
      this.operator = operator;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public void setDivisor(int divisor) {
      this.divisor = divisor;
    }

    public void setTrueMonkey(int trueMonkey) {
      this.trueMonkey = trueMonkey;
    }

    public void setFalseMonkey(int falseMonkey) {
      this.falseMonkey = falseMonkey;
    }

    Long operation(Long old) {
      if (value != null) {
        return operator.applyAsLong(old, value);
      } else {
        return operator.applyAsLong(old, old);
      }

    }

    int getMonkeyToThrowTo(Long worryLevel) {
      numberOfInspections++;
      return worryLevel % divisor == 0 ? trueMonkey : falseMonkey;
    }

    @Override
    public int compareTo(Monkey monkey) {
      return Integer.compare(numberOfInspections, monkey.numberOfInspections);
    }
  }

  private void readFromFile(String inputLocation) {
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      Monkey monkey = new Monkey();
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        if (line.contains("Starting items")) {
          Matcher matcher = Pattern.compile("\\d+").matcher(line);

          List<Long> startingItems = new ArrayList<>();
          while (matcher.find()) {
            startingItems.add(Long.valueOf(matcher.group()));
          }
          monkey.setStartingItems(startingItems);
        } else if (line.contains("Operation")) {
          Pattern p = Pattern.compile("new = old (.*)");
          Matcher m = p.matcher(line);
          if (m.find()) {
            String operation = m.group(1);
            char operator = operation.charAt(0);
            if (operator == '+') {
              monkey.setOperator((old, value) -> old + value);
            } else if (operator == '*') {
              monkey.setOperator((old, value) -> old * value);
            }
            String value = operation.split(" ")[1];
            if (!value.equals("old")) {
              monkey.setValue(Integer.parseInt(value));
            }
          }
        } else if (line.contains("Test")) {
          Matcher matcher = Pattern.compile("\\d+").matcher(line);
          if (matcher.find()) {
            int divisor = Integer.parseInt(matcher.group());
            supermodulo *= divisor;
            monkey.setDivisor(divisor);
          }
        } else if (line.contains("true")) {
          String lastCharacter = Character.toString(line.charAt(line.length() - 1));
          monkey.setTrueMonkey(Integer.parseInt(lastCharacter));
        } else if (line.contains("false")) {
          String lastCharacter = Character.toString(line.charAt(line.length() - 1));
          monkey.setFalseMonkey(Integer.parseInt(lastCharacter));
          monkeys.add(monkey);
          monkey = new Monkey();
        }
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  private void throwItemToMonkey(Long worryLevel, int monkeyNo) {
    Monkey newMonkey = monkeys.get(monkeyNo);
    List<Long> items = newMonkey.startingItems;
    items.add(worryLevel);
  }

  private Long minimiseWorry(Long currentWorry, int divisor) {
    return currentWorry % supermodulo;
  }

  private void performRound(boolean divideBy3) {
    for (Monkey monkey : monkeys) {
      List<Long> toRemove = new ArrayList<>();
      for (Long item : monkey.startingItems) {
        Long worryLevel = monkey.operation(item);
        if (divideBy3) {
          worryLevel = Math.floorDiv(worryLevel, 3);
        } else {
          worryLevel = minimiseWorry(worryLevel, monkey.divisor);
        }
        int newMonkey = monkey.getMonkeyToThrowTo(worryLevel);
        throwItemToMonkey(worryLevel, newMonkey);
        toRemove.add(item);
      }
      for (Long item : toRemove) {
        monkey.startingItems.remove(item);
      }
    }
  }

  public int part1(String inputLocation) {
    readFromFile(inputLocation);
    for (int i = 0; i < 20; i++) {
      performRound(true);
    }
    Collections.sort(monkeys, Collections.reverseOrder());
    return monkeys.get(0).numberOfInspections * monkeys.get(1).numberOfInspections;
  }

  public Long part2(String inputLocation) {
    readFromFile(inputLocation);
    for (int i = 0; i < 10000; i++) {
      performRound(false);
    }
    Collections.sort(monkeys, Collections.reverseOrder());
    return Long.valueOf(monkeys.get(0).numberOfInspections) * monkeys.get(1).numberOfInspections;
  }
}
