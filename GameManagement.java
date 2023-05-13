import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameManagement implements KeyListener {

  // Level Information
  private ArrayList<ArrayList<BufferedImage[][]>> levelTileMaps;
  private int currentLevel = 0;

  private Player player;

  private ArrayList<GarbageObject> trashList;

  // Player Grabbing Logic
  boolean isGrabbing = false;
  int grabFrame = 0;

  // Are we in the main menu
  boolean isMenuState = true;

  public GameManagement(Player player) {
    levelTileMaps = new ArrayList<>();
    levelTileMaps.add(BackgroundAssets.firstLevelLayers);
    this.player = player;

    GarbageObject.init();
    trashList = new ArrayList<GarbageObject>();

    generateGarbage();
  }

  public void generateGarbage() {
    for (int i = 0; i < 10; i++) {
      GarbageObject garbage = new GarbageObject();

      trashList.add(garbage);
    }
  }

  public void render(Graphics g) {
    // Draw the level tile map

    if (isMenuState) {
      g.drawImage(Assets.menuScreen, 0, 0, TurtleHacks.WIDTH, TurtleHacks.HEIGHT, null, null);
      return;
    }

    for (BufferedImage[][] layer : levelTileMaps.get(currentLevel)) {
      for (int i = 0; i < TurtleHacks.HEIGHT / 64; i++) {
        for (int j = 0; j < TurtleHacks.WIDTH / 64; j++) {
          g.drawImage(layer[i][j], j * 64, i * 64, 64, 64, null, null);
        }
      }
    }

    for (GarbageObject obj : trashList) {
      obj.render(g);
    }

    if (isGrabbing) {
      player.renderGrab(g, grabFrame);
      grabFrame++;
      if (grabFrame == PlayerAssets.pickUpDownAnimations.size()) {
        isGrabbing = false;
        grabFrame = 0;
      }
    } else {
      player.render(g);
    }

  }

  public void update() {
    if (isMenuState)
      return;
    if (isGrabbing)
      return;
    player.update();

  }

  public void checkCollisions() {
    
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    if (isMenuState) {
      isMenuState = false;
      return;
    }
    if (e.getKeyChar() == 'q' && player.stopped && !isGrabbing) {
      isGrabbing = true;
      grabFrame = 0;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

}
