package com.kushal.battleship.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kushal.battleship.exception.InvalidInput;

public class GameUtil {
  private static final Logger LOG = LogManager.getLogger(GameUtil.class);

  public static List<String> loadInput(String fileName) {
    List<String> input = new ArrayList<String>();
    ClassLoader classLoader = GameUtil.class.getClassLoader();
    if (classLoader.getResource(fileName) != null) {
      fileName = classLoader.getResource(fileName).getFile();
    }
    File file = new File(fileName);
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        input.add(scanner.nextLine());
      }
      scanner.close();
    } catch (IOException e) {
      LOG.error("File not found", e);
    }

    for (String string : input) {
      LOG.info(string);
    }

    return input;
  }

  public static boolean isNumOfShipsAllowed(String input, int totalShips) {
    String[] coordinates = input.split(" ");
    int maxShip = Integer.parseInt(coordinates[0]) * coordinates[1].charAt(0);

    if (totalShips < 1) {
      throw new InvalidInput("Invalid Ship Count: Must be greater or equal to 1");
    } else if (totalShips > maxShip) {
      throw new InvalidInput("Invalid Ship Count: Must be less than or equal to " + maxShip);
    }
    return true;
  }

  public static void setAllowedSizeForShip(String input) {
    String[] coordInput = input.split(" ");
    GameConstants.maxWidth = new Integer(coordInput[0]);
    GameConstants.maxHeight = coordInput[1].charAt(0);

  }
}
