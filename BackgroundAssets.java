import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedInputStream;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.nio.Buffer;
import java.util.Scanner; // Import the Scanner class to read text files

public class BackgroundAssets {

  public static ArrayList<BufferedImage[][]> firstLevelLayers, secondLevelLayers;
  public static HashMap<Character, BufferedImage> map1;
  public static HashMap<Character, BufferedImage> map2;

  public static BufferedImage transparentTile, sandTileBasic, sandTileRocks, sandTileGrass, sandTileStarFish;
  public static BufferedImage sandTileShadowed, sandTileShadowBottom1, sandTileShadowBottom2, sandTileShadowBottom3;
  public static BufferedImage rockTopLeft, rockTopRight, rockBottomLeft, rockBottomRight;
  public static BufferedImage waterTileDark, waterTileLight, waterTileCorner, waterShadowed, shoreTileCurveTop,
      shoreTileCurveBottom, shoreTileHoriz;

  public static BufferedImage transparent, grassBasic, grassLeftCorner, grassRightCorner, grassTopHoriz, grassRightVert, grassLeftVert, grassBottomRight, grassBottomLeft, grassBottomHoriz;
  public static BufferedImage shoreLeftCorner, shoreRightCorner, shoreLeftVert, shoreTopHoriz, shoreRightVert, shoreBottomLeft, shoreBottomRight, ShoreBottomHoriz;
  public static BufferedImage rock, flowers, lilypads;

  public static void init(SpriteSheet spreadsheet) {
    map1 = new HashMap<Character, BufferedImage>();
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

    firstLevelLayers = new ArrayList<BufferedImage[][]>();
    secondLevelLayers = new ArrayList<BufferedImage[][]>();
    map1.put('t', transparentTile);
    map1.put('0', sandTileBasic);
    map1.put('a', sandTileRocks);
    map1.put('d', waterTileDark);
    map1.put('l', waterTileLight);
    map1.put('s', shoreTileCurveTop);
    map1.put('e', shoreTileCurveBottom);
    map1.put('h', shoreTileHoriz);
    map1.put('1', sandTileShadowed);
    map1.put('2', sandTileShadowBottom1);
    map1.put('c', waterTileCorner);
    map1.put('w', waterShadowed);
    map1.put('3', sandTileShadowBottom2);
    map1.put('4', sandTileGrass);

    setFirstLevel();

  }

  public static void init2(SpriteSheet spreadsheet) {

    map2 = new HashMap<>();
    transparent = spreadsheet.crop(4 * 16, 6 * 16, 16, 16);
    grassBasic = spreadsheet.crop(0, 7 * 16, 16, 16);
    shoreLeftVert = spreadsheet.crop(8*16, 8*16, 16, 16);
    shoreLeftCorner = spreadsheet.crop(8*16, 7*16, 16, 16);
    shoreTopHoriz = spreadsheet.crop(9*16, 7*16, 16, 16);
    shoreRightCorner = spreadsheet.crop(10*16, 7*16, 16, 16);
    shoreRightVert = spreadsheet.crop(10*16, 8*16, 16, 16);
    shoreBottomRight = spreadsheet.crop(10*16, 10*16, 16, 16);
    shoreBottomLeft = spreadsheet.crop(8*16, 10*16, 16, 16);
    ShoreBottomHoriz = spreadsheet.crop(9*16, 10*16, 16, 16);
    grassLeftCorner = spreadsheet.crop(0*16, 8*16, 16, 16);
    grassRightCorner = spreadsheet.crop(3*16, 8*16, 16, 16);
    grassTopHoriz = spreadsheet.crop(1*16, 8*16, 16, 16);
    grassRightVert = spreadsheet.crop(3*16, 9*16, 16, 16);
    grassLeftVert = spreadsheet.crop(0*16, 9*16, 16, 16);
    grassBottomLeft = spreadsheet.crop(0*16, 10*16, 16, 16);
    grassBottomRight = spreadsheet.crop(3*16, 10*16, 16, 16);
    grassBottomHoriz = spreadsheet.crop(1*16, 10*16, 16, 16);
    rock = spreadsheet.crop(7*16, 9*16, 16, 16);
    flowers = spreadsheet.crop(7*16, 10*16, 16, 16);
    lilypads = spreadsheet.crop(10*16, 11*16, 16, 16);

    map2.put('t', transparent);
    map2.put('0', grassBasic);
    map2.put('l', waterTileLight);
    map2.put('s', shoreLeftCorner);
    map2.put('v', shoreLeftVert);
    map2.put('h', shoreTopHoriz);
    map2.put('r', shoreRightCorner);
    map2.put('b', shoreRightVert);
    map2.put('c', shoreBottomRight);
    map2.put('d', shoreBottomLeft);
    map2.put('e', ShoreBottomHoriz);
    map2.put('f', grassLeftCorner);
    map2.put('g', grassRightCorner);
    map2.put('i', grassTopHoriz);
    map2.put('j', grassRightVert);
    map2.put('k', grassLeftVert);
    map2.put('m', grassBottomLeft);
    map2.put('n', grassBottomRight);
    map2.put('o', grassBottomHoriz);
    map2.put('p', rock);
    map2.put('q', flowers);
    map2.put('u', lilypads);


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
          firstLevelTileMapLayer0[i][j] = map1.get(data.charAt(j));
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
          firstLevelTileMapLayer1[i][j] = map1.get(data.charAt(j));
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
          firstLevelTileMapLayer0[i][j] = map2.get(data.charAt(j));
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
          firstLevelTileMapLayer1[i][j] = map2.get(data.charAt(j));
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
