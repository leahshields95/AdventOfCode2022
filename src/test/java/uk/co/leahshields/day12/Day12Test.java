package uk.co.leahshields.day12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.List;

import org.junit.Test;

public class Day12Test {
  @Test
  public void shouldCalculateIfReachable() {
    Day12 day12 = new Day12("/day12_example.txt");
    assertEquals(day12.currentPosition, new Point(0, 0));

    assertTrue(day12.isReachable('a'));
    assertTrue(day12.isReachable('b'));
    assertFalse(day12.isReachable('c'));

    day12.currentPosition = new Point(2, 4);
    assertEquals(day12.heightMap[day12.currentPosition.x][day12.currentPosition.y], 'z');
    assertTrue(day12.isReachable('a'));
    assertTrue(day12.isReachable('b'));
    assertTrue(day12.isReachable('z'));
  }

  @Test
  public void shouldGetReachableNeighbours() {
    Day12 day12 = new Day12("/day12_example.txt");
    assertEquals(day12.currentPosition, new Point(0, 0));
    List<Point> neighbours = day12.getReachableNeighbours(day12.currentPosition);
    assertEquals(2, neighbours.size());
    assertEquals(new Point(0,1), neighbours.get(0));
    assertEquals(new Point(1,0), neighbours.get(1));

    day12.currentPosition = new Point(2, 4);

    neighbours = day12.getReachableNeighbours(day12.currentPosition);
    assertEquals(4, neighbours.size());
    assertEquals(new Point(2,3), neighbours.get(0));
    assertEquals(new Point(2,5), neighbours.get(1));
    assertEquals(new Point(1,4), neighbours.get(2));
    assertEquals(new Point(3,4), neighbours.get(3));

    day12.currentPosition = new Point(3, 7);

    neighbours = day12.getReachableNeighbours(day12.currentPosition);
    assertEquals(2, neighbours.size());
    assertEquals(new Point(2,7), neighbours.get(0));
    assertEquals(new Point(4,7), neighbours.get(1));
  }

  @Test
  public void shouldReturn31() {
    Day12 day12 = new Day12("/day12_example.txt");
    assertEquals(31, day12.part1());
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day12 day12 = new Day12("/day12_actual.txt");
    System.out.println(day12.part1());
  }

  @Test
  public void shouldReturnStartingPositions() {
    Day12 day12 = new Day12("/day12_example.txt");
    List<Point> startingPositions = day12.getStartingPositions();
    assertEquals(6, startingPositions.size());
    assertEquals(new Point(0,1), startingPositions.get(1));
  }

  @Test
  public void shouldReturn29() {
    Day12 day12 = new Day12("/day12_example.txt");
    assertEquals(29, day12.part2());
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day12 day12 = new Day12("/day12_actual.txt");
    System.out.println(day12.part2());
  }

}
