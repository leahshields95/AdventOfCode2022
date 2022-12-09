package uk.co.leahshields.day9;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day9 {
  Point headPosition;
  Map<Point, Integer> tMovements = new HashMap<>();
  List<Character> directions = new ArrayList<>();
  List<Integer> movements = new ArrayList<>();
  Point[] knots;

  public Day9(int numberOfKnots) {
    knots = new Point[numberOfKnots];
    for (int i = 0; i < knots.length; i++) {
      knots[i] = new Point(0, 0);
    }
    headPosition = knots[0];
    tMovements.put(new Point(0, 0), 1);
  }

  private double getXDirection(Character direction) {
    switch (direction) {
      case 'L':
        return -1;
      case 'R':
        return 1;
      default:
        return 0;
    }
  }

  private double getYDirection(Character direction) {
    switch (direction) {
      case 'U':
        return -1;
      case 'D':
        return 1;
      default:
        return 0;
    }
  }

  private void moveHead(Character direction, int movement) {
    for (int i = 0; i < movement; i++) {
      int newXPosition = (int) (headPosition.getX() + getXDirection(direction));
      int newYPosition = (int) (headPosition.getY() + getYDirection(direction));
      headPosition.setLocation(newXPosition, newYPosition);

      moveNextKnotIfNotAdjacent(1);
    }
  }

  private void moveNextKnotIfNotAdjacent(int nextKnotPosition) {
    Point currentKnot = knots[nextKnotPosition - 1];
    Point nextKnot = knots[nextKnotPosition];
    if (!((nextKnot.getX() >= currentKnot.getX() - 1 && nextKnot.getX() <= currentKnot.getX() + 1)
        && (nextKnot.getY() >= currentKnot.getY() - 1 && nextKnot.getY() <= currentKnot.getY() + 1))) {
      int newXPosition = (int) nextKnot.getX();
      int newYPosition = (int) nextKnot.getY();
      if ((currentKnot.getX() - newXPosition) < 0) {
        newXPosition += getXDirection('L');
      }
      if ((currentKnot.getX() - newXPosition) > 0) {
        newXPosition += getXDirection('R');
      }
      if ((currentKnot.getY() - newYPosition) < 0) {
        newYPosition += getYDirection('U');
      }
      if ((currentKnot.getY() - newYPosition) > 0) {
        newYPosition += getYDirection('D');
      }
      nextKnot.setLocation(newXPosition, newYPosition);
      knots[nextKnotPosition] = nextKnot;

      // If is tail, record movement
      if ((nextKnotPosition) == (knots.length - 1)) {
        if (tMovements.containsKey(knots[nextKnotPosition])) {
    
          tMovements.put(knots[nextKnotPosition], tMovements.get(knots[nextKnotPosition]) + 1);
        } else {
          tMovements.put(new Point(newXPosition, newYPosition), 1);
        }
      }
      // if not tail, check next knot
      else {
        moveNextKnotIfNotAdjacent(nextKnotPosition + 1);
      }

    }

  }

  private void readFromFile(String inputLocation) {
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String[] line = scanner.nextLine().split(" ");
        directions.add(line[0].charAt(0));
        movements.add(Integer.parseInt(line[1]));
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  private int getNumberOfPositionsVisited() {
    // for (Map.Entry<Point, Integer> entry : tMovements.entrySet()) {
    //   System.out.println(entry.getKey());
    //   System.out.println(entry.getValue());
    // }
    return tMovements.keySet().size();
  }

  public int part1(String inputLocation) {
    readFromFile(inputLocation);
    for (int i = 0; i < directions.size(); i++) {
      moveHead(directions.get(i), movements.get(i));
    }
    return getNumberOfPositionsVisited();

  }

  public int part2(String inputLocation) {
    readFromFile(inputLocation);
    for (int i = 0; i < directions.size(); i++) {
      moveHead(directions.get(i), movements.get(i));
    }
    return getNumberOfPositionsVisited();
  }
}
