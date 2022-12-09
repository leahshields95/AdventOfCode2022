package uk.co.leahshields.day9;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day9Test {
  @Test
  public void shouldReturn13() {
    Day9 day9 = new Day9(2);
    assertEquals(13, day9.part1("/day9_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day9 day9 = new Day9(2);
    System.out.println(day9.part1("/day9_actual.txt"));
  }

  @Test
  public void shouldReturnViewingDistance36() {
    Day9 day9 = new Day9(10);
    assertEquals(36, day9.part2("/day9_example2.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day9 day9 = new Day9(10);
    System.out.println(day9.part2("/day9_actual.txt"));
  }

}
