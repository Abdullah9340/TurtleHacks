import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

  private double x, y;
  private char direction = 's';
  private double velocityX = 0;
  private double velocityY = 0;
  int walkSize = PlayerAssets.upAnimations.size();
  int currPlayerWalk = 0;

  // Movement logic
  boolean stopped = true;
  char nextDirection = direction;

  public Player(double x, double y) {
    this.x = x;
    this.y = y;
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
    int nextX = (int) (x * 64), nextY = (int) (y * 64);
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

  }

  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    stopped = false;
    if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == 38) {
      nextDirection = 'w';

    }
    // Movement direction (back)
    // set to the letter s or down arrow on the keyboard
    if (e.getKeyChar() == 's' || e.getKeyChar() == 'S' || e.getKeyCode() == 40) {
      nextDirection = 's';

    }
    // Movement direction (left)
    // set to the letter a or left arrow on the keyboard
    if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getKeyCode() == 37) {
      nextDirection = 'a';

    }
    // Movement direction (right)
    // set to the letter d or right arrow on the keyboard
    if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == 39) {
      nextDirection = 'd';

    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    stopped = true;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
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

}
