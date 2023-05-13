import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game implements Runnable, KeyListener {
  private String title;
  private int WIDTH, HEIGHT;

  // Variables for graphics objects
  private Display display;
  private Graphics g;
  private BufferStrategy bs;
  
  // Player Object
  Player player;

  private boolean running = false;
  private boolean isMenu = false;

  // Variables for framerate
  private double timePerTick, delta;
  long now, lastTime;
  private int FPS = 15;

  int currPlayerWalk, currPlayerPickup, currPlayerDance = 0;

  private Thread thread;

  /*-
  * Game() 
  * Description: This calls the title, sets the width and height Also
  * starts the game 
  * Pre: the width, height dimensions, title 
  * Post: Starts game
  */
  public Game(String title, int width, int height) {
    this.title = title;
    this.WIDTH = width;
    this.HEIGHT = height;
    this.start(); // Start the thread/game
  }

  public void init() {
    display = new Display(title, WIDTH, HEIGHT); // Set up display
    Assets.init();
    player = new Player(5, 5);
    display.getJFrame().addKeyListener(player); // Set up keylisteners
    display.getJFrame().addKeyListener(this);
    isMenu = false;
    timePerTick = 1000000000 / FPS;
    delta = 0;
    lastTime = System.nanoTime();
  }

  public void update() {
    player.update();
  }

  public void render() {
    bs = display.getCanvas().getBufferStrategy();
    if (bs == null) { // Create a buffer strategy
      display.getCanvas().createBufferStrategy(3);
      return;
    }
    g = bs.getDrawGraphics();
    g.clearRect(0, 0, WIDTH, HEIGHT); // Clear the background
    g.setColor(Color.BLACK);
    player.render(g);

    // // Walk animations
    // g.drawImage(PlayerAssets.upAnimations.get(currPlayerWalk % walkSize), 0, 0, 64, 64, null,
    //     null);
    // g.drawImage(PlayerAssets.leftAnimations.get(currPlayerWalk % walkSize), 64, 0, 64, 64, null,
    //     null);
    // g.drawImage(PlayerAssets.rightAnimations.get(currPlayerWalk % walkSize), 64 * 2, 0, 64, 64,
    //     null,
    //     null);
    // g.drawImage(PlayerAssets.downAnimations.get(currPlayerWalk % walkSize), 64 * 3, 0, 64, 64,
    //     null,
    //     null);

    // // Pickup animations
    // g.drawImage(PlayerAssets.pickUpUpAnimations.get(currPlayerPickup % pickupSize), 0, 64,
    //     64, 64,
    //     null,
    //     null);
    // g.drawImage(PlayerAssets.pickUpDownAnimations.get(currPlayerPickup % pickupSize), 64,
    //     64, 64, 64,
    //     null,
    //     null);
    // g.drawImage(PlayerAssets.pickUpLeftAnimations.get(currPlayerPickup % pickupSize),
    //     64 * 2, 64, 64,
    //     64,
    //     null,
    //     null);
    // g.drawImage(PlayerAssets.pickUpRightAnimations.get(currPlayerPickup % pickupSize),
    //     64 * 3, 64, 64,
    //     64,
    //     null,
    //     null);
    
    // // Dance animations
    // g.drawImage(PlayerAssets.danceAnimations.get(currPlayerDance % danceSize), 0, 128,
    //     64, 64,
    //     null,
    //     null);
    
    // currPlayerPickup++;
    // currPlayerWalk++;
    // currPlayerDance++;
    // currPlayerPickup %= PlayerAssets.upAnimations.size();
    // currPlayerWalk %= PlayerAssets.upAnimations.size();
    // currPlayerDance %= PlayerAssets.danceAnimations.size();
    // End Draw
    bs.show();
    g.dispose();

  }

  /*-
  * start()
  * Description: Starts the whole game
  * initializes the thread
  * Pre: None
  * Post: Starts the game thread 
  */
  public void start() {
    if (running) {
      return;
    }
    running = true;
    thread = new Thread(this);
    thread.start(); // Start the thread
  }

  /*-
  * stop()
  * Pre: None
  * Post: Starts the game thread 
  */
  public void stop() { // Stop the thread
    if (!running) {
      return;
    }
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    init();

    // Create constant framerate
    // Main game loop
    while (running) {
      // If the player is in the menu state
      if (isMenu) {
        // Menu Stuff

      } else { // If the player is has started the game
        now = System.nanoTime();
        delta += (now - lastTime) / timePerTick;
        lastTime = now;

        // Main game loop
        if (delta >= 1) {
          update();
          render();
          delta--;
        }
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (isMenu) {
      isMenu = false;
      timePerTick = 1000000000 / FPS;
      delta = 0;
      lastTime = System.nanoTime();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

}
