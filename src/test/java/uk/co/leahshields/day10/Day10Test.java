package uk.co.leahshields.day10;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day10Test {
  @Test
  public void shouldReturn13140() {
    Day10 day10 = new Day10();
    assertEquals(13140, day10.part1("/day10_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day10 day10 = new Day10();
    System.out.println(day10.part1("/day10_actual.txt"));
  }

  @Test
  public void shouldReturnViewingDistance36() {
    Day10 day10 = new Day10();
    day10.part2("/day10_example.txt");
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day10 day10 = new Day10();
    day10.part2("/day10_actual.txt");
  }

}
