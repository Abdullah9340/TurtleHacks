import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {

  private static final int width = 32, height = 32; // Declare width and height for certain tile sheet

  // Declare all our image assets

  public static BufferedImage sandTile;

  public static BufferedImage menuScreen, gameover, gamewon;
  /*-
   * Method: init() 
   * Description: Set up all image assets
   * pre: none 
   * post: loads all images into variables
   */

  public static void init() {
    // Load spritesheets
    // SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("Assets/rpg.png"));
    SpriteSheet players = new SpriteSheet(ImageLoader.loadImage("Assets/playersprite.png"));
    SpriteSheet background = new SpriteSheet(ImageLoader.loadImage("Assets/beach.png"));
    // SpriteSheet darksoilder = new
    // SpriteSheet(ImageLoader.loadImage("Assets/darksoilder.png"));

    // Load Images
    // menuScreen = ImageLoader.loadImage("Assets/mainmenu.png");
    // gameover = ImageLoader.loadImage("Assets/Gameover.png");
    // gamewon = ImageLoader.loadImage("Assets/gamewon.png");

    // Load Background

    // Load player sprites
    PlayerAssets.init(players);
  }
}