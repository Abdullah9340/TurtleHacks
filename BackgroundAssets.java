import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class BackgroundAssets {

  public static ArrayList<BufferedImage[][]> firstLevelLayers, secondLevelLayers;
  public static HashMap<Character, BufferedImage> map;

  public static BufferedImage transparentTile, sandTileBasic, sandTileRocks, sandTileGrass, sandTileStarFish;
  public static BufferedImage sandTileShadowed, sandTileShadowBottom1, sandTileShadowBottom2, sandTileShadowBottom3;
  public static BufferedImage rockTopLeft, rockTopRight, rockBottomLeft, rockBottomRight;
  public static BufferedImage waterTileDark, waterTileLight, waterTileCorner, waterShadowed, shoreTileCurveTop,
      shoreTileCurveBottom, shoreTileHoriz;
  public static BufferedImage sandIsland1, sandIsland2, sandIsland3, sandIsland4, sandIsland5, sandIsland6, sandIsland7,
      sandIsland8, sandIsland9, sandIsland10, sandIsland11, sandIsland12;

  public static void init(SpriteSheet spreadsheet) {
    map = new HashMap<Character, BufferedImage>();
    transparentTile = spreadsheet.crop(8 * 16, 4 * 16, 16, 16);
    sandTileBasic = spreadsheet.crop(8 * 16, 2 * 16, 16, 16);
    sandTileRocks = spreadsheet.crop(16 * 16, 9 * 16, 16, 16);
    sandTileGrass = spreadsheet.crop(2 * 16, 16, 16, 16);
    waterTileDark = spreadsheet.crop(7 * 16, 6 * 16, 16, 16);
    waterTileLight = spreadsheet.crop(5 * 16, 6 * 16, 16, 16);
    shoreTileCurveTop = spreadsheet.crop(11 * 16, 13 * 16, 16, 16);
    shoreTileCurveBottom = spreadsheet.crop(11 * 16, 14 * 16, 16, 16);
    shoreTileHoriz = spreadsheet.crop(9 * 16, 7 * 16, 16, 16);
    sandTileShadowed = spreadsheet.crop(5 * 16, 1 * 16, 16, 16);
    sandTileShadowBottom1 = spreadsheet.crop(15 * 16, 11 * 16, 16, 16);
    sandTileShadowBottom2 = spreadsheet.crop(6 * 16, 1 * 16, 16, 16);
    waterTileCorner = spreadsheet.crop(16 * 16, 11 * 16, 16, 16);
    waterShadowed = spreadsheet.crop(5 * 16, 5 * 16, 16, 16);
    sandIsland1 = spreadsheet.crop(0, 3 * 16, 16, 16);
    sandIsland2 = spreadsheet.crop(1 * 16, 3 * 16, 16, 16);
    sandIsland3 = spreadsheet.crop(2 * 16, 3 * 16, 16, 16);
    sandIsland4 = spreadsheet.crop(3 * 16, 3 * 16, 16, 16);
    sandIsland5 = spreadsheet.crop(0, 4 * 16, 16, 16);
    sandIsland6 = spreadsheet.crop(1 * 16, 4 * 16, 16, 16);
    sandIsland7 = spreadsheet.crop(2 * 16, 4 * 16, 16, 16);
    sandIsland8 = spreadsheet.crop(3 * 16, 4 * 16, 16, 16);
    sandIsland9 = spreadsheet.crop(0, 5 * 16, 16, 16);
    sandIsland10 = spreadsheet.crop(1 * 16, 5 * 16, 16, 16);
    sandIsland11 = spreadsheet.crop(2 * 16, 5 * 16, 16, 16);
    sandIsland12 = spreadsheet.crop(3 * 16, 5 * 16, 16, 16);
    firstLevelLayers = new ArrayList<BufferedImage[][]>();
    secondLevelLayers = new ArrayList<BufferedImage[][]>();
    map.put('t', transparentTile);
    map.put('0', sandTileBasic);
    map.put('a', sandTileRocks);
    map.put('d', waterTileDark);
    map.put('l', waterTileLight);
    map.put('s', shoreTileCurveTop);
    map.put('e', shoreTileCurveBottom);
    map.put('h', shoreTileHoriz);
    map.put('1', sandTileShadowed);
    map.put('2', sandTileShadowBottom1);
    map.put('c', waterTileCorner);
    map.put('w', waterShadowed);
    map.put('3', sandTileShadowBottom2);
    map.put('4', sandTileGrass);

    map.put('5', sandIsland1);
    map.put('6', sandIsland2);
    map.put('7', sandIsland3);
    map.put('8', sandIsland4);
    map.put('9', sandIsland5);
    map.put('g', sandIsland6);
    map.put('i', sandIsland7);
    map.put('k', sandIsland8);
    map.put('p', sandIsland9);
    map.put('n', sandIsland10);
    map.put('m', sandIsland11);
    map.put('r', sandIsland12);

    setFirstLevel();
    setSecondLevel();

  }

  public static void setFirstLevel() {
    try {
      // Layer 1
      BufferedImage[][] firstLevelTileMapLayer0 = new BufferedImage[10][17];
      File obj = new File("Assets/level1BackgroundLayer0.txt");
      Scanner myReader = new Scanner(obj);
      int i = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        for (int j = 0; j < data.length(); j++) {
          firstLevelTileMapLayer0[i][j] = map.get(data.charAt(j));
        }
        i++;
      }
      firstLevelLayers.add(firstLevelTileMapLayer0);
      myReader.close();
      // Layer 2
      BufferedImage[][] firstLevelTileMapLayer1 = new BufferedImage[10][17];
      obj = new File("Assets/level1BackgroundLayer1.txt");
      myReader = new Scanner(obj);
      i = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        for (int j = 0; j < data.length(); j++) {
          firstLevelTileMapLayer1[i][j] = map.get(data.charAt(j));
        }
        i++;
      }
      firstLevelLayers.add(firstLevelTileMapLayer1);
      myReader.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred when trying to set first level");
      e.printStackTrace();
    }
  }

  public static void setSecondLevel() {
    try {
      // Layer 1
      BufferedImage[][] firstLevelTileMapLayer0 = new BufferedImage[10][17];
      File obj = new File("Assets/level2BackgroundLayer0.txt");
      Scanner myReader = new Scanner(obj);
      int i = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        for (int j = 0; j < data.length(); j++) {
          firstLevelTileMapLayer0[i][j] = map.get(data.charAt(j));
        }
        i++;
      }
      secondLevelLayers.add(firstLevelTileMapLayer0);
      myReader.close();
      // Layer 2
      BufferedImage[][] firstLevelTileMapLayer1 = new BufferedImage[10][17];
      obj = new File("Assets/level2BackgroundLayer1.txt");
      myReader = new Scanner(obj);
      i = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        for (int j = 0; j < data.length(); j++) {
          firstLevelTileMapLayer1[i][j] = map.get(data.charAt(j));
        }
        i++;
      }
      secondLevelLayers.add(firstLevelTileMapLayer1);
      myReader.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred when trying to set first level");
      e.printStackTrace();
    }
  }

}
