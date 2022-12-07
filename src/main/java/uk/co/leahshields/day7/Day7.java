package uk.co.leahshields.day7;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
  private static final int TOTAL_DISK_SPACE = 70000000;
  private static final int UPDATE_SPACE_REQUIRED = 30000000;

  private TreeNode<String> root = new TreeNode<>("root");
  private int count = 0;
  private List<TreeNode<String>> removalCandidates = new ArrayList<>();

  public class TreeNode<T> {
    T data;
    TreeNode<T> parent;
    List<TreeNode<T>> children;
    int fileSize = 0;
    int totalSize = 0;

    public TreeNode(T data2) {
      this.data = data2;
      this.children = new LinkedList<TreeNode<T>>();
    }

    public TreeNode<T> addChild(T child) {
      TreeNode<T> childNode = new TreeNode<T>(child);
      childNode.parent = this;
      this.children.add(childNode);
      return childNode;
    }

    public void addFile(int fileSize) {
      this.fileSize += fileSize;
    }

    public int getDirectoriesOver(int minSize) {
      int totalSize = this.fileSize;

      for (TreeNode<T> directory : children) {
        int directorySize = directory.getDirectoriesOver(minSize);
        totalSize += directorySize;
        if (directorySize >= minSize) {
          TreeNode<String> node = new TreeNode<String>((String) directory.data);
          node.totalSize = directorySize;
          removalCandidates.add(node);
        }
      }
      return totalSize;
    }

    public int getTotalSizeOfDirectoriesUnder(int maxSize) {
      int totalSize = this.fileSize;

      for (TreeNode<T> directory : children) {
        int directorySize = directory.getTotalSizeOfDirectoriesUnder(maxSize);
        totalSize += directorySize;
        if (directorySize <= maxSize) {
          count += directorySize;
        }
      }
      return totalSize;
    }

    public int getTotalSize() {
      int totalSize = this.fileSize;
      for (TreeNode<T> directory : children) {
        totalSize += directory.getTotalSize();
      }
      return totalSize;
    }

  }

  private void readFromFile(String inputLocation) {
    TreeNode<String> currentDirectory = root;
    try (
        InputStream inputStream = this.getClass().getResourceAsStream(inputLocation);
        Scanner scanner = new Scanner(inputStream)) {
      while (scanner.hasNext()) {
        String line = scanner.nextLine();

        if (line.contains("$ cd")) {
          String newDirectory = line.split(" ")[2].trim();
          if (newDirectory.equals("..")) {
            currentDirectory = currentDirectory.parent;
          } else if (!newDirectory.equals("/")) {
            currentDirectory = currentDirectory.addChild(newDirectory);
          }
        }
        Matcher matcher = Pattern.compile("\\d+\s+").matcher(line);
        if (matcher.find()) {
          currentDirectory.addFile(Integer.parseInt(matcher.group().trim()));
        }
      }

      inputStream.close();
      scanner.close();
    } catch (IOException exception) {
      System.out.println(exception);
    }
  }

  public int part1(String inputLocation) {
    readFromFile(inputLocation);
    root.getTotalSizeOfDirectoriesUnder(100000);
    return count;
  }

  public int part2(String inputLocation) {
    readFromFile(inputLocation);
    int unusedSpace = TOTAL_DISK_SPACE - root.getTotalSize();
    int spaceRequired = UPDATE_SPACE_REQUIRED - unusedSpace;
    root.getDirectoriesOver(spaceRequired);

    int smallestPossible = Integer.MAX_VALUE;
    for (TreeNode<String> treeNode : removalCandidates) {
      if (treeNode.totalSize < smallestPossible) {
        smallestPossible = treeNode.totalSize;
      }
    }
    return smallestPossible;
  }
}
