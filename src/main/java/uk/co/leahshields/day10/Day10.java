package uk.co.leahshields.day10;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Day10 {
  List<String> instructions = new ArrayList<>();
  int currentCycle = 1;
  int totalSignalStrengths = 0;
  int registerX = 1;
  Queue<AddInstruction> addQueue = new LinkedList<>();

  public class AddInstruction {
    int value;
    int cycleNumber;

    public AddInstruction(int value, int cycleNumber) {
      this.value = value;
      this.cycleNumber = cycleNumber;
    }
  }

  private int getSignalStrength() {
    return currentCycle * registerX;
  }

  private void increaseCycle() {
    int pixelBeingDrawn = currentCycle - 1;
    if (pixelBeingDrawn >= 40) {
      pixelBeingDrawn = pixelBeingDrawn % 40;
    }
    if (pixelBeingDrawn == 0)
      System.out.println();

    if (pixelBeingDrawn <= registerX + 1 && pixelBeingDrawn >= registerX - 1) {
      System.out.print("# ");
    } else {
      System.out.print(". ");
    }
    if (currentCycle == 20 || (currentCycle - 20) % 40 == 0) {
      totalSignalStrengths += getSignalStrength();
    }
    currentCycle++;
  }

  private void checkQueuedInstructions() {
    if (!addQueue.isEmpty()) {
      if (addQueue.peek().cycleNumber == currentCycle) {
        int addValue = addQueue.remove().value;
        registerX += addValue;
      }
    }
  }

  private void runCycle(String instruction) {
    checkQueuedInstructions();
    if (instruction.contains("add")) {
      increaseCycle();
      increaseCycle();
      registerX += Integer.parseInt(instruction.split(" ")[1]);
    } else {
      increaseCycle();
    }

  }

  private void readFromFile(String inputLocation) {
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        instructions.add(line);
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  public int part1(String inputLocation) {
    readFromFile(inputLocation);
    for (String instruction : instructions) {
      runCycle(instruction);
    }
    return totalSignalStrengths;

  }

  public int part2(String inputLocation) {
    readFromFile(inputLocation);
    for (String instruction : instructions) {
      runCycle(instruction);
    }
    return 0;
  }
}
