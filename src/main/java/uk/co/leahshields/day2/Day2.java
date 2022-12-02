package uk.co.leahshields.day2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day2 {
  enum Choice {
    ROCK(1, "A", "X"),
    PAPER(2, "B", "Y"),
    SCISSORS(3, "C", "Z");

    private String[] aliases;
    private static final Map<String, Choice> choiceAliases = new HashMap<>();
    private final int score;

    static {
      for (Choice choice : Choice.values()) {
        for (String alias : choice.aliases) {
          choiceAliases.put(alias, choice);
        }
      }
    }

    private Choice(int score, String... aliases) {
      this.score = score;
      this.aliases = aliases;
    }

    public static Choice getChoice(String alias) {
      return choiceAliases.get(alias);
    }

    public static int getScore(String alias) {
      return choiceAliases.get(alias).score;
    }

  }

  private List<String[]> fileToList(String inputLocation) {
    List<String[]> strategyGuide = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String[] line = scanner.nextLine().split(" ");
        strategyGuide.add(line);
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
    return strategyGuide;
  }

  private int getRoundOutcome(Choice playerChoice, Choice opponentChoice) {
    if (playerChoice == opponentChoice) {
      return 3;
    }
    if (playerChoice == Choice.ROCK) {
      if (opponentChoice == Choice.PAPER)
        return 0;
      if (opponentChoice == Choice.SCISSORS)
        return 6;
    }
    if (playerChoice == Choice.PAPER) {
      if (opponentChoice == Choice.SCISSORS)
        return 0;
      if (opponentChoice == Choice.ROCK)
        return 6;
    }
    if (playerChoice == Choice.SCISSORS) {
      if (opponentChoice == Choice.ROCK)
        return 0;
      if (opponentChoice == Choice.PAPER)
        return 6;
    }
    return 0;
  }

  public int part1(String inputLocation) {
    int totalScore = 0;
    List<String[]> strategyGuide = fileToList(inputLocation);
    for (String[] round : strategyGuide) {
      Choice opponentChoice = Choice.getChoice(round[0]);
      Choice playerChoice = Choice.getChoice(round[1]);
      int choiceScore = playerChoice.score;
      totalScore += choiceScore;
      int roundScore = getRoundOutcome(playerChoice, opponentChoice);
      totalScore += roundScore;
    }
    return totalScore;
  }

  private int getRoundOutcome(Choice opponentChoice, String outcomeCode) {
    // Lose
    if (outcomeCode.equals("X")) {
      if (opponentChoice == Choice.ROCK)
        return Choice.SCISSORS.score;
      if (opponentChoice == Choice.PAPER)
        return Choice.ROCK.score;
      if (opponentChoice == Choice.SCISSORS)
        return Choice.PAPER.score;
    }
    // Win
    if (outcomeCode.equals("Z")) {
      if (opponentChoice == Choice.ROCK)
        return Choice.PAPER.score + 6;
      if (opponentChoice == Choice.PAPER)
        return Choice.SCISSORS.score + 6;
      if (opponentChoice == Choice.SCISSORS)
        return Choice.ROCK.score + 6;
    }

    // Draw
    return opponentChoice.score + 3;
  }

  public int part2(String inputLocation) {
    int totalScore = 0;
    List<String[]> strategyGuide = fileToList(inputLocation);
    for (String[] round : strategyGuide) {
      Choice opponentChoice = Choice.getChoice(round[0]);
      int roundOutcome = getRoundOutcome(opponentChoice, round[1]);
      totalScore += roundOutcome;
    }
    return totalScore;
  }
}
