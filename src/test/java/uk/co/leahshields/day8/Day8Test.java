package uk.co.leahshields.day8;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day8Test {
  @Test
  public void shouldReturn21VisibleTrees() {
    Day8 day8 = new Day8();
    assertEquals(21, day8.part1("/day8_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day8 day8 = new Day8();
    System.out.println(day8.part1("/day8_actual.txt"));
  }

  @Test
  public void shouldReturnViewingDistance8() {
    Day8 day8 = new Day8();
    assertEquals(8, day8.part2("/day8_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day8 day8 = new Day8();
    System.out.println(day8.part2("/day8_actual.txt"));
  }

}
