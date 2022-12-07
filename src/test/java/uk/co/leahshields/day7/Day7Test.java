package uk.co.leahshields.day7;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day7Test {
  @Test
  public void shouldReturnDirectorySize() {
    Day7 day7 = new Day7();
    assertEquals(95437, day7.part1("/day7_example.txt"));
  }

  @Test
  public void shouldGetPart1PuzzleAnswer() {
    Day7 day7 = new Day7();
    System.out.println(day7.part1("/day7_actual.txt"));
  }

  @Test
  public void shouldReturnDirectorySize24933642() {
    Day7 day7 = new Day7();
    assertEquals(24933642, day7.part2("/day7_example.txt"));
  }

  @Test
  public void shouldGetPart2PuzzleAnswer() {
    Day7 day7 = new Day7();
    System.out.println(day7.part2("/day7_actual.txt"));
  }

}
