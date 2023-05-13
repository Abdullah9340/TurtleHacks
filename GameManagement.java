import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class GameManagement implements KeyListener {

  // Level Information
  private ArrayList<ArrayList<BufferedImage[][]>> levelTileMaps;
  private int currentLevel = 0;

  private Player player;

  private ArrayList<GarbageObject> trashList;

  private HashSet<BufferedImage> allowedObjects;

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
    allowedObjects = new HashSet<>();

    allowedObjects.add(BackgroundAssets.sandTileBasic);
    allowedObjects.add(BackgroundAssets.transparentTile);
    allowedObjects.add(BackgroundAssets.sandTileGrass);
    allowedObjects.add(BackgroundAssets.sandTileShadowBottom1);
    allowedObjects.add(BackgroundAssets.sandTileShadowBottom2);
    allowedObjects.add(BackgroundAssets.sandTileShadowBottom3);
    allowedObjects.add(BackgroundAssets.shoreTileHoriz);
    allowedObjects.add(BackgroundAssets.shoreTileCurveTop);
    allowedObjects.add(BackgroundAssets.shoreTileCurveBottom);

    generateGarbage();
  }

  public void generateGarbage() {
    for (int i = 0; i < 10; i++) {
      GarbageObject garbage = new GarbageObject();
      System.out.println(garbage.getX() + " " + garbage.getY());
      trashList.add(garbage);
    }
  }

  public void render(Graphics g) {
    // Draw the level tile map

    if (isMenuState) {
      g.drawImage(Assets.menuScreen, 0, 0, TurtleHacks.WIDTH, TurtleHacks.HEIGHT, null, null);
      return;
    }
    g.setColor(Color.red);
    for (BufferedImage[][] layer : levelTileMaps.get(currentLevel)) {
      for (int i = 0; i < TurtleHacks.HEIGHT / 64; i++) {
        for (int j = 0; j < TurtleHacks.WIDTH / 64; j++) {
          g.drawImage(layer[i][j], j * 64, i * 64, 64, 64, null, null);
          g.drawRect(j * 64, i * 64, 64, 64);
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
        checkForGarbage();
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
    checkCollisions();

  }

  public void checkCollisions() {
    // Player Collision With Trash
    for (GarbageObject obj : trashList) {
      if (obj.getX() == (int) player.getX() && obj.getY() == (int) player.getY()) {
        player.updateAfterCollide();
      }
    }
  }

  public void checkForGarbage() {
    char direction = player.getDirection();
    int xDir = 0;
    int yDir = 0;

    if (direction == 'w') {
      xDir = 0;
      yDir = -1;
    }
    if (direction == 's') {
      xDir = 0;
      yDir = 1;

    }
    if (direction == 'd') {
      xDir = 1;
      yDir = 0;

    }
    if (direction == 'a') {
      xDir = -1;
      yDir = 0;
    }
    
    for (GarbageObject obj : trashList) {
      if (player.getX() + xDir == obj.getX() && player.getY() + yDir == obj.getY()){
        trashList.remove(obj);
        break;
      }
    }
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
