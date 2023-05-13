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

  private boolean running = false;
  private boolean isMenu = false;

  // Variables for framerate
  private double timePerTick, delta;
  long now, lastTime;
  private int FPS = 10;

  int currPlayerWalk, currPlayerPickup = 0;

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
    isMenu = false;
    timePerTick = 1000000000 / FPS;
    delta = 0;
    lastTime = System.nanoTime();
  }

  public void update() {

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
    for (BufferedImage[][] layer : BackgroundAssets.firstLevelLayers) {
      for (int i = 0; i < TurtleHacks.HEIGHT / 64; i++) {
        for (int j = 0; j < TurtleHacks.WIDTH / 64; j++) {
          g.drawImage(layer[i][j], j * 64, i * 64, 64, 64, null, null);
        }
      }
    }

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
    // TODO Auto-generated method stub
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
