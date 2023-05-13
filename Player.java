import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

  private int inventory = 0;
  private int score = 0;
  private int x, y;
  private int FPS = 10;
  private  char direction = 's';
  private  int velocityX = 0;
  private  int velocityY = 0;
  int walkSize =  PlayerAssets.upAnimations.size();
  int currPlayerWalk= 0;


  public Player(int x, int y) {
    this.inventory = 0;
    this.score = 0;
    this.x = x;
    this.y = y;
  }


  public void update() {
    x += velocityX;
    y += velocityY;
    currPlayerWalk += Math.max(Math.abs(velocityX), Math.abs(velocityY));
    currPlayerWalk %= walkSize;
  }

  public void render(Graphics g) {

    // Walk animations
    if (getDirection() == 'w'){
    g.drawImage(PlayerAssets.upAnimations.get(currPlayerWalk), x*64, y*64, 64, 64, null,
        null);
    } else if (getDirection() == 'a') {
      g.drawImage(PlayerAssets.leftAnimations.get(currPlayerWalk), x*64, y*64, 64, 64, null,
        null);
    }
    else if (getDirection() == 'd') {
    g.drawImage(PlayerAssets.rightAnimations.get(currPlayerWalk), x*64, y*64, 64, 64,
        null,
        null);
    }
    else if (getDirection() == 's') {
    g.drawImage(PlayerAssets.downAnimations.get(currPlayerWalk), x*64, y*64, 64, 64,
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
    System.out.println(e.getKeyChar());
    if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == 38) {
      setDirection('w');
      setVelocityX(0);
      setVelocityY(-1);
    } else if (e.getKeyChar() == 's' || e.getKeyChar() == 'S' || e.getKeyCode() == 40) {
      setDirection('s');
      setVelocityX(0);
      setVelocityY(1);

    } else if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getKeyCode() == 37) {
      setDirection('a');
      setVelocityX(-1);
      setVelocityY(0);

    } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == 39) {
      setDirection('d');
      setVelocityX(1);
      setVelocityY(0);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    setVelocityX(0);
    setVelocityY(0);
    currPlayerWalk = 0;
  }


  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }

  public void setDirection(char direction){
    this.direction = direction;
  }
  public char getDirection(){
    return this.direction;
  }

  public void setVelocityX(int velocity){
    this.velocityX = velocity;
  }
  public void setVelocityY(int velocity){
    this.velocityY = velocity;
  }
  
}
