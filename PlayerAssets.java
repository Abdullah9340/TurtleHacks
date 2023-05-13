import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerAssets {
  public static ArrayList<BufferedImage> upAnimations;
  public static ArrayList<BufferedImage> downAnimations;
  public static ArrayList<BufferedImage> leftAnimations;
  public static ArrayList<BufferedImage> rightAnimations;
  public static ArrayList<BufferedImage> pickUpRightAnimations;
  public static ArrayList<BufferedImage> pickUpLeftAnimations;
  public static ArrayList<BufferedImage> pickUpUpAnimations;
  public static ArrayList<BufferedImage> pickUpDownAnimations;

  public static void init(SpriteSheet players) {
    // Main player sprite
    PlayerAssets.upAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 9; i++) {
      PlayerAssets.upAnimations.add(players.crop(64 * i, 64 * 8, 64, 64));
    }

    PlayerAssets.downAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 9; i++) {
      PlayerAssets.downAnimations.add(players.crop(64 * i, 64 * 10, 64, 64));
    }

    PlayerAssets.leftAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 9; i++) {
      PlayerAssets.leftAnimations.add(players.crop(64 * i, 64 * 9, 64, 64));
    }

    PlayerAssets.rightAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 9; i++) {
      PlayerAssets.rightAnimations.add(players.crop(64 * i, 64 * 11, 64, 64));
    }

    PlayerAssets.pickUpRightAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 8; i++) {
      PlayerAssets.pickUpRightAnimations.add(players.crop(64 * i, 64 * 7, 64, 64));
    }

    PlayerAssets.pickUpDownAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 8; i++) {
      PlayerAssets.pickUpDownAnimations.add(players.crop(64 * i, 64 * 6, 64, 64));
    }

    PlayerAssets.pickUpLeftAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 8; i++) {
      PlayerAssets.pickUpLeftAnimations.add(players.crop(64 * i, 64 * 5, 64, 64));
    }

    PlayerAssets.pickUpUpAnimations = new ArrayList<BufferedImage>();

    for (int i = 0; i < 8; i++) {
      PlayerAssets.pickUpUpAnimations.add(players.crop(64 * i, 64 * 4, 64, 64));
    }

  }

}
