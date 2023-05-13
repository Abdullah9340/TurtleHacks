import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class GarbageObject {
    private int x_pos, y_pos;
    private String garbageType;
    private BufferedImage trashIcon;
    private BufferedImage trashRotatedIcon;
    public static HashMap<Integer, String> typeMap = new HashMap<Integer, String>();
    public static HashMap<String, BufferedImage> iconMap = new HashMap<String, BufferedImage>();

    public static void init() {
        typeMap.put(0, "bottle");
        typeMap.put(1, "battery");

        iconMap.put("bottle", GarbageAssets.bottle);
        iconMap.put("battery", GarbageAssets.battery);
        iconMap.put("bottleRotated", GarbageAssets.bottleRotated);
        iconMap.put("batteryRotated", GarbageAssets.batteryRotated);
    }

    public GarbageObject() {
        Random rand = new Random();

        int typeInt = rand.nextInt(2);

        int x_bound = TurtleHacks.WIDTH / 64;
        int y_bound = TurtleHacks.HEIGHT / 64;

        x_pos = rand.nextInt(x_bound);
        y_pos = rand.nextInt(y_bound);

        garbageType = typeMap.get(typeInt);

        trashIcon = iconMap.get(garbageType);
        trashRotatedIcon = iconMap.get(garbageType+"Rotated");

    }

    public void render(Graphics g) {
        g.drawImage(trashIcon, x_pos * 64 + 16, y_pos * 64 + 16, 32, 32, null, null);
    }

    public int getX() {
        return x_pos;
    }

    public int getY() {
        return y_pos;
    }

    public BufferedImage getIcon() {
        return trashIcon;
    }
    
    public BufferedImage getRotatedIcon() {
      return trashRotatedIcon;
    }

    public String getGarbageType() {
      return garbageType;
    }

}