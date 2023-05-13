import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class BackgroundAssets {

  public static ArrayList<BufferedImage[][]> firstLevelLayers;
  public static HashMap<Character, BufferedImage> map;

  public static BufferedImage transparentTile, sandTileBasic, sandTileRocks, sandTileGrass, sandTileStarFish;
  public static BufferedImage sandTileShadowed, sandTileShadowBottom1, sandTileShadowBottom2, sandTileShadowBottom3;
  public static BufferedImage rockTopLeft, rockTopRight, rockBottomLeft, rockBottomRight;
  public static BufferedImage waterTileDark, waterTileLight, waterTileCorner, waterShadowed, shoreTileCurveTop, shoreTileCurveBottom, shoreTileHoriz;

  public static void init(SpriteSheet spreadsheet) {
    map = new HashMap<Character, BufferedImage>();
    transparentTile = spreadsheet.crop(8 * 16, 4 * 16, 16, 16);
    sandTileBasic = spreadsheet.crop(8 * 16, 2 * 16, 16, 16);
    sandTileRocks = spreadsheet.crop(16 * 16, 9 * 16, 16, 16);
    waterTileDark = spreadsheet.crop(7 * 16, 6 * 16, 16, 16);
    waterTileLight = spreadsheet.crop(5 * 16, 6 * 16, 16, 16);
    shoreTileCurveTop = spreadsheet.crop(11 * 16, 13 * 16, 16, 16);
    shoreTileCurveBottom = spreadsheet.crop(11 * 16, 14 * 16, 16, 16);
    shoreTileHoriz = spreadsheet.crop(9 * 16, 7 * 16, 16, 16);
    sandTileShadowed = spreadsheet.crop(5 * 16, 1 * 16, 16, 16);
    sandTileShadowBottom1 = spreadsheet.crop(15*16, 11*16, 16, 16);
    sandTileShadowBottom2 = spreadsheet.crop(6*16, 1*16, 16, 16);
    waterTileCorner = spreadsheet.crop(16*16, 11*16, 16, 16);
    waterShadowed = spreadsheet.crop(5*16, 5*16, 16, 16);
    firstLevelLayers = new ArrayList<BufferedImage[][]>();
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

    setFirstLevel();

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

}
