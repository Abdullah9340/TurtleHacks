import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;

public class GameManagement implements KeyListener {

  // Level Information
  private ArrayList<ArrayList<BufferedImage[][]>> levelTileMaps;
  private int currentLevel = 0;
  private int garbageWavesLeft = 1;

  private Player player;

  private ArrayList<GarbageObject> trashList;

  private HashSet<BufferedImage> allowedObjects;

  private GarbageBins trashBin, recycleBin;

  private int total_points = 0;
  private int round_number = 1;

  // Player Grabbing Logic
  boolean isGrabbing = false;
  int grabFrame = 0;

  // Player Rolling Logic
  boolean isRolling = false;
  int rollingFrame = 0;

  // Game Won logic
  boolean isGameWon = false;
  int gameWonFrame = 0, other = 0;

  // Are we in the main menu
  boolean isMenuState = true;

  // Are we transitioning levels
  boolean isLevelTransis = false;
  int levelTransisFrame = 0, other2 = 0;

  public GameManagement(Player player) {
    levelTileMaps = new ArrayList<>();
    levelTileMaps.add(BackgroundAssets.firstLevelLayers);
    this.player = player;

    GarbageObject.init();
    trashList = new ArrayList<GarbageObject>();
    allowedObjects = new HashSet<>();

    trashBin = new GarbageBins("trash");
    recycleBin = new GarbageBins("recycling");

    allowedObjects.add(BackgroundAssets.sandTileBasic);
    allowedObjects.add(BackgroundAssets.transparentTile);
    allowedObjects.add(BackgroundAssets.sandTileGrass);
    allowedObjects.add(BackgroundAssets.sandTileShadowBottom1);
    allowedObjects.add(BackgroundAssets.sandTileShadowBottom2);
    allowedObjects.add(BackgroundAssets.sandTileShadowBottom3);
    allowedObjects.add(BackgroundAssets.shoreTileHoriz);
    allowedObjects.add(BackgroundAssets.shoreTileCurveTop);
    allowedObjects.add(BackgroundAssets.shoreTileCurveBottom);
    allowedObjects.add(BackgroundAssets.sandTileShadowed);
    allowedObjects.add(BackgroundAssets.sandTileShadowed);
    allowedObjects.add(BackgroundAssets.sandTileStarFish);

    generateGarbage();
  }

  public void generateGarbage() {
    for (int i = 0; i < 1; i++) {
      GarbageObject garbage = new GarbageObject();
      while (!garbageValid(garbage)) {
        garbage = new GarbageObject();
      }
      trashList.add(garbage);
    }
  }

  public boolean garbageValid(GarbageObject garbage) {
    if (garbage.getX() == (int) player.getX() && garbage.getY() == (int) player.getY()) {
      return false;
    }
    for (GarbageObject obj : trashList) {
      if (obj.getX() == garbage.getX() && obj.getY() == garbage.getY()) {
        return false;
      }
    }

    if (trashBin.get_X() == garbage.getX() && trashBin.get_Y() == garbage.getY()) {
      return false;
    }

    if (recycleBin.get_X() == garbage.getX() && recycleBin.get_Y() == garbage.getY()) {
      return false;
    }

    if (!allowedObjects
        .contains(
            levelTileMaps.get(currentLevel)
                .get(levelTileMaps.get(currentLevel).size() - 1)[garbage.getY()][garbage.getX()])) {
      return false;
    }

    return true;
  }

