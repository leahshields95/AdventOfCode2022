package uk.co.leahshields.day8;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8 {
  Integer[][] treeGrid;

  private void readFromFile(String inputLocation) {
    List<List<Integer>> input = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        List<Integer> row = new ArrayList<>();
        String line = scanner.nextLine();

        for (int i = 0; i < line.length(); i++) {
          row.add(Integer.parseInt(Character.toString(line.charAt(i))));
        }
        input.add(row);
      }
      treeGrid = input.stream()
          .map(l -> l.stream().toArray(Integer[]::new))
          .toArray(Integer[][]::new);

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  public boolean isVisible(int xPosition, int yPosition) {
    int tree = treeGrid[xPosition][yPosition];
    boolean isVisibleToRight = true;
    boolean isVisibleToLeft = true;
    boolean isVisibleAbove = true;
    boolean isVisibleBelow = true;

    if (xPosition == 0 || xPosition == treeGrid.length - 1 || yPosition == 0 || yPosition == treeGrid.length - 1) {
      return true;
    }

    for (int i = 0; i < treeGrid.length; i++) {
      if (i != yPosition && (treeGrid[xPosition][i] >= tree)) {
        if (i < yPosition) {
          isVisibleToLeft = false;
        } else {
          isVisibleToRight = false;
        }
      }
      if (i != xPosition && (treeGrid[i][yPosition] >= tree)) {
        if (i < xPosition) {
          isVisibleAbove = false;
        } else {
          isVisibleBelow = false;
        }
      }
    }

    boolean isVisibleInRow = isVisibleToLeft || isVisibleToRight;
    boolean isVisibleInColumn = isVisibleAbove || isVisibleBelow;

    return isVisibleInColumn || isVisibleInRow;
  }

  private int getScenicView(int leftViewingDistance, int rightViewingDistance, int bottomViewingDistance,
      int topViewingDistance) {
    return leftViewingDistance * rightViewingDistance * bottomViewingDistance * topViewingDistance;
  }

  public int viewingDistance(int xPosition, int yPosition) {
    int tree = treeGrid[xPosition][yPosition];
    int rightViewingDistance = 0;
    int leftViewingDistance = 0;
    int topViewingDistance = 0;
    int bottomViewingDistance = 0;

    if (xPosition == 0 || xPosition == treeGrid.length - 1 || yPosition == 0 || yPosition == treeGrid.length - 1) {
      return 0;
    }
    for (int i = 0; i < treeGrid.length; i++) {
      if (i != yPosition && (treeGrid[xPosition][i] >= tree)) {
        if (i < yPosition) {
          leftViewingDistance = yPosition - i;
        } else if (i > yPosition && rightViewingDistance == 0) {
          rightViewingDistance = i - yPosition;
        }
      }
      if (i != xPosition && (treeGrid[i][yPosition] >= tree)) {
        if (i < xPosition) {
          topViewingDistance = xPosition - i;
        } else if(i > xPosition && bottomViewingDistance == 0) {
          bottomViewingDistance = i - xPosition;
        }
      }
    }
    if (leftViewingDistance == 0) {
      leftViewingDistance = yPosition;
    }
    if (rightViewingDistance == 0) {
      rightViewingDistance = treeGrid.length - yPosition - 1;

    }
    if (topViewingDistance == 0) {
      topViewingDistance = xPosition;
    }
    if (bottomViewingDistance == 0) {
      bottomViewingDistance = treeGrid.length - xPosition -1;
    }
    // System.out.println("tree: " + tree + " position " + xPosition + ", " + yPosition);
    // System.out.println("t: " + topViewingDistance);
    // System.out.println("l: " + leftViewingDistance);
    // System.out.println("r: " + rightViewingDistance);
    // System.out.println("b: " + bottomViewingDistance);
    // System.out.println("------");

    return getScenicView(leftViewingDistance, rightViewingDistance, bottomViewingDistance, topViewingDistance);
  }

  public int part1(String inputLocation) {
    int visibleTrees = 0;
    readFromFile(inputLocation);
    for (int row = 0; row < treeGrid.length; row++) {
      for (int column = 0; column < treeGrid.length; column++) {
        if (isVisible(row, column)) {
          visibleTrees++;
        }
      }
    }
    return visibleTrees;
  }

  public int part2(String inputLocation) {
    readFromFile(inputLocation);
    int highestScenicScore = 0;
    for (int row = 0; row < treeGrid.length; row++) {
      for (int column = 0; column < treeGrid.length; column++) {
        int viewingDistance = viewingDistance(row, column);
        if (viewingDistance > highestScenicScore) {
          highestScenicScore = viewingDistance;
        }
      }
    }
    return highestScenicScore;
  }
}
