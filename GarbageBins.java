import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GarbageBins {
    private int x_pos, y_pos;
    private String binType;
    private BufferedImage binIcon;

    public GarbageBins(String type) {
        if (type == "trash") {
            x_pos = 0;
            y_pos = 9;
            binType = type;
            binIcon = GarbageAssets.garbageBin;
        } else if (type == "recycling") {
            x_pos = 1;
            y_pos = 9;
            binType = type;
            binIcon = GarbageAssets.recyclingBin;
        }
    }

    public void render(Graphics g) {
        g.drawImage(binIcon, x_pos * 64 + 8, y_pos * 64, 64, 64, null, null);
    }

    public int get_X() {
        return x_pos;
    }

    public int get_Y() {
        return y_pos;
    }

    public String get_binType() {
        return binType;
    }

}
