package uk.co.leahshields.day13;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Day13 {
  List<List<List<Object>>> packetPairs = new ArrayList<>();
  List<Integer> rightOrderIndices = new ArrayList<>();

  private Object convertItem(JsonElement item) {
    if (item instanceof JsonPrimitive) {
      return item.getAsInt();
    } else {
      JsonArray array = item.getAsJsonArray();
      List<Object> list = new ArrayList<>();
      for (JsonElement jsonElement : array) {
        list.add(convertItem(jsonElement));
      }
      return list;
    }
  }

  private void readFromFile(String inputLocation) {
    List<List<Object>> packetPair = new ArrayList<>();
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {

        String line = scanner.nextLine();
        JsonElement json = JsonParser.parseString(line);

        if (!json.isJsonNull()) {
          JsonArray jsonArray = json.getAsJsonArray();
          if (jsonArray.isEmpty()) {
            packetPair.add(new ArrayList<>());
          } else {
            packetPair
                .add(jsonArray.asList().stream().map(x -> convertItem(x)).collect(Collectors.toList()));
          }
        } else {
          packetPairs.add(packetPair);
          packetPair = new ArrayList<>();
        }
      }
      packetPairs.add(packetPair);
      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  private int compare(Object left, Object right) {
    if (left instanceof Integer && right instanceof Integer) {
      int leftObject = (int) left;
      int rightObject = (int) right;
      return leftObject - rightObject;
    } else if (left instanceof List && right instanceof List) {
      List<Object> leftObject = (List<Object>) left;
      List<Object> rightObject = (List<Object>) right;
      if (leftObject.isEmpty()) {
        return rightObject.isEmpty() ? 0 : -1;
      }
      for (int i = 0; i < leftObject.size(); i++) {
        if ((i == (rightObject.size()))) {
          return 1;
        }
        int comparison = compare(leftObject.get(i), rightObject.get(i));
        if (comparison != 0)
          return comparison;
      }
    } else {
      List<Object> leftObject;
      List<Object> rightObject;
      if (left instanceof Integer) {
        leftObject = List.of(left);
        rightObject = (List<Object>) right;
      } else {
        rightObject = List.of(right);
        leftObject = (List<Object>) left;
      }
      return compare(leftObject, rightObject);
    }
    return -1;
  }

  private int getSum(List<Integer> list) {
    int sum = 0;
    for (Integer integer : list) {
      sum += integer;
    }
    return sum;
  }

  public int part1(String inputLocation) {
    readFromFile(inputLocation);
    for (int i = 0; i < packetPairs.size(); i++) {
      List<Object> left = packetPairs.get(i).get(0);
      List<Object> right = packetPairs.get(i).get(1);
      if (compare(left, right) <= 0) {
        rightOrderIndices.add(i + 1);
      }
    }
    return getSum(rightOrderIndices);

  }

  public int part2(String inputLocation) {
    readFromFile(inputLocation);
    List<Object> allPackets = new ArrayList<>();
    List<List<Object>> dividerPackets = List.of(
        List.of(2),
        List.of(6));

    packetPairs.add(dividerPackets);

    for (int i = 0; i < packetPairs.size(); i++) {
      allPackets.add(packetPairs.get(i).get(0));
      allPackets.add(packetPairs.get(i).get(1));
    }

    List<Object> sortedPackets = allPackets.stream().sorted(this::compare).toList();
    int dividerLocations = dividerPackets.stream()
        .map(p -> sortedPackets.indexOf(p) + 1)
        .reduce((a, b) -> a * b).get();
    return dividerLocations;
  }
}
