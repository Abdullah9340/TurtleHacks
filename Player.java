import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Player implements KeyListener {

  // Movement variables
  private double x, y;
  private char direction = 's';
  private double velocityX = 0;
  private double velocityY = 0;

  // Animation array sizes
  int walkSize = PlayerAssets.upAnimations.size();
  int pickupSize = PlayerAssets.pickUpDownAnimations.size();
  int currPlayerWalk = 0;

  // Movement logic
  boolean stopped = true;
  char nextDirection = direction;

  // Hotbar variables
  private HashMap<String, Integer> hotbar;
  private int inventoryLimit = 3;
  private ArrayList<GarbageObject> inventory;

  public Player(double x, double y) {
    this.hotbar = new HashMap<>();
    this.inventory = new ArrayList<GarbageObject>();
    this.x = x;
    this.y = y;
    this.hotbar.put("bottle", 0);
    this.hotbar.put("battery", 0);
  }

  public void updateDirectionMovements() {
    if (direction == 'w') {
      setVelocityX(0);
      setVelocityY(-0.25);
    } else if (direction == 's') {
      setVelocityX(0);
      setVelocityY(0.25);

    } else if (direction == 'a') {
      setVelocityX(-0.25);
      setVelocityY(0);

    } else if (direction == 'd') {
      setVelocityX(0.25);
      setVelocityY(0);
    }
  }

  public void update() {
    if (x % 1 == 0 && y % 1 == 0) {
      if (stopped) {
        setVelocityX(0);
        setVelocityY(0);
        currPlayerWalk = 0;
      } else {
        direction = nextDirection;
        updateDirectionMovements();
      }
    }
    x += velocityX;
    y += velocityY;
    currPlayerWalk += Math.max(Math.abs(velocityX * 4), Math.abs(velocityY * 4));
    currPlayerWalk %= walkSize;
  }

  public void render(Graphics g) {
    int nextX = (int) (x * 64), nextY = (int) (y * 64) - 8;
    // Walk animations
    if (getDirection() == 'w') {
      g.drawImage(PlayerAssets.upAnimations.get(currPlayerWalk), nextX, nextY, 64, 64, null,
          null);
    } else if (getDirection() == 'a') {
      g.drawImage(PlayerAssets.leftAnimations.get(currPlayerWalk), nextX, nextY, 64, 64, null,
          null);
    } else if (getDirection() == 'd') {
      g.drawImage(PlayerAssets.rightAnimations.get(currPlayerWalk), nextX, nextY, 64, 64,
          null,
          null);
    } else if (getDirection() == 's') {
      g.drawImage(PlayerAssets.downAnimations.get(currPlayerWalk), nextX, nextY, 64, 64,
          null,
          null);
    }
    renderHotbar(g);
  }

  public void renderGrab(Graphics g, int grabIndex) {
    int nextX = (int) (x * 64), nextY = (int) (y * 64) - 8;
    if (getDirection() == 'w') {
      g.drawImage(PlayerAssets.pickUpUpAnimations.get(grabIndex), nextX, nextY, 64, 64, null,
          null);
    } else if (getDirection() == 'a') {
      g.drawImage(PlayerAssets.pickUpLeftAnimations.get(grabIndex), nextX, nextY, 64, 64, null,
          null);
    } else if (getDirection() == 'd') {
      g.drawImage(PlayerAssets.pickUpRightAnimations.get(grabIndex), nextX, nextY, 64, 64,
          null,
          null);
    } else if (getDirection() == 's') {
      g.drawImage(PlayerAssets.pickUpDownAnimations.get(grabIndex), nextX, nextY, 64, 64,
          null,
          null);
    }
    renderHotbar(g);
  }

  public void renderRoll(Graphics g, int rollIndex) {
    int nextX = (int) (x * 64), nextY = (int) (y * 64) - 8;
    g.drawImage(PlayerAssets.rollAnimations.get(rollIndex), nextX, nextY, null, null);
    renderHotbar(g);
  }

  public void renderHotbar(Graphics g) {
    g.setColor(Color.darkGray);
    for (int i = 0; i < inventoryLimit; i++) {
      drawThickRect(g, i * 64, 0, 64, 64, 3);
    }
    for (int i = 0; i < inventory.size(); i++) {
      g.drawImage(inventory.get(i).getRotatedIcon(), i * 64, 0, 64, 64, null, null);
    }
  }

  public void updateAfterCollide() {
    x -= velocityX;
    y -= velocityY;

    velocityX = 0;
    velocityY = 0;

    stopped = true;
    currPlayerWalk = 0;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == 38) {
      nextDirection = 'w';
      stopped = false;
    }
    // Movement direction (back)
    // set to the letter s or down arrow on the keyboard
    if (e.getKeyChar() == 's' || e.getKeyChar() == 'S' || e.getKeyCode() == 40) {
      nextDirection = 's';
      stopped = false;
    }
    // Movement direction (left)
    // set to the letter a or left arrow on the keyboard
    if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getKeyCode() == 37) {
      nextDirection = 'a';
      stopped = false;
    }
    // Movement direction (right)
    // set to the letter d or right arrow on the keyboard
    if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == 39) {
      nextDirection = 'd';
      stopped = false;
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == 38) {
      stopped = true;
    }
    // Movement direction (back)
    // set to the letter s or down arrow on the keyboard
    if (e.getKeyChar() == 's' || e.getKeyChar() == 'S' || e.getKeyCode() == 40) {
      stopped = true;
    }
    // Movement direction (left)
    // set to the letter a or left arrow on the keyboard
    if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getKeyCode() == 37) {
      stopped = true;
    }
    // Movement direction (right)
    // set to the letter d or right arrow on the keyboard
    if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == 39) {
      stopped = true;
    }
  }

  public double getX() {
    if (direction == 'a') {
      return Math.floor(x);
    } else {
      return Math.ceil(x);
    }
  }

  public double getY() {
    if (direction == 's') {
      return Math.ceil(y);
    } else {
      return Math.floor(y);
    }
  }

  public void setDirection(char direction) {
    this.direction = direction;
  }

  public char getDirection() {
    return this.direction;
  }

  public void setVelocityX(double velocity) {
    this.velocityX = velocity;
  }

  public void setVelocityY(double velocity) {
    this.velocityY = velocity;
  }

  public void pushGarbage(GarbageObject garbage) {
    inventory.add(garbage);
  }

  public GarbageObject removeGarbage() {
    if (inventory.size() == 0) return null;
    return inventory.remove(0);
  }

  public ArrayList<GarbageObject> getInventory() {
    return inventory;
  }

  public boolean isInventoryFull() {
    return inventory.size() == inventoryLimit;
  }

  public void drawThickRect(Graphics g, int x, int y, int width,
      int height, int thickness) {
    for (int i = 0; i < thickness; i++)
      g.drawRect(x + i, y + i, width - 2 * i, height - 2 * i);
  }

}
