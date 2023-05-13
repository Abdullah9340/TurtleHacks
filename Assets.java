import java.awt.image.BufferedImage;

public class Assets {

  private static final int width = 32, height = 32; // Declare width and height for certain tile sheet

  // Declare all our image assets
  public static BufferedImage[][] player = new BufferedImage[4][3];

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
    // SpriteSheet darksoilder = new
    // SpriteSheet(ImageLoader.loadImage("Assets/darksoilder.png"));

    // Load Images
    // menuScreen = ImageLoader.loadImage("Assets/mainmenu.png");
    // gameover = ImageLoader.loadImage("Assets/Gameover.png");
    // gamewon = ImageLoader.loadImage("Assets/gamewon.png");

    // Main player sprite
    player[0][0] = players.crop(4 * 32, 0, 32, 32);
    player[0][1] = players.crop(5 * 32, 0, 32, 32);
    player[0][2] = players.crop(3 * 32, 0, 32, 32);
    player[1][0] = players.crop(4 * 32, 32, 32, 32);
    player[1][1] = players.crop(5 * 32, 32, 32, 32);
    player[1][2] = players.crop(3 * 32, 32, 32, 32);
    player[2][0] = players.crop(4 * 32, 32 * 2, 32, 32);
    player[2][1] = players.crop(5 * 32, 32 * 2, 32, 32);
    player[2][2] = players.crop(3 * 32, 32 * 2, 32, 32);
    player[3][0] = players.crop(4 * 32, 32 * 3, 32, 32);
    player[3][1] = players.crop(5 * 32, 32 * 3, 32, 32);
    player[3][2] = players.crop(3 * 32, 32 * 3, 32, 32);
  }
}