  public void render(Graphics g) {
    // Draw the level tile map

    if (gameWonFrame == Assets.endingScreen.length) {
      g.drawImage(Assets.gamewon, 0, 0, TurtleHacks.WIDTH, TurtleHacks.HEIGHT, null, null);
      return;
    }

    if (isLevelTransis) {
      if (levelTransisFrame == Assets.endingScreen.length) {
        renderFunFact(g);
        other2++;
        if (other2 == 30) {
          isLevelTransis = false;
          currentLevel++;
          if (currentLevel == levelTileMaps.size()) {
            isGameWon = true;
          }
        }
        return;
      }
    }

    if (isMenuState) {
      g.drawImage(Assets.menuScreen, 0, 0, TurtleHacks.WIDTH, TurtleHacks.HEIGHT, null, null);
      return;
    }

    g.setColor(Color.red);
    for (BufferedImage[][] layer : levelTileMaps
        .get(currentLevel == levelTileMaps.size() ? currentLevel - 1 : currentLevel)) {
      for (int i = 0; i < TurtleHacks.HEIGHT / 64; i++) {
        for (int j = 0; j < TurtleHacks.WIDTH / 64; j++) {
          g.drawImage(layer[i][j], j * 64, i * 64, 64, 64, null, null);
          // g.drawRect(j * 64, i * 64, 64, 64);
        }
      }
    }

    g.setColor(Color.black);
    g.setFont(new Font("Serif", Font.BOLD, 25));
    g.drawString("Round: " + round_number, 16 * 64 - 48, 0 * 64 + 32);

    g.setColor(Color.black);
    g.setFont(new Font("Serif", Font.BOLD, 25));
    g.drawString("Points: " + total_points, 16 * 64 - 48, 1 * 64 + 16);

    for (GarbageObject obj : trashList) {
      obj.render(g);
    }

    trashBin.render(g);
    recycleBin.render(g);

    if (isGrabbing) {
      player.renderGrab(g, grabFrame);
      grabFrame++;
      if (grabFrame == PlayerAssets.pickUpDownAnimations.size()) {
        checkForGarbage();
        isGrabbing = false;
        grabFrame = 0;
      }
    } else if (isRolling && currentLevel != levelTileMaps.size()) {
      player.renderRoll(g, rollingFrame);
      rollingFrame++;
      if (rollingFrame == PlayerAssets.rollAnimations.size()) {
        isRolling = false;
        rollingFrame = 0;
        if (garbageWavesLeft != 0) {
          generateGarbage();
        } else {
          isLevelTransis = true;
        }
      }
    } else {
      player.render(g);
    }

    if (isGameWon) {
      g.drawImage(Assets.endingScreen[gameWonFrame], 0, 0, TurtleHacks.WIDTH, TurtleHacks.HEIGHT, null, null);
      other++;
      if (other == 10) {
        other = 0;
        gameWonFrame++;
      }
    }

    if (isLevelTransis) {
      g.drawImage(Assets.endingScreen[levelTransisFrame], 0, 0, TurtleHacks.WIDTH, TurtleHacks.HEIGHT, null,
          null);
      other2++;
      if (other2 == 10) {
        other2 = 0;
        levelTransisFrame++;
      }
    }

  }

  public void renderFunFact(Graphics g) {
    g.drawImage(Assets.endingScreen[levelTransisFrame - 1], 0, 0, TurtleHacks.WIDTH, TurtleHacks.HEIGHT, null, null);
    g.setFont(new Font("Serif", Font.BOLD, 35));
    g.drawString("FUN FACT: DONT BE DUMB", 4 * 64, 5 * 64);
  }

  public void update() {
    if (isMenuState)
      return;
    if (isGrabbing || isRolling || isGameWon)
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
    // Player collision with map border
    if (player.getX() < 0 || player.getX() >= TurtleHacks.WIDTH / 64 || player.getY() < 0
        || player.getY() >= TurtleHacks.HEIGHT / 64) {
      player.updateAfterCollide();
    }

    // Player collision with trash bin and recycling bin
    if (trashBin.get_X() == (int) player.getX() && trashBin.get_Y() == (int) player.getY()) {
      player.updateAfterCollide();
    }

    if (recycleBin.get_X() == (int) player.getX() && recycleBin.get_Y() == (int) player.getY()) {
      player.updateAfterCollide();
    }

    if (currentLevel == levelTileMaps.size()) {
      return;
    }
    // Player collision with map objects
    if (!allowedObjects
        .contains(
            levelTileMaps.get(currentLevel)
                .get(levelTileMaps.get(currentLevel).size() - 1)[(int) player.getY()][(int) player.getX()])) {
      player.updateAfterCollide();

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
      if (player.getX() + xDir == obj.getX() && player.getY() + yDir == obj.getY()) {
        if (player.isInventoryFull())
          break;
        trashList.remove(obj);
        player.pushGarbage(obj);

        break;
      }
    }
  }

  public void checkForBin() {
    if (player.getInventory().size() == 0)
      return;

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

    if (player.getX() + xDir == trashBin.get_X() && player.getY() + yDir == trashBin.get_Y()) {
      GarbageObject top = player.removeGarbage();
      if (top.getBinType().equals(trashBin.get_binType())) {
        total_points++;
      } else {
        total_points -= 2;
      }
      if (player.getInventory().size() == 0) {
        if (trashList.size() == 0) {
          garbageWavesLeft -= 1;
          isRolling = true;
        }
      }
    }
    if (player.getX() + xDir == recycleBin.get_X() && player.getY() + yDir == recycleBin.get_Y()) {
      GarbageObject top = player.removeGarbage();
      if (top.getBinType().equals(recycleBin.get_binType())) {
        total_points++;
      } else {
        total_points -= 2;
      }
      if (player.getInventory().size() == 0) {
        if (trashList.size() == 0) {
          garbageWavesLeft -= 1;
          isRolling = true;
        }
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
    if (e.getKeyChar() == 'e' && player.stopped && !isGrabbing) {
      checkForBin();
      isGrabbing = true;
      grabFrame = 0;
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

}
