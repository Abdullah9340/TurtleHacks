import java.awt.image.BufferedImage;

public class Assets {

  // Declare all our image assets

  public static BufferedImage sandTile;

  public static BufferedImage menuScreen, gameover, gamewon;

  public static BufferedImage[] endingScreen;
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
    menuScreen = ImageLoader.loadImage("Assets/mainmenu.png");
    // gameover = ImageLoader.loadImage("Assets/Gameover.png");
    gamewon = ImageLoader.loadImage("Assets/credits.png");
    endingScreen = new BufferedImage[] { ImageLoader.loadImage("Assets/30.png"), ImageLoader.loadImage("Assets/50.png"),
        ImageLoader.loadImage("Assets/70.png"), ImageLoader.loadImage("Assets/90.png"),
        ImageLoader.loadImage("Assets/100.png") };
    // Load Background
    BackgroundAssets.init(background);
    // Load player sprites
    PlayerAssets.init(players);
  }
}