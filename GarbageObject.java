import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

public class GarbageObject {
    private int x_pos, y_pos;
    private String garbageType;
    private BufferedImage trashIcon;
    public static HashMap<Integer, String> typeMap = new HashMap<Integer, String>();
    public static HashMap<String, BufferedImage> iconMap = new HashMap<String, BufferedImage>();

    public static void init() {
        typeMap.put(0, "bottle");
        typeMap.put(1, "battery");

        iconMap.put("bottle", GarbageAssets.bottle);
        iconMap.put("battery", GarbageAssets.battery);
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

        // randomly set x,y positions, set garbage type, once have garbage type set the
        // image
    }

    public void render(Graphics g) {
        g.drawImage(trashIcon, x_pos, y_pos, 64, 64, null, null);
    }

}