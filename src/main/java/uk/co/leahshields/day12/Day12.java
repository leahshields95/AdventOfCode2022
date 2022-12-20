package uk.co.leahshields.day12;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day12 {
  Point currentPosition;
  Point endPosition;
  char[][] heightMap;
  Map<Point, Integer> visited = new HashMap<>();
  List<Point> unvisited = new ArrayList<>();

  public Day12(String inputLocation) {
    readFromFile(inputLocation);
  }

  private void readFromFile(String inputLocation) {
    List<char[]> inputMap = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        if (line.contains("S")) {
          currentPosition = new Point(inputMap.size(), line.indexOf("S"));
          line = line.replace("S", "a");
        }
        if (line.contains("E")) {
          endPosition = new Point(inputMap.size(), line.indexOf("E"));
          line = line.replace("E", "z");
        }
        char[] row = line.toCharArray();
        inputMap.add(row);
      }
      heightMap = new char[inputMap.size()][inputMap.get(0).length];
      for (int i = 0; i < inputMap.size(); i++) {
        heightMap[i] = inputMap.get(i);
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  public boolean isReachable(char newSquare) {
    char currentHeight = heightMap[(int) currentPosition.getX()][(int) currentPosition.getY()];
    if (Character.valueOf(currentHeight) + 1 == Character.valueOf(newSquare)
        || Character.valueOf(currentHeight) == Character.valueOf(newSquare)) {
      return true;
    }
    if (Character.valueOf(newSquare) < Character.valueOf(currentHeight)) {
      return true;
    }
    return false;
  }

  private boolean isReachable(Point neighbour) {
    if (neighbour.x < 0 || neighbour.y < 0 || neighbour.x >= heightMap.length || neighbour.y >= heightMap[0].length) {
      return false;
    }
    return isReachable(heightMap[neighbour.x][neighbour.y]);
  }

  public List<Point> getReachableNeighbours(Point location) {
    List<Point> neighbours = List.of(
        new Point(currentPosition.x, currentPosition.y - 1),
        new Point(currentPosition.x, currentPosition.y + 1),
        new Point(currentPosition.x - 1, currentPosition.y),
        new Point(currentPosition.x + 1, currentPosition.y));
    return neighbours.stream().filter(this::isReachable).toList();
  }

  public void bfs(Point startingLocation) {
    unvisited.remove(startingLocation);
    currentPosition = startingLocation;

    if (!currentPosition.equals(endPosition)) {
      List<Point> neighbours = getReachableNeighbours(startingLocation);

      for (Point point : neighbours) {
        if (!visited.containsKey(point) && !unvisited.contains(point)) {
          unvisited.add(point);
          visited.put(new Point(point), visited.get(startingLocation) + 1);
        }
      }
      for (int i = 0; i < unvisited.size(); i++) {
        bfs(unvisited.get(i));
      }
    }
  }

  public int part1() {
    visited.put(currentPosition, 0);
    unvisited.add(currentPosition);
    bfs(currentPosition);
    return visited.get(endPosition);
  }

  public List<Point> getStartingPositions() {
    List<Point> startingPositions = new ArrayList<>();
    for (int i = 0; i < heightMap.length; i++) {
      if (heightMap[0][i] == 'a') {
        startingPositions.add(new Point(0, i));
      }
      if (i != 0 && heightMap[i][0] == 'a') {
        startingPositions.add(new Point(i, 0));
      }
    }
    return startingPositions;
  }

  public int part2() {
    List<Point> startingPositions = getStartingPositions();
    int minSteps = 10000;

    for (Point point : startingPositions) {
      visited.put(point, 0);
      unvisited.add(point);
      bfs(point);
      if (visited.containsKey(endPosition)) {
        int stepsTaken = visited.get(endPosition);
        if (stepsTaken < minSteps) {
          minSteps = stepsTaken;
        }
      }

      unvisited = new ArrayList<>();
      visited.clear();
    }

    return minSteps;
  }
}